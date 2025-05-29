import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { DragDropContext, Droppable, Draggable } from "@hello-pangea/dnd";
import "../styles/GerarEquipes.css";

const LOCAL_STORAGE_KEY = "gerarEquipesDistribuicao";

const GerarEquipes = () => {
  const [equipes, setEquipes] = useState({});
  const [pessoasMap, setPessoasMap] = useState({});
  const { cpf, ano } = useParams();

  // Mapeamento dos nomes das equipes para seus IDs reais (ajuste conforme backend)
  const equipeNomeParaId = {
    Emaús: 1,
    Jordão: 2,
    Damasco: 3,
    Naim: 4,
    Jericó: 5,
    Cafarnaum: 6,
    Profetas: 7,
    Evangelistas: 8,
    Salmistas: 9,
    Sião: 10,
    Maná: 11,
  };

  const calcularIdade = (dataNascimento) => {
    if (!dataNascimento) return 0;
    const hoje = new Date();
    const nascimento = new Date(dataNascimento);
    let idade = hoje.getFullYear() - nascimento.getFullYear();
    const m = hoje.getMonth() - nascimento.getMonth();
    if (m < 0 || (m === 0 && hoje.getDate() < nascimento.getDate())) {
      idade--;
    }
    return idade;
  };

  const obterClassificacao = (dataNascimento) => {
    const idade = calcularIdade(dataNascimento);
    return idade < 18 ? "Adolescente" : "Jovem";
  };

  const verificarConflito = (pessoa, nomeEquipeAtual, equipes) => {
    if (!pessoa.conexoesFamiliares || pessoa.conexoesFamiliares.length === 0) return false;

    for (const conexao of pessoa.conexoesFamiliares) {
      const cpfConexao = conexao.cpf;

      let equipeConexao = null;
      for (const [nomeEquipe, membros] of Object.entries(equipes)) {
        if (membros.some((m) => m.cpf === cpfConexao)) {
          equipeConexao = nomeEquipe;
          break;
        }
      }

      if (conexao.queremTrabalharJuntos === false && equipeConexao === nomeEquipeAtual) {
        return true;
      }

      if (conexao.queremTrabalharJuntos === true && equipeConexao && equipeConexao !== nomeEquipeAtual) {
        return true;
      }
    }
    return false;
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Primeiro, tentar carregar equipes do localStorage
        const equipesSalvas = localStorage.getItem(LOCAL_STORAGE_KEY);
        if (equipesSalvas) {
          const equipesParse = JSON.parse(equipesSalvas);
          setEquipes(equipesParse);
          return;
        }

        // Se não tiver no localStorage, buscar do backend normalmente
        const [encontreirosRes, pessoasRes, familiaresRes] = await Promise.all([
          fetch("http://localhost:8080/encontreiros"),
          fetch("http://localhost:8080/pessoas"),
          fetch("http://localhost:8080/encontreiros/familiares"),
        ]);

        const [encontreirosData, pessoasData, familiaresData] = await Promise.all([
          encontreirosRes.json(),
          pessoasRes.json(),
          familiaresRes.json(),
        ]);

        const pessoasMapTemp = {};
        pessoasData.forEach((p) => {
          pessoasMapTemp[p.cpf] = p;
        });
        setPessoasMap(pessoasMapTemp);

        const conexoesTemp = {};
        familiaresData.forEach(({ encontreiro1Cpf, encontreiro2Cpf, relacao, trabalhar_junto }) => {
          if (!conexoesTemp[encontreiro1Cpf]) conexoesTemp[encontreiro1Cpf] = [];
          if (!conexoesTemp[encontreiro2Cpf]) conexoesTemp[encontreiro2Cpf] = [];

          conexoesTemp[encontreiro1Cpf].push({
            cpf: encontreiro2Cpf,
            nome: pessoasMapTemp[encontreiro2Cpf]?.nome || "Desconhecido",
            parentesco: relacao,
            queremTrabalharJuntos: trabalhar_junto,
          });

          conexoesTemp[encontreiro2Cpf].push({
            cpf: encontreiro1Cpf,
            nome: pessoasMapTemp[encontreiro1Cpf]?.nome || "Desconhecido",
            parentesco: relacao,
            queremTrabalharJuntos: trabalhar_junto,
          });
        });

        const equipesIniciais = {
          Emaús: [],
          Jordão: [],
          Damasco: [],
          Naim: [],
          Jericó: [],
          Cafarnaum: [],
          Profetas: [],
          Evangelistas: [],
          Salmistas: [],
          Sião: [],
          Maná: [],
          "Não atribuídos": [],
        };

        encontreirosData.forEach((encontreiro) => {
          equipesIniciais["Não atribuídos"].push({
            ...encontreiro,
            ...pessoasMapTemp[encontreiro.cpf],
            conexoesFamiliares: conexoesTemp[encontreiro.cpf] || [],
          });
        });

        setEquipes(equipesIniciais);
      } catch (error) {
        console.error("Erro ao buscar dados:", error);
      }
    };

    fetchData();
  }, []);

  // Salvar equipes no localStorage sempre que equipes mudarem
  useEffect(() => {
    if (Object.keys(equipes).length > 0) {
      localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(equipes));
    }
  }, [equipes]);

  // Função para atualizar o banco a cada drag end
  const atualizarRegistroNoBanco = async (cpfPessoa, equipeNova) => {
    const encontroAno = parseInt(ano) || 2022;

    for (const [nomeEquipe, membros] of Object.entries(equipes)) {
      if (membros.some((m) => m.cpf === cpfPessoa)) {
        if (nomeEquipe !== equipeNova && nomeEquipe !== "Não atribuídos") {
          const equipeIdAntiga = equipeNomeParaId[nomeEquipe];
          try {
            await fetch(`http://localhost:8080/registroEncontreiro/${encontroAno}/${equipeIdAntiga}/${cpfPessoa}`, {
              method: "DELETE",
            });
          } catch (error) {
            console.warn(`Erro ao deletar registro antigo de ${cpfPessoa}:`, error);
          }
        }
      }
    }

    if (equipeNova !== "Não atribuídos") {
      const equipeIdNova = equipeNomeParaId[equipeNova];
      if (!equipeIdNova) {
        console.warn(`Equipe "${equipeNova}" sem ID definido, pulando inserção.`);
        return;
      }

      const body = {
        encontro: { ano: encontroAno },
        equipe: { id: equipeIdNova },
        encontreiro: { cpf: cpfPessoa },
      };

      try {
        const res = await fetch("http://localhost:8080/registroEncontreiro", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(body),
        });

        if (!res.ok) {
          console.error(`Falha ao registrar encontreiro ${cpfPessoa}, status: ${res.status}`);
        }
      } catch (error) {
        console.error(`Erro ao registrar encontreiro ${cpfPessoa}:`, error);
      }
    }
  };

  const onDragEnd = async (result) => {
    const { source, destination } = result;
    if (!destination) return;
    if (source.droppableId === destination.droppableId && source.index === destination.index) return;

    const newEquipes = { ...equipes };
    const sourceEquipe = Array.from(newEquipes[source.droppableId]);
    const [movedItem] = sourceEquipe.splice(source.index, 1);

    if (source.droppableId === destination.droppableId) {
      sourceEquipe.splice(destination.index, 0, movedItem);
      newEquipes[source.droppableId] = sourceEquipe;
    } else {
      const destEquipe = Array.from(newEquipes[destination.droppableId]);
      destEquipe.splice(destination.index, 0, movedItem);
      newEquipes[source.droppableId] = sourceEquipe;
      newEquipes[destination.droppableId] = destEquipe;
    }

    setEquipes(newEquipes);

    await atualizarRegistroNoBanco(movedItem.cpf, destination.droppableId);
  };

  return (
    <div className="container-equipes">
      <h1>Equipes</h1>
      <Link to={`/encontro-atual/${cpf}/${ano}`}>
        <button className="btn-sair">Voltar</button>
      </Link>

      <DragDropContext onDragEnd={onDragEnd}>
        {Object.entries(equipes).map(([nomeEquipe, membros]) => (
          <Droppable key={nomeEquipe} droppableId={nomeEquipe}>
            {(provided) => (
              <div className="equipe" ref={provided.innerRef} {...provided.droppableProps}>
                <h2>{nomeEquipe}</h2>
                {membros.map((pessoa, i) => (
                  <Draggable key={pessoa.cpf} draggableId={pessoa.cpf} index={i}>
                    {(provided) => (
                      <div
                        className={`pessoa-item ${verificarConflito(pessoa, nomeEquipe, equipes) ? "conflito" : ""}`}
                        ref={provided.innerRef}
                        {...provided.draggableProps}
                        {...provided.dragHandleProps}
                      >
                        <div style={{ display: "flex", alignItems: "center", gap: "8px" }}>
                          <img
                            className="pessoa-foto"
                            src={pessoa.foto_url || "default.jpg"}
                            alt={pessoa.nome || pessoa.cpf}
                          />
                          <div className="pessoa-info">
                            <div>
                              <strong>{pessoa.nome || pessoa.cpf}</strong>
                            </div>
                            <div className="pessoa-cpf">CPF: {pessoa.cpf}</div>
                            <div className="pessoa-tipo">{obterClassificacao(pessoa.data_nascimento)}</div>
                          </div>
                        </div>
                        {pessoa.conexoesFamiliares?.length > 0 && (
                          <div className="conexao-info">
                            <div style={{ color: "green" }}>Conectado com:</div>
                            <ul>
                              {pessoa.conexoesFamiliares.map((conexao, idx) => (
                                <li key={idx}>
                                  <strong>{conexao.nome}</strong> - {conexao.parentesco} -{" "}
                                  {conexao.queremTrabalharJuntos ? "Querem trabalhar juntos" : "Não querem"}
                                </li>
                              ))}
                            </ul>
                          </div>
                        )}
                      </div>
                    )}
                  </Draggable>
                ))}
                {provided.placeholder}
              </div>
            )}
          </Droppable>
        ))}
      </DragDropContext>
    </div>
  );
};

export default GerarEquipes;

import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import '../styles/EncontroAtual.css';

const EncontroAtual = () => {
  const [encontreiros, setEncontreiros] = useState([]);
  const [pessoasMapeadas, setPessoasMapeadas] = useState({});
  const [expandedCpf, setExpandedCpf] = useState(null);
  const [modoEdicao, setModoEdicao] = useState({});
  const [formEdicao, setFormEdicao] = useState({});
  const { cpf, ano } = useParams();

  useEffect(() => {
    Promise.all([
      fetch("http://localhost:8080/encontreiros").then(res => res.json()),
      fetch("http://localhost:8080/pessoas").then(res => res.json())
    ])
    .then(([encontreirosData, pessoasData]) => {
      const mapa = {};
      pessoasData.forEach(p => { mapa[p.cpf] = p; });
      setPessoasMapeadas(mapa);
      setEncontreiros(encontreirosData);
    })
    .catch(error => console.error("Erro ao buscar dados:", error));
  }, []);

  const adolescentes = encontreiros.filter(e => !e.fezEjc);
  const jovens = encontreiros.filter(e => e.fezEjc);

  const toggleExpand = (cpf) => {
    setExpandedCpf(expandedCpf === cpf ? null : cpf);
  };

  const toggleModoEdicao = (e) => {
    const isEditando = !!modoEdicao[e.cpf];
    if (!isEditando) {
      setFormEdicao({
        ...formEdicao,
        [e.cpf]: {
          responsavelNome: e.responsavelNome || "",
          responsavelTelefone: e.responsavelTelefone || ""
        }
      });
    }
    setModoEdicao({ ...modoEdicao, [e.cpf]: !isEditando });
  };

  const handleChange = (cpf, campo, valor) => {
    setFormEdicao({
      ...formEdicao,
      [cpf]: {
        ...formEdicao[cpf],
        [campo]: valor
      }
    });
  };

  const salvarEdicao = async (e) => {
    const dadosEditados = {
      cpf: e.cpf,
      fezEjc: e.fezEjc,
      responsavelNome: formEdicao[e.cpf].responsavelNome,
      responsavelTelefone: formEdicao[e.cpf].responsavelTelefone
    };

    try {
      const response = await fetch(`http://localhost:8080/encontreiros/${e.cpf}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(dadosEditados)
      });

      if (response.ok) {
        const atualizados = encontreiros.map(en => en.cpf === e.cpf ? dadosEditados : en);
        setEncontreiros(atualizados);
        setModoEdicao({ ...modoEdicao, [e.cpf]: false });
      } else {
        console.error("Erro ao salvar edição.");
      }
    } catch (error) {
      console.error("Erro ao salvar edição:", error);
    }
  };

  const apagarEncontreiro = async (cpf) => {
  if (!window.confirm("Tem certeza que deseja apagar este encontreiro?")) return;

  try {
    const response = await fetch(`http://localhost:8080/encontreiros/${cpf}`, {
      method: "DELETE"
    });

    if (response.ok) {
      const atualizados = encontreiros.filter(e => e.cpf !== cpf);
      setEncontreiros(atualizados);
    } else {
      // Tentar extrair mensagem de erro do backend
      const erroJson = await response.json().catch(() => null);

      // Verificar se é erro relacionado a registros existentes
      if (response.status === 409 || (erroJson && erroJson.message && erroJson.message.toLowerCase().includes("registro_encontreiro"))) {
        alert("Este encontreiro está presente em registros associados. Por favor, remova o encontreiro dos registros antes de apagar.");
      } else {
        alert("Erro ao apagar encontreiro. Tente novamente.");
        console.error("Erro ao apagar encontreiro:", erroJson || response.statusText);
      }
    }
  } catch (error) {
    alert("Erro ao apagar encontreiro. Verifique sua conexão e tente novamente.");
    console.error("Erro ao apagar:", error);
  }
};


  const renderEncontreiro = (e, numero) => {
    const pessoa = pessoasMapeadas[e.cpf];
    const isExpanded = expandedCpf === e.cpf;
    const isEditando = !!modoEdicao[e.cpf];

    return (
      <div key={e.cpf} className="item-encontro" style={{ display: "flex", alignItems: "center", gap: "10px" }}>
        <p className="numero-encontreiro" style={{ fontWeight: "bold", width: "24px", textAlign: "right" }}>{numero}.</p>
        <img
          src={pessoa?.foto_url || "/default-foto.png"}
          alt="Foto de perfil"
          className="foto-perfil"
        />
        <div>
          <p><strong>CPF:</strong> {e.cpf}</p>
          <p><strong>Nome:</strong> {pessoa?.nome || "Nome não encontrado"}</p>
          <p><strong>Apelido:</strong> {pessoa?.apelido || "Apelido não encontrado"}</p>
          <p><strong>Telefone:</strong> {pessoa?.telefone || "Telefone não encontrado"}</p>

          <button onClick={() => toggleExpand(e.cpf)} className="botao-ver-mais">
            {isExpanded ? "Ver menos" : "Ver mais"}
          </button>

          {isExpanded && (
            <div className="infos-adicionais">
              <p><strong>Data de Nascimento:</strong> {pessoa?.data_nascimento || "Não informado"}</p>
              <p><strong>Bairro:</strong> {pessoa?.bairro || "Não informado"}</p>
              <p><strong>Complemento:</strong> {pessoa?.complemento || "Não informado"}</p>
              <p><strong>Número:</strong> {pessoa?.numero || "Não informado"}</p>
              <p><strong>Rua:</strong> {pessoa?.rua || "Não informado"}</p>
              <p><strong>CEP:</strong> {pessoa?.cep || "Não informado"}</p>
              <p><strong>Instituição de Ensino:</strong> {pessoa?.instituicao_ensino || "Não informado"}</p>

              {isEditando ? (
                <>
                  <p>
                    <strong>Nome do Responsável:</strong>
                    <input
                      type="text"
                      value={formEdicao[e.cpf]?.responsavelNome || ""}
                      onChange={e2 => handleChange(e.cpf, "responsavelNome", e2.target.value)}
                    />
                  </p>
                  <p>
                    <strong>Telefone do Responsável:</strong>
                    <input
                      type="text"
                      value={formEdicao[e.cpf]?.responsavelTelefone || ""}
                      onChange={e2 => handleChange(e.cpf, "responsavelTelefone", e2.target.value)}
                    />
                  </p>
                  <button onClick={() => salvarEdicao(e)} className="botao-ver-mais">Salvar</button>
                </>
              ) : (
                <>
                  <p><strong>Nome do Responsável:</strong> {e.responsavelNome || "Não informado"}</p>
                  <p><strong>Telefone do Responsável:</strong> {e.responsavelTelefone || "Não informado"}</p>
                </>
              )}

              <button onClick={() => toggleModoEdicao(e)} className="botao-ver-mais">
                {isEditando ? "Cancelar" : "Editar"}
              </button>
              <button onClick={() => apagarEncontreiro(e.cpf)} className="botao-ver-mais">Apagar</button>
            </div>
          )}
        </div>
      </div>
    );
  };

  return (
    <div className="pagina-inicial">
      <div style={{ paddingTop: '30px', paddingLeft: '20px' }}>
        <Link to={`/pagina-inicial-admin/${cpf}`}>
          <button className="botao-sair">Voltar</button>
        </Link>
      </div>

      <div className="botoes-navegacao" style={{ display: 'flex', justifyContent: 'center', marginTop: '30px', gap: '20px' }}>
        <button className="botao-eac ativo">Encontreiros Inscritos</button>
        <Link to={`/encontristas-inscritos/${cpf}/${ano}`}>
          <button className="botao-eac">Encontristas Inscritos</button>
        </Link>
        <Link to={`/gerar-equipes/${cpf}/${ano}`}>
          <button className="botao-eac">Gerar Equipes</button>
        </Link>
      </div>

      <h2>ENCONTREIROS INSCRITOS</h2>
      <div className="conteudo-central" style={{ flexDirection: 'row', justifyContent: 'space-evenly', marginTop: '40px' }}>
        <div className="coluna-inscritos">
          <h3>ADOLESCENTES</h3>
          <div className="caixa-inscritos">
            {adolescentes.length === 0 ? (
              <p>Sem inscritos</p>
            ) : (
              adolescentes.map((e, i) => renderEncontreiro(e, i + 1))
            )}
          </div>
        </div>

        <div className="coluna-inscritos">
          <h3>JOVENS</h3>
          <div className="caixa-inscritos">
            {jovens.length === 0 ? (
              <p>Sem inscritos</p>
            ) : (
              jovens.map((e, i) => renderEncontreiro(e, i + 1))
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default EncontroAtual;

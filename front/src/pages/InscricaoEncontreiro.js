import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import '../styles/InscricaoEncontreiro.css';

const InscricaoEncontreiro = () => {
  const { ano, cpf } = useParams();

  const [habilidades, setHabilidades] = useState([]);
  const [habilidadesSelecionadas, setHabilidadesSelecionadas] = useState([]);
  const [nomeResponsavel, setNomeResponsavel] = useState("");
  const [telefoneResponsavel, setTelefoneResponsavel] = useState("");
  const [fezEjc, setFezEjc] = useState(false);
  const [adolescente, setAdolescente] = useState(false);
  const [temFamiliar, setTemFamiliar] = useState(false);
  const [familiares, setFamiliares] = useState([]);
  const [observacao, setObservacao] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/habilidades")
      .then(response => {
        if (!response.ok) throw new Error("Erro ao buscar habilidades");
        return response.json();
      })
      .then(data => setHabilidades(data))
      .catch(error => console.error("Erro:", error));
  }, []);

  const handleHabilidadeChange = (id) => {
    setHabilidadesSelecionadas(prev =>
      prev.includes(id) ? prev.filter(h => h !== id) : [...prev, id]
    );
  };

  const adicionarFamiliar = () => {
    setFamiliares([...familiares, { cpf: "", parentesco: "", trabalharJunto: "sim" }]);
  };

  const removerFamiliar = (index) => {
    const novosFamiliares = [...familiares];
    novosFamiliares.splice(index, 1);
    setFamiliares(novosFamiliares);
  };

  const handleFamiliarChange = (index, campo, valor) => {
    const novosFamiliares = [...familiares];
    novosFamiliares[index][campo] = valor;
    setFamiliares(novosFamiliares);
  };

    const validarCampos = () => {
        if (!nomeResponsavel.trim()) return false;
        if (!telefoneResponsavel.trim()) return false;
        if (habilidadesSelecionadas.length === 0) return false;

        if (temFamiliar) {
            for (const familiar of familiares) {
            if (!familiar.cpf.trim() || !familiar.parentesco.trim()) {
                return false;
            }
            }
        }

        return true;
    };


  const handleSubmit = () => {
    if (!validarCampos()) {
      alert("Por favor, preencha todos os campos obrigatórios.");
      return;
    }

    const encontreiro = {
      cpf: cpf,
      fezEjc: fezEjc,
      responsavelNome: nomeResponsavel || null,
      responsavelTelefone: telefoneResponsavel || null,
      habilidades: habilidadesSelecionadas,
      familiares: temFamiliar ? familiares : [],
      observacao: observacao || null,
      ano: ano
    };

    const responsavel = {
      telefone: telefoneResponsavel,
      nome: nomeResponsavel
    };

    fetch("http://localhost:8080/responsavel", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(responsavel)
    })
      .then(response => {
        if (!response.ok) throw new Error("Erro ao salvar responsável");
        return response.json();
      })
      .then(() => {
        return fetch("http://localhost:8080/encontreiros", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(encontreiro)
        });
      })
      .then(response => {
        if (!response.ok) throw new Error("Erro ao salvar inscrição");
        return response.json();
      })
      .then(() => {
        alert("✅ Inscrição realizada com sucesso!");
        // Limpa formulário
        setNomeResponsavel("");
        setTelefoneResponsavel("");
        setFezEjc(false);
        setAdolescente(false);
        setHabilidadesSelecionadas([]);
        setTemFamiliar(false);
        setFamiliares([]);
        setObservacao("");
      })
      .catch(error => {
        console.error("Erro:", error);
        alert("❌ Erro ao realizar inscrição.");
      });
  };

  return (
    <div className="inscricao-container">
      <div className="logo-circular">EAC<br />{ano}</div>

      <div className="conteudo-inscricao">
        <div className="habilidades-box">
          <h2>Habilidades</h2>
          <div className="habilidades-grid">
            {habilidades.length > 0 ? (
              habilidades.map(habilidade => (
                <label key={habilidade.id}>
                  <input 
                    type="checkbox"
                    value={habilidade.id}
                    checked={habilidadesSelecionadas.includes(habilidade.id)}
                    onChange={() => handleHabilidadeChange(habilidade.id)}
                  /> 
                  {habilidade.habilidade}
                </label>
              ))
            ) : (
              <p>Carregando habilidades...</p>
            )}
          </div>
        </div>

        <div className="formulario">
          <input 
            type="text" 
            placeholder="Nome do Responsável" 
            value={nomeResponsavel}
            onChange={e => setNomeResponsavel(e.target.value)}
          />
          <input 
            type="text" 
            placeholder="Telefone do Responsável" 
            value={telefoneResponsavel}
            onChange={e => setTelefoneResponsavel(e.target.value)}
          />

          <div className="idade-checkboxes">
            <label>
                <input 
                type="radio"
                name="idade"
                checked={adolescente === true}
                onChange={() => {
                    setAdolescente(true);
                    setFezEjc(false);
                }}
                /> 
                Adolescente
            </label>
            <label>
                <input 
                type="radio"
                name="idade"
                checked={fezEjc === true}
                onChange={() => {
                    setFezEjc(true);
                    setAdolescente(false);
                }}
                /> 
                Jovem
            </label>
            <p className="nota">*Marque Jovem se você tem +18 anos e já fez o EJC.</p>
        </div>


          <div className="familia-box">
            <p>Você tem algum familiar/namorado(a) que vai trabalhar?</p>
            <label>
              <input 
                type="radio"
                checked={temFamiliar === true}
                onChange={() => setTemFamiliar(true)}
              />
              Sim
            </label>
            <label>
              <input 
                type="radio"
                checked={temFamiliar === false}
                onChange={() => setTemFamiliar(false)}
              />
              Não
            </label>

            {temFamiliar && (
              <div className="familiares-lista">
                {familiares.map((familiar, index) => (
                  <div key={index} className="familiar-item">
                    <input 
                    type="text" 
                    placeholder="CPF da pessoa" 
                    value={familiar.cpf} 
                    onChange={e => {
                        const valorNumerico = e.target.value.replace(/\D/g, ''); // só números
                        handleFamiliarChange(index, "cpf", valorNumerico);
                    }}
                    inputMode="numeric"   // para celular mostrar teclado numérico
                    pattern="\d*"         // só dígitos
                    maxLength={11}        // máximo 11 números do CPF
                    />

                    <input 
                      type="text" 
                      placeholder="Parentesco"
                      value={familiar.parentesco}
                      onChange={e => handleFamiliarChange(index, "parentesco", e.target.value)}
                    />
                    <select
                      value={familiar.trabalharJunto}
                      onChange={e => handleFamiliarChange(index, "trabalharJunto", e.target.value)}
                    >
                      <option value="sim">Quer trabalhar junto</option>
                      <option value="nao">Não precisa trabalhar junto</option>
                    </select>
                    
                    <button 
                      type="button"
                      onClick={() => removerFamiliar(index)}
                      className="remover-btn"
                    >
                      ❌
                    </button>
                  </div>
                ))}
                <button type="button" onClick={adicionarFamiliar} className="adicionar-btn">
                  ➕ Adicionar mais
                </button>
              </div>
            )}
          </div>

          <textarea 
            placeholder="Alguma Observação?" 
            value={observacao}
            onChange={e => setObservacao(e.target.value)}
          ></textarea>
        </div>
      </div>

      <div className="botao-inscricao">
        <button onClick={handleSubmit}>Me inscrever</button>
      </div>
    </div>
  );
};

export default InscricaoEncontreiro;

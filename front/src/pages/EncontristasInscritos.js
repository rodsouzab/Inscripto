import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import '../styles/EncontristasInscritos.css';

const EncontristasInscritos = () => {
  const [encontristas, setEncontristas] = useState([]);
  const [pessoasMapeadas, setPessoasMapeadas] = useState({});
  const [expandedCpf, setExpandedCpf] = useState(null);
  const { cpf, ano } = useParams();

  useEffect(() => {
    fetchDados();
  }, []);

  const fetchDados = () => {
    Promise.all([
      fetch("http://localhost:8080/encontristas").then(res => res.json()),
      fetch("http://localhost:8080/pessoas").then(res => res.json())
    ])
      .then(([encontristasData, pessoasData]) => {
        const mapa = {};
        pessoasData.forEach(p => { mapa[p.cpf] = p; });
        setPessoasMapeadas(mapa);
        setEncontristas(encontristasData);
      })
      .catch(error => console.error("Erro ao buscar dados:", error));
  };

  const toggleExpand = (cpf) => {
    setExpandedCpf(expandedCpf === cpf ? null : cpf);
  };

  const apagarEncontrista = async (cpf) => {
    if (!window.confirm("Tem certeza que deseja apagar este encontrista?")) return;

    try {
      const res = await fetch(`http://localhost:8080/encontristas/${cpf}`, { method: "DELETE" });
      if (!res.ok) throw new Error("Erro ao apagar encontrista");

      fetchDados();
    } catch (err) {
      console.error(err);
      alert("Erro ao apagar encontrista");
    }
  };

  const renderEncontrista = (e, index) => {
    const pessoa = pessoasMapeadas[e.cpf];
    const isExpanded = expandedCpf === e.cpf;

    return (
      <div key={index} className="item-encontro" style={{ position: "relative" }}>
        <img
          src={pessoa?.foto_url || "/default-foto.png"}
          alt="Foto de perfil"
          className="foto-perfil"
        />
        <p><strong>{index + 1}.</strong></p>
        <p><strong>CPF:</strong> {e.cpf}</p>
        <p><strong>Nome:</strong> {pessoa?.nome || "Nome não encontrado"}</p>
        <p><strong>Apelido:</strong> {pessoa?.apelido || "Apelido não encontrado"}</p>
        <p><strong>Telefone:</strong> {pessoa?.telefone || "Telefone não encontrado"}</p>
        <p><strong>Pais separados:</strong> {pessoa?.paisSeparados ? "Sim" : "Não"}</p>

        <div className="container-botoes">

          <button onClick={() => toggleExpand(e.cpf)} className="botao-ver-mais">
            {isExpanded ? "Ver menos" : "Ver mais"}
          </button>
          <button className="botao-ver-mais" onClick={() => apagarEncontrista(e.cpf)}>
            Apagar
          </button>
        </div>

        {isExpanded && (
          <div className="infos-adicionais" style={{ marginTop: '10px' }}>
            <p><strong>Data de Nascimento:</strong> {pessoa?.data_nascimento || "Não informado"}</p>
            <p><strong>Bairro:</strong> {pessoa?.bairro || "Não informado"}</p>
            <p><strong>Complemento:</strong> {pessoa?.complemento || "Não informado"}</p>
            <p><strong>Número:</strong> {pessoa?.numero || "Não informado"}</p>
            <p><strong>Rua:</strong> {pessoa?.rua || "Não informado"}</p>
            <p><strong>CEP:</strong> {pessoa?.cep || "Não informado"}</p>
            <p><strong>Instituição de Ensino:</strong> {pessoa?.instituicao_ensino || "Não informado"}</p>
          </div>
        )}
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
        <Link to={`/encontro-atual/${cpf}/${ano}`}>
          <button className="botao-eac">Encontreiros Inscritos</button>
        </Link>
        <button className="botao-eac ativo">Encontristas Inscritos</button>
        <Link to={`/gerar-equipes/${cpf}/${ano}`}>
          <button className="botao-eac">Gerar Equipes</button>
        </Link>
      </div>

      <h2>ENCONTRISTAS INSCRITOS</h2>
      <div
        className="conteudo-central"
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          marginTop: "10px",
          gap: "20px",
          flexDirection: "row"
        }}
      >
        <div className="coluna-inscritos" style={{ width: '600px' }}>
          {encontristas.length === 0 ? (
            <p>Não há encontristas inscritos.</p>
          ) : (
            encontristas.map((e, index) => renderEncontrista(e, index))
          )}
        </div>
      </div>
    </div>
  );
};

export default EncontristasInscritos;

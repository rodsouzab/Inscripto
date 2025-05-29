import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import '../styles/EncontroAtual.css';

const EncontroAtual = () => {
  const [encontreiros, setEncontreiros] = useState([]);
  const [pessoasMapeadas, setPessoasMapeadas] = useState({});
  const [expandedCpf, setExpandedCpf] = useState(null);
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

  const renderEncontreiro = (e, numero) => {
    const pessoa = pessoasMapeadas[e.cpf];
    const isExpanded = expandedCpf === e.cpf;

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
              <p><strong>Nome do Responsável:</strong> {e.responsavelNome || "Não informado"}</p>
              <p><strong>Telefone do Responsável:</strong> {e.responsavelTelefone || "Não informado"}</p>
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

import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import '../styles/EncontroAtual.css';

const EncontroAtual = () => {
  const [encontreiros, setEncontreiros] = useState([]);
  const [pessoas, setPessoas] = useState([]);
  const [expandedCpf, setExpandedCpf] = useState(null); // Estado para expandir infos extras

  useEffect(() => {
    // Buscar encontreiro + pessoa
    Promise.all([
      fetch("http://localhost:8080/encontreiros").then((res) => res.json()),
      fetch("http://localhost:8080/pessoas").then((res) => res.json())
    ])
    .then(([encontreirosData, pessoasData]) => {
      setEncontreiros(encontreirosData);
      setPessoas(pessoasData);
    })
    .catch((error) => console.error("Erro ao buscar dados:", error));
  }, []);

  const encontrarPessoa = (cpf) => pessoas.find(p => p.cpf === cpf);

  const adolescentes = encontreiros.filter(e => !e.fezEjc);
  const jovens = encontreiros.filter(e => e.fezEjc);

  const toggleExpand = (cpf) => {
    if (expandedCpf === cpf) {
      setExpandedCpf(null);
    } else {
      setExpandedCpf(cpf);
    }
  };

  const renderEncontreiro = (e, index) => {
    const pessoa = encontrarPessoa(e.cpf);
    const isExpanded = expandedCpf === e.cpf;

    return (
      <div key={index} className="item-encontro">
        <img
          src={pessoa?.foto_url || "/default-foto.png"}
          alt="Foto de perfil"
          className="foto-perfil"
        />
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
    );
  };

  return (
    <div className="pagina-inicial">
      <Link to="/pagina-inicial-admin/:cpf">
        <button className="botao-sair">Voltar</button>
      </Link>

      <div className="botoes-navegacao" style={{ display: 'flex', justifyContent: 'center', marginTop: '30px', gap: '20px' }}>
        <button className="botao-eac ativo">Encontreiros Inscritos</button>
        <button className="botao-eac">Encontristas Inscritos</button>
        <button className="botao-eac">Gerar Equipes</button>
      </div>

      <h2>ENCONTREIROS INSCRITOS</h2>
      <div className="conteudo-central" style={{ flexDirection: 'row', justifyContent: 'space-evenly', marginTop: '40px' }}>
        <div className="coluna-inscritos">
          <h2>ADOLESCENTES</h2>
          <div className="caixa-inscritos">
            {adolescentes.length === 0 ? (
              <p>Sem inscritos</p>
            ) : (
              adolescentes.map(renderEncontreiro)
            )}
          </div>
        </div>

        <div className="coluna-inscritos">
          <h2>JOVENS</h2>
          <div className="caixa-inscritos">
            {jovens.length === 0 ? (
              <p>Sem inscritos</p>
            ) : (
              jovens.map(renderEncontreiro)
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default EncontroAtual;

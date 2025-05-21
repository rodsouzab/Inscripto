import React from 'react';
import { Link, useParams } from 'react-router-dom';
import '../styles/VerificacaoDadosEncontreiro.css';

const VerificacaoDadosEncontreiro = ({
  userData = {
    nome: "NOME",
    sobrenome: "sobrenome",
    cpf: "XXX.XXX.XXX-XX",
    endereco: "XXXXXXXXX",
    dataNascimento: "XX/XX/XXXX",
    telefone: "(XX) XXXXX-XXXX",
    instituicao: "XXXXXXXX",
    restricoes: "XXXXXXX",
  },
  encontros = [
    { ano: "2024", tipo: "REGULAR - JOVEM - EVANGELISTAS" },
    { ano: "2023", tipo: "COORD - ADOLESCENTE - OLIVEIRAS" },
  ],
  onEdit = () => {},
  onRegisterEncontro = () => {},
  onContinuar = () => {},
  onVoltar = () => {},
}) => {
  return (
    <div className="verificacao-container">
      <div className="logo">LOGO</div>

      <h1 className="titulo">VERIFICAR DADOS DO PERFIL</h1>

      <div className="conteudo">
        <div className="perfil-container">
          <div className="foto-perfil">
            <div className="foto-texto">FOTO DE PERFIL</div>
          </div>

          <div className="nome-container">
            <div className="nome">{userData.nome}</div>
            <div className="sobrenome">{userData.sobrenome}</div>
          </div>

          <div className="dados-pessoais">
            <div>CPF: {userData.cpf}</div>
            <div>ENDEREÇO: {userData.endereco}</div>
            <div>DATA DE NASCIMENTO: {userData.dataNascimento}</div>
            <div>TELEFONE: {userData.telefone}</div>
            <div>INSTITUIÇÃO DE ENSINO: {userData.instituicao}</div>
            <div>RESTRIÇÕES: {userData.restricoes}</div>
          </div>

          <button className="editar-btn" onClick={onEdit}>
            EDITAR
          </button>
        </div>

        <div className="encontros-container">
          <h2 className="encontros-titulo">ÚLTIMOS ENCONTROS</h2>

          <button className="registrar-btn" onClick={onRegisterEncontro}>
            Registrar Encontro
          </button>

          <div className="encontros-lista">
            {encontros.map((encontro, index) => (
              <div className="encontro-item" key={index}>
                <div className="encontro-ano">{encontro.ano}</div>
                <div className="encontro-tipo">{encontro.tipo}</div>
              </div>
            ))}
          </div>
        </div>
      </div>

      <div className="continuar-container">
        <h2 className="continuar-titulo">Continuar Inscrição?</h2>

        <div className="botoes-container">
          <button className="voltar-btn" onClick={onVoltar}>
            Voltar
          </button>
          <button className="continuar-btn" onClick={onContinuar}>
            Continuar
          </button>
        </div>
      </div>
    </div>
  )
}

export default VerificacaoDadosEncontreiro

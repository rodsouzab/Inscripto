import React from "react";
import { useParams } from "react-router-dom";
import '../styles/InscricaoEncontreiro.css';

const InscricaoEncontreiro = () => {
  const { ano } = useParams();

  return (
    <div className="inscricao-container">
      <div className="logo-circular">EAC<br />{ano}</div>

      <div className="conteudo-inscricao">
        <div className="habilidades-box">
          <h2>Habilidades</h2>
          <div className="habilidades-grid">
            <label><input type="checkbox" /> ...</label>
            <label><input type="checkbox" /> ...</label>
            <label><input type="checkbox" /> ...</label>
            <label><input type="checkbox" /> ...</label>
            <label><input type="checkbox" /> ...</label>
            <label><input type="checkbox" /> ...</label>
            <label><input type="checkbox" /> ...</label>
            <label><input type="checkbox" /> ...</label>
          </div>
        </div>

        <div className="formulario">
          <input type="text" placeholder="Nome do Responsável" />
          <input type="text" placeholder="Telefone do Responsável" />

          <div className="idade-checkboxes">
            <label><input type="checkbox" /> Adolescente</label>
            <label><input type="checkbox" /> Jovem</label>
            <p className="nota">*Tenho +18 anos e já fiz o EJC</p>
          </div>

          <textarea placeholder="Você tem algum familiar que vai trabalhar? Se sim, você deseja trabalhar com ele(s)?"></textarea>
          <textarea placeholder="Alguma Observação?"></textarea>
        </div>
      </div>

      <div className="botao-inscricao">
        <button>Me inscrever</button>
      </div>
    </div>
  );
};

export default InscricaoEncontreiro;

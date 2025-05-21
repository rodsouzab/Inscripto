import React from 'react';
import { Link, useParams } from 'react-router-dom';
import '../styles/Encontro.css';

function Encontro() {
  const { cpf,ano } = useParams();

  return (
    <div className="container-encontro">
      <Link to={`/pagina-inicial/${cpf}`} className="botao-voltar">
        Voltar
      </Link>

      <div className="botoes-encontro">
        <div className="botao-box">
          <Link to={`/verificacao-dados-encontreiro/${cpf}/${ano}`} className="botao-encontro">
            SOU ENCONTREIRO
          </Link>
          <p className="descricao">*Pretendo trabalhar no encontro</p>
        </div>

        <div className="botao-box">
          <Link to={`/verificacao-dados-encontrista/${cpf}/${ano}`} className="botao-encontro">
            SOU ENCONTRISTA
          </Link>
          <p className="descricao">*Pretendo fazer o encontro</p>
        </div>
      </div>
    </div>
  );
}

export default Encontro;

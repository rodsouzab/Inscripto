import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import '../styles/Encontro.css';

function Encontro() {
  const { cpf, ano } = useParams();
  const [cpfValido, setCpfValido] = useState(null); // null = carregando, true = encontrado, false = não encontrado
  const [erro, setErro] = useState(null);

  useEffect(() => {
    async function verificarCpf() {
      try {
        const [resEncontreiro, resEncontrista] = await Promise.all([
          fetch('http://localhost:8080/encontreiros'),
          fetch('http://localhost:8080/encontristas')
        ]);

        if (!resEncontreiro.ok || !resEncontrista.ok) {
          throw new Error('Erro ao buscar dados');
        }

        const [dadosEncontreiro, dadosEncontrista] = await Promise.all([
          resEncontreiro.json(),
          resEncontrista.json()
        ]);

        const cpfExiste = dadosEncontreiro.some(e => e.cpf === cpf) ||
                          dadosEncontrista.some(e => e.cpf === cpf);

        setCpfValido(cpfExiste);
      } catch (error) {
        console.error(error);
        setErro('Erro ao conectar com o servidor');
        setCpfValido(false);
      }
    }

    verificarCpf();
  }, [cpf]);

  if (cpfValido === null) {
    return <div className="container-encontro">Carregando...</div>;
  }

  if (erro) {
    return (
      <div className="container-encontro">
        <p>{erro}</p>
        <Link to={`/pagina-inicial/${cpf}`} className="botao-voltar">Voltar</Link>
      </div>
    );
  }

  return (
    <div className="container-encontro">
      <Link to={`/pagina-inicial/${cpf}`} className="botao-voltar">
        Voltar
      </Link>

      {cpfValido ? (
        <div className="mensagem-inscricao">
          <span role="img" aria-label="smile" className="emoji">😊</span>
          <div>
            <p>Você já está inscrito no EAC {ano}</p>
            <p className="subtexto">Em breve, você receberá mais notícias!</p>
          </div>
        </div>
      ) : (
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
      )}
    </div>
  );
}

export default Encontro;

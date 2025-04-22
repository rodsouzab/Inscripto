import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import '../styles/PaginaInicial.css';

function PaginaInicial() {
  const { cpf } = useParams();
  const [apelido, setApelido] = useState('');
  const [fotoUrl, setFotoUrl] = useState('');

  useEffect(() => {
    const buscarPessoa = async () => {
      try {
        const response = await fetch(`http://localhost:8080/pessoa/${cpf}`);
        if (response.ok) {
          const data = await response.json();
          setApelido(data.apelido);
          setFotoUrl(data.foto_url);
        } else {
          setApelido('usuário');
        }
      } catch (error) {
        console.error('Erro ao buscar dados da pessoa:', error);
        setApelido('usuário');
      }
    };

    buscarPessoa();
  }, [cpf]);

  return (
    <div className="pagina-inicial">
      <div className="top-bar">
        <Link to="/">
          <button className="botao-sair">Sair da Conta</button>
        </Link>
        <Link to={`/perfil/${cpf}`}>
          <button className="botao-perfil">
            {fotoUrl ? (
              <img
                src={fotoUrl}
                alt="Foto de perfil"
                style={{ width: '60px', height: '60px', borderRadius: '50%' }}
              />
            ) : (
              'FOTO\nDE\nPERFIL'
            )}
          </button>
        </Link>
      </div>

      <div className="conteudo-central">
        <p className="mensagem-boas-vindas">Bem-vindo(a), {apelido}!</p>
        <h1 className="titulo-encontro">ENCONTRO</h1>
        <button className="botao-eac">EAC 2025</button>
      </div>
    </div>
  );
}

export default PaginaInicial;

import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import '../styles/PaginaInicial.css';

function PaginaInicial() {
  const { cpf } = useParams();
  const [apelido, setApelido] = useState('');
  const [fotoUrl, setFotoUrl] = useState('');
  const [ultimoEncontro, setUltimoEncontro] = useState(null);

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

    // Buscar o último encontro
    const buscarUltimoEncontro = async () => {
      try {
        const response = await fetch('http://localhost:8080/encontros');
        if (response.ok) {
          const data = await response.json();
          // Supondo que os encontros vêm em ordem cronológica, pegamos o primeiro (último criado)
          if (data && data.content && data.content.length > 0) {
            setUltimoEncontro(data.content[0]);
          }
        }
      } catch (error) {
        console.error('Erro ao buscar últimos encontros:', error);
      }
    };

    buscarUltimoEncontro();
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
        <h1 className="titulo-encontro">Encontro</h1>
        {ultimoEncontro ? (
          <div>
            <h2>Encontro {ultimoEncontro.ano}: {ultimoEncontro.tema}</h2>
            <p><strong>Data:</strong> {ultimoEncontro.data?.split("T")[0]}</p>
          </div>
        ) : (
          <p>Aguardando próximo encontro...</p>
        )}
      </div>
    </div>
  );
}

export default PaginaInicial;

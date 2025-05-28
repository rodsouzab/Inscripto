import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import '../styles/PaginaInicial.css';

function PaginaInicial() {
  const { cpf } = useParams();
  const [apelido, setApelido] = useState('');
  const [fotoUrl, setFotoUrl] = useState('');
  const [ultimoEncontro, setUltimoEncontro] = useState(null);
  const [encontroDisponivel, setEncontroDisponivel] = useState(true);

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

    const buscarUltimoEncontro = async () => {
      try {
        const response = await fetch('http://localhost:8080/encontros');
        if (response.ok) {
          const data = await response.json();

          if (data && data.length > 0) {
            const hoje = new Date();
            const anoAtual = hoje.getFullYear();

            // Filtra os encontros do ano atual ou futuros
            const encontrosValidos = data.filter((encontro) => {
              const dataEncontro = new Date(encontro.data);
              return dataEncontro.getFullYear() >= anoAtual;
            });

            if (encontrosValidos.length > 0) {
              // Ordena por data decrescente (mais recente primeiro)
              const encontrosOrdenados = encontrosValidos.sort(
                (a, b) => new Date(b.data) - new Date(a.data)
              );

              const encontroMaisRecente = encontrosOrdenados[0];

              setUltimoEncontro(encontroMaisRecente);
              setEncontroDisponivel(true);
            } else {
              setUltimoEncontro(null);
              setEncontroDisponivel(false);
            }
          } else {
            setUltimoEncontro(null);
            setEncontroDisponivel(false);
          }
        }
      } catch (error) {
        console.error('Erro ao buscar últimos encontros:', error);
        setUltimoEncontro(null);
        setEncontroDisponivel(false);
      }
    };

    buscarPessoa();
    buscarUltimoEncontro();
  }, [cpf]);

  return (
    <>
      <div className="top-bar">
        <Link to="/">
          <button className="botao-sair">Sair da Conta</button>
        </Link>
        <Link to={`/perfil/${cpf}`} className="profile-link">
          {fotoUrl ? (
            <img src={fotoUrl} alt="Foto de perfil" className="profile-image" />
          ) : (
            <div className="profile-placeholder">
              FOTO<br />DE<br />PERFIL
            </div>
          )}
        </Link>
      </div>

      <div className="pagina-inicial">
        <div className="conteudo-central">
          <p className="mensagem-boas-vindas">Bem-vindo(a), {apelido}!</p>

          <h1 className="titulo-encontro">
            {encontroDisponivel
              ? 'Encontro Atual:'
              : 'O encontro desse ano já aconteceu. Ano que vem tem mais 😊'}
          </h1>

          {encontroDisponivel && ultimoEncontro && (
            <div className="info-encontro">
              <h2>
                Encontro {ultimoEncontro.ano}: {ultimoEncontro.tema}
              </h2>
              <p>
                <strong>Data:</strong> {ultimoEncontro.data?.split('T')[0]}
              </p>

              <Link to={`/encontro/${cpf}/${ultimoEncontro.ano}`}>
                <button className="botao-encontro">Quero me Inscrever</button>
              </Link>
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default PaginaInicial;

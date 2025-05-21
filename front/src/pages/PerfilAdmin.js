import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import '../styles/Perfil.css';

function PerfilAdmin() {
  const { cpf } = useParams();
  const [nome, setNome] = useState('');
  const [apelido, setApelido] = useState('');
  const [data_nascimento, setData_nascimento] = useState('');
  const [telefone, setTelefone] = useState('');
  const [foto_url, setFoto_url] = useState('');
  const [bairro, setBairro] = useState('');
  const [complemento, setComplemento] = useState('');
  const [numero, setNumero] = useState('');
  const [rua, setRua] = useState('');
  const [cep, setCep] = useState('');
  const [instituicao_ensino, setInstituicao_ensino] = useState('');
  const [senha, setSenha] = useState('');

  const [modoEdicao, setModoEdicao] = useState(false);

  const deletarPessoa = async () => {
    const confirmacao = window.confirm("Tem certeza que deseja apagar este perfil? Essa ação não poderá ser desfeita.");
    if (confirmacao) {
      try {
        const response = await fetch(`http://localhost:8080/pessoa/${cpf}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          alert("Pessoa apagada com sucesso.");
          window.location.href = '/'; // redireciona para a página de login
        } else {
          alert("Erro ao apagar a pessoa.");
        }
      } catch (err) {
        console.error(err);
        alert("Erro ao apagar a pessoa.");
      }
    }
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const imageURL = URL.createObjectURL(file);
      setFoto_url(imageURL);
    }
  };

  useEffect(() => {
    const buscarPessoa = async () => {
      try {
        const response = await fetch(`http://localhost:8080/pessoa/${cpf}`);
        if (response.ok) {
          const data = await response.json();
          setNome(data.nome);
          setApelido(data.apelido);
          setData_nascimento(data.data_nascimento);
          setTelefone(data.telefone);
          setFoto_url(data.foto_url);
          setBairro(data.bairro);
          setComplemento(data.complemento);
          setNumero(data.numero);
          setRua(data.rua);
          setCep(data.cep);
          setInstituicao_ensino(data.instituicao_ensino);
          setSenha(data.senha);
        }
      } catch (error) {
        console.error('Erro ao buscar os dados da pessoa:', error);
      }
    };
    buscarPessoa();
  }, [cpf]);

  const salvarEdicao = async () => {
    try {
      const response = await fetch(`http://localhost:8080/pessoa/${cpf}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          cpf,
          nome,
          apelido,
          data_nascimento,
          telefone,
          foto_url,
          bairro,
          complemento,
          numero,
          rua,
          cep,
          instituicao_ensino,
          senha,
        }),
      });

      if (response.ok) {
        alert('Dados atualizados com sucesso!');
        setModoEdicao(false);
      } else {
        alert('Erro ao atualizar os dados.');
      }
    } catch (err) {
      console.error(err);
      alert('Erro ao atualizar os dados.');
    }
  };

  return (
    <div className="perfil-container">
      <Link to={`/pagina-inicial-admin/${cpf}`}>
        <button className="botao-sair">Voltar para Página Inicial</button>
      </Link>

      <h1 className="titulo">Perfil</h1>

      <div className="perfil-card">
        <div className="foto-perfil">
          {modoEdicao ? (
            <>
              <input
                type="file"
                accept="image/*"
                onChange={handleImageChange}
                style={{ marginBottom: '10px' }}
              />
              {foto_url && (
                <div style={{ marginTop: '10px' }}>
                  <img
                    src={foto_url}
                    alt="Pré-visualização"
                    style={{ width: '100px', height: '100px', borderRadius: '50%', objectFit: 'cover' }}
                  />
                </div>
              )}
            </>
          ) : (
            foto_url ? (
              <img
                src={foto_url}
                alt="Foto de Perfil"
                style={{ width: '100px', height: '100px', borderRadius: '50%', objectFit: 'cover' }}
              />
            ) : (
              <div className="profile-placeholder">
                FOTO<br />DE<br />PERFIL
              </div>
            )
          )}
        </div>

        <div className="dados-perfil">
          {modoEdicao ? (
            <>
              <label>Nome</label>
              <input
                type="text"
                value={nome}
                onChange={(e) => setNome(e.target.value)}
              />
              <label>Apelido</label>
              <input
                type="text"
                value={apelido}
                onChange={(e) => setApelido(e.target.value)}
              />
              <label>Data de Nascimento</label>
              <input
                type="date"
                value={data_nascimento}
                onChange={(e) => setData_nascimento(e.target.value)}
              />
              <label>Telefone</label>
              <input
                type="text"
                value={telefone}
                onChange={(e) => setTelefone(e.target.value)}
              />
              <label>Bairro</label>
              <input
                type="text"
                value={bairro}
                onChange={(e) => setBairro(e.target.value)}
              />
              <label>Complemento</label>
              <input
                type="text"
                value={complemento}
                onChange={(e) => setComplemento(e.target.value)}
              />
              <label>Número</label>
              <input
                type="number"
                value={numero}
                onChange={(e) => setNumero(e.target.value)}
              />
              <label>Rua</label>
              <input
                type="text"
                value={rua}
                onChange={(e) => setRua(e.target.value)}
              />
              <label>CEP</label>
              <input
                type="text"
                value={cep}
                onChange={(e) => setCep(e.target.value)}
              />
              <label>Instituição de Ensino</label>
              <input
                type="text"
                value={instituicao_ensino}
                onChange={(e) => setInstituicao_ensino(e.target.value)}
              />
              <label>Senha</label>
              <input
                type="password"
                value={senha}
                onChange={(e) => setSenha(e.target.value)}
              />
            </>
          ) : (
            <>
              <p><strong>Nome:</strong> {nome}</p>
              <p><strong>Apelido:</strong> {apelido}</p>
              <p><strong>Data de Nascimento:</strong> {data_nascimento}</p>
              <p><strong>Telefone:</strong> {telefone}</p>
              <p><strong>Bairro:</strong> {bairro}</p>
              <p><strong>Complemento:</strong> {complemento}</p>
              <p><strong>Número:</strong> {numero}</p>
              <p><strong>Rua:</strong> {rua}</p>
              <p><strong>CEP:</strong> {cep}</p>
              <p><strong>Instituição de Ensino:</strong> {instituicao_ensino}</p>
            </>
          )}
        </div>

<div className="acoes-perfil">
  {modoEdicao ? (
    <>
      <button className="botao-perfil" onClick={salvarEdicao}>Salvar</button>
      <button className="botao-perfil cancelar" onClick={() => setModoEdicao(false)}>Cancelar</button>
    </>
  ) : (
    <>
      <button className="botao-perfil" onClick={() => setModoEdicao(true)}>Editar</button>
      <button className="botao-perfil deletar" onClick={deletarPessoa}>Deletar Perfil</button>
    </>
  )}
</div>

      </div>
    </div>
  );
}

export default PerfilAdmin;

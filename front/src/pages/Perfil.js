import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import '../styles/Perfil.css';

function Perfil() {
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
      <Link to={`/pagina-inicial/${cpf}`}>
        <button className="botao-sair">Voltar para Página Inicial</button>
      </Link>

      <h1 className="titulo">Perfil</h1>

      <div className="perfil-card">
        <div className="foto-perfil">
          {modoEdicao ? (
            <input
              type="text"
              placeholder="URL da Foto"
              value={foto_url}
              onChange={(e) => setFoto_url(e.target.value)}
            />
          ) : foto_url ? (
            <img src={foto_url} alt="Foto de perfil" className="imagem-perfil" />
          ) : (
            <div>FOTO<br />DE<br />PERFIL</div>
          )}
        </div>

        <div className="dados-pessoais">
          {modoEdicao ? (
            <>
              <input value={nome} onChange={(e) => setNome(e.target.value)} />
              <input value={apelido} onChange={(e) => setApelido(e.target.value)} />
              <input value={telefone} onChange={(e) => setTelefone(e.target.value)} />
              <input value={data_nascimento} onChange={(e) => setData_nascimento(e.target.value)} />
              <input value={rua} onChange={(e) => setRua(e.target.value)} />
              <input value={numero} onChange={(e) => setNumero(e.target.value)} />
              <input value={complemento} onChange={(e) => setComplemento(e.target.value)} />
              <input value={bairro} onChange={(e) => setBairro(e.target.value)} />
              <input value={cep} onChange={(e) => setCep(e.target.value)} />
              <input value={instituicao_ensino} onChange={(e) => setInstituicao_ensino(e.target.value)} />
            </>
          ) : (
            <>
              <strong>{nome}</strong>
              <p className="apelido">{apelido}</p>
              <p>CPF: {cpf}</p>
              <p>ENDEREÇO: {rua}, {numero}, {complemento}, {bairro} - {cep}</p>
              <p>DATA DE NASCIMENTO: {data_nascimento}</p>
              <p>TELEFONE: {telefone}</p>
              <p>INSTITUIÇÃO DE ENSINO: {instituicao_ensino}</p>
            </>
          )}

          <p>RESTRIÇÕES:</p>

          {modoEdicao ? (
            <div className="botoes-edicao">
                <button className="botao-cancelar" onClick={salvarEdicao}>Salvar</button>
                <button className="botao-cancelar" onClick={() => setModoEdicao(false)}>Cancelar</button>
                <button className="botao-apagar" onClick={deletarPessoa}>Apagar</button>
            </div>
            ) : (
            <button className="botao-editar" onClick={() => setModoEdicao(true)}>Editar</button>
            )}

        </div>
      </div>

      <div className="ultimos-encontros">
        <h2>ÚLTIMOS ENCONTROS</h2>
        <div className="encontro">
          <span className="ano">2024</span>
          <span>REGULAR - JOVEM - EVANGELISTAS</span>
        </div>
        <div className="encontro">
          <span className="ano">2023</span>
          <span>COORD - ADOLESCENTE - OLIVEIRAS</span>
        </div>
      </div>
    </div>
  );
}

export default Perfil;

import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Cadastro.css';

function Cadastro() {

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const imageURL = URL.createObjectURL(file);
      setFormData((prev) => ({
        ...prev,
        foto_url: imageURL
      }));
    }
  };
  

  const [formData, setFormData] = useState({
    cpf: '',
    nome: '',
    apelido: '',
    data_nascimento: '',
    telefone: '',
    foto_url: '',
    bairro: '',
    complemento: '',
    numero: '',
    rua: '',
    cep: '',
    instituicao_ensino: '',
    senha: ''
  });

  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleCadastro = async (e) => {
    e.preventDefault();
  
    const camposObrigatorios = [
      'cpf', 'nome', 'apelido', 'data_nascimento', 'telefone', 'foto_url',
      'bairro', 'complemento', 'numero', 'rua', 'cep', 'instituicao_ensino', 'senha'
    ];
  
    for (let campo of camposObrigatorios) {
      if (!formData[campo]) {
        setMessage(`O campo ${campo} é obrigatório!`);
        return;
      }
    }
  
    // Adicionar um dia à data de nascimento
    const dataOriginal = new Date(formData.data_nascimento);
    const dataAjustada = new Date(dataOriginal);
    dataAjustada.setDate(dataOriginal.getDate() + 1);
  
    const formDataAjustado = {
      ...formData,
      data_nascimento: dataAjustada.toISOString().split('T')[0] // Formato YYYY-MM-DD
    };
  
    const response = await fetch('http://localhost:8080/pessoa', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formDataAjustado),
    });
  
    const data = await response.text();
    setMessage(data);
  
    if (data === "Pessoa cadastrada com sucesso!") {
      setTimeout(() => {
        navigate('/');
      }, 1500); // Redireciona após 1,5s
    }
  };

  return (
    <div className="container">
      

      <div className="login-box">
      <Link to="/" className="admin-button">Voltar</Link>
        <h2>Cadastro</h2>
        <form onSubmit={handleCadastro}>
          
        <label htmlFor="cpf">CPF</label>
          <input type="text" id="cpf" name="cpf" value={formData.cpf} onChange={handleChange} placeholder="Digite seu CPF" />

          <label htmlFor="nome">Nome</label>
          <input type="text" id="nome" name="nome" value={formData.nome} onChange={handleChange} placeholder="Digite seu nome completo" />

          <label htmlFor="apelido">Apelido</label>
          <input type="text" id="apelido" name="apelido" value={formData.apelido} onChange={handleChange} placeholder="Digite seu apelido" />

          <label htmlFor="data_nascimento">Data de Nascimento</label>
          <input type="date" id="data_nascimento" name="data_nascimento" value={formData.data_nascimento} onChange={handleChange} />

          <label htmlFor="telefone">Telefone</label>
          <input type="text" id="telefone" name="telefone" value={formData.telefone} onChange={handleChange} placeholder="(xx) xxxxx-xxxx" />

          <label htmlFor="foto_url">Foto de Perfil</label>
          <input
            type="file"
            id="foto_url"
            name="foto_url"
            accept="image/*"
            onChange={handleImageChange}
          />

          {formData.foto_url && (
            <div style={{ marginTop: '10px' }}>
              <img
                src={formData.foto_url}
                alt="Pré-visualização"
                style={{ width: '100px', height: '100px', borderRadius: '50%', objectFit: 'cover' }}
              />
            </div>
          )}


          <label htmlFor="bairro">Bairro</label>
          <input type="text" id="bairro" name="bairro" value={formData.bairro} onChange={handleChange} placeholder="Seu bairro" />

          <label htmlFor="complemento">Complemento</label>
          <input type="text" id="complemento" name="complemento" value={formData.complemento} onChange={handleChange} placeholder="Complemento" />

          <label htmlFor="numero">Número</label>
          <input type="number" id="numero" name="numero" value={formData.numero} onChange={handleChange} placeholder="Número da residência" />

          <label htmlFor="rua">Rua</label>
          <input type="text" id="rua" name="rua" value={formData.rua} onChange={handleChange} placeholder="Nome da rua" />

          <label htmlFor="cep">CEP</label>
          <input type="text" id="cep" name="cep" value={formData.cep} onChange={handleChange} placeholder="Digite o CEP" />

          <label htmlFor="instituicao_ensino">Instituição de Ensino</label>
          <input type="text" id="instituicao_ensino" name="instituicao_ensino" value={formData.instituicao_ensino} onChange={handleChange} placeholder="Nome da instituição" />

          <label htmlFor="senha">Senha</label>
          <input type="password" id="senha" name="senha" value={formData.senha} onChange={handleChange} placeholder="Crie uma senha" />

          <button type="submit" className="login-button">Cadastrar</button>
        </form>

        {message && <p>{message}</p>}

        <p className="register-link">
          Já tem conta? <a href="/">Faça login</a>
        </p>
      </div>
    </div>
  );
}

export default Cadastro;

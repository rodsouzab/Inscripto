import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/App.css';

function Login() {
  const [cpf, setCpf] = useState('');
  const [senha, setSenha] = useState('');
  const [message, setMessage] = useState('');

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
  
    if (!cpf || !senha) {
      setMessage('Por favor, preencha todos os campos!');
      return;
    }
  
    const response = await fetch('http://localhost:8080/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ cpf, senha }),
    });
  
    const data = await response.text();
    setMessage(data);
  
    if (data === 'Login bem-sucedido!') {
    setTimeout(() => {
      navigate(`/pagina-inicial/${cpf}`);
    }, 2000);
  }
};
  

  return (
    <div className="container">
      <Link to="/admin-login" className="admin-button">Login Admin</Link>

      <div className="login-box">
        <h2>Login Regular</h2>
        <form onSubmit={handleLogin}>
          <label htmlFor="cpf">CPF</label>
          <input
            type="text"
            id="cpf"
            name="cpf"
            placeholder="Digite seu CPF"
            value={cpf}
            onChange={(e) => setCpf(e.target.value)}
          />

          <label htmlFor="senha">Senha</label>
          <input
            type="password"
            id="senha"
            name="senha"
            placeholder="Digite sua senha"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
          />

          <button type="submit" className="login-button">Entrar</button>
        </form>

        {message && <p>{message}</p>}

        <p className="register-link">
          Ainda não tem conta? <a href="/cadastro">Registrar</a>
        </p>
      </div>
    </div>
  );
}

export default Login;

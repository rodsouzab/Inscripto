import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; // Atualizado para v6
import './App.css'; // Importando o arquivo CSS principal

function App() {

  const [cpf,setCpf] = useState('');
  const [senha,setSenha] = useState('');
  const [message, setMessage] = useState('');

  const handleLogin = async (e) => {

    e.preventDefault();

    const response = await fetch('http://localhost:8080/login', {

      method: 'POST',
      headers: {
        'Content-Type' : 'application/json',
      },

      body: JSON.stringify({cpf,senha}),

    });

    const data = await response.text();
    setMessage(data);

  };


  return (
    <div className="container">
      <a href="/admin-login" className="admin-button">Admin</a>

      <div className="login-box">
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
          <label htmlFor="cpf">CPF</label>
          <input type="text" id="cpf" name="cpf" placeholder="Digite seu CPF" value={cpf} onChange={(e) => setCpf(e.target.value)}/>

          <label htmlFor="senha">Senha</label>
          <input type="password" id="senha" name="senha" placeholder="Digite sua senha" value={senha} onChange={(e) => setSenha(e.target.value)}/>

          <button type="submit" className="login-button">Entrar</button>
        </form>

        {message && <p>{message}</p>}

        <p className="register-link">
          Ainda não tem conta? <a href="/registrar">Registrar</a>
        </p>
      </div>
    </div>
  );
}

export default App;
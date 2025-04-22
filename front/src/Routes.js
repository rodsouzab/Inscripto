import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import LoginAdmin from './pages/LoginAdmin';
import Cadastro from './pages/Cadastro';
import PaginaInicial from './pages/PaginaInicial';
import Perfil from './pages/Perfil';

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/admin-login" element={<LoginAdmin />} />
      <Route path="/cadastro" element={<Cadastro />} />
      <Route path="/pagina-inicial/:cpf" element={<PaginaInicial />} />
      <Route path="/perfil/:cpf" element={<Perfil />} />
    </Routes>
  );
};

export default AppRoutes;

import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import LoginAdmin from './pages/LoginAdmin';
import Cadastro from './pages/Cadastro';
import PaginaInicial from './pages/PaginaInicial';
import Perfil from './pages/Perfil';
import PaginaInicialAdmin from './pages/PaginaInicialAdmin';
import PerfilAdmin from './pages/PerfilAdmin';

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/admin-login" element={<LoginAdmin />} />
      <Route path="/cadastro" element={<Cadastro />} />
      <Route path="/pagina-inicial/:cpf" element={<PaginaInicial />} />
      <Route path="/perfil/:cpf" element={<Perfil />} />
      <Route path="/perfil-admin/:cpf" element={<PerfilAdmin />} />
      <Route path="/pagina-inicial-admin/:cpf" element={<PaginaInicialAdmin />} />
    </Routes>
  );
};

export default AppRoutes;

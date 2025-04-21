import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import LoginAdmin from './pages/LoginAdmin';
import Cadastro from './pages/Cadastro';

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/admin-login" element={<LoginAdmin />} />
      <Route path="/cadastro" element={<Cadastro />} />
    </Routes>
  );
};

export default AppRoutes;

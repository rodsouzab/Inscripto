import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import LoginAdmin from './pages/LoginAdmin';
import Cadastro from './pages/Cadastro';
import PaginaInicial from './pages/PaginaInicial';
import Perfil from './pages/Perfil';
import PaginaInicialAdmin from './pages/PaginaInicialAdmin';
import PerfilAdmin from './pages/PerfilAdmin';
import Encontro from './pages/Encontro';
import VerificacaoDadosEncontreiro from './pages/VerificacaoDadosEncontreiro';
import VerificacaoDadosEncontrista from './pages/VerificacaoDadosEncontrista';
import InscricaoEncontreiro from './pages/InscricaoEncontreiro';
import InscricaoEncontrista from './pages/InscricaoEnconstrista'

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
      <Route path="/encontro/:cpf/:ano" element={<Encontro />} />
      <Route path="/verificacao-dados-encontreiro/:cpf/:ano" element={<VerificacaoDadosEncontreiro />} />
      <Route path="/verificacao-dados-encontrista/:cpf/:ano" element={<VerificacaoDadosEncontrista />} />
      <Route path="/inscricao-encontreiro/:cpf/:ano" element={<InscricaoEncontreiro />} />
      <Route path="/inscricao-encontrista/:cpf/:ano" element={<InscricaoEncontrista />} />
    </Routes>
  );
};

export default AppRoutes;

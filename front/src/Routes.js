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
import InscricaoEncontrista from './pages/InscricaoEnconstrista'; // Mantido conforme seu código original
import EncontroAntigo from './pages/EncontroAntigo';
import EncontroAtual from './pages/EncontroAtual';
import EncontristasInscritos from './pages/EncontristasInscritos';

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
      <Route path="/encontro-antigo/:cpf/:ano" element={<EncontroAntigo />} />
      <Route path="/encontro-atual/:cpf/:ano" element={<EncontroAtual />} />
      <Route path="/encontristas-inscritos/:cpf/:ano" element={<EncontristasInscritos />} />
    </Routes>
  );
};

export default AppRoutes;

import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './Login';
import LoginAdmin from './LoginAdmin';

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/admin-login" element={<LoginAdmin />} />
    </Routes>
  );
};

export default AppRoutes;

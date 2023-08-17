// AdminRoutes.js
import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Dashboard from './Dashboard';
import Teachers from './Teachers';
import SideBar from '../../Components/Admin/Sidebar';
import sidebar_menu from '../../constants/sidebar-menu';
import Estudents from './Estudents';

function AdminRoutes() {
  return (
    <div className='dashboard-container'>
      <SideBar menu={sidebar_menu} />
      <main className='dashboard-body'>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/profesores" element={<Teachers />} />
          <Route path="/estudiantes" element={<Estudents />} />
          <Route path="/categorias" element={<div></div>} />
          <Route path="/caracteristicas" element={<div></div>} />
        </Routes>
      </main>
    </div>
  );
}

export default AdminRoutes;
import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Dashboard from './Dashboard';
import Teachers from './Teachers';
import AddTeacher from './Teachers/AddTeacher';
import Students from './Students';
import Categories from './Categories';
import Features from './Features';
import SideBar from '../../Components/Admin/Sidebar';
import sidebar_menu from '../../constants/sidebar-menu';


function AdminRoutes() {
  return (
    <div className='dashboard-container'>
      <SideBar menu={sidebar_menu} />
      <main className='dashboard-body'>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/profesores" element={<Teachers />} />
          <Route path="/agregar-profesor" element={<AddTeacher />} />
          <Route path="/estudiantes" element={<Students />} />
          <Route path="/categorias" element={<Categories />} />
          <Route path="/caracteristicas" element={<Features />} />
        </Routes>
      </main>
    </div>
  );
}

export default AdminRoutes;
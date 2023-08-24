import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Dashboard from './Dashboard';
import Teachers from './Teachers';
import AddTeacher from './Teachers/AddTeacher';
import Students from './Students';
import Categories from './Categories';
import AddCategory from './Categories/AddCategory';
import Characteristics from './Characteristics';
import AddCharacteristic from './Characteristics/AddCharacteristic';
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
          <Route path="/editar-profesor/:id" element={<AddTeacher />} />
          <Route path="/estudiantes" element={<Students />} />
          <Route path="/categorias" element={<Categories />} />
          <Route path="/agregar-categoria" element={<AddCategory/>} />
          <Route path="/caracteristicas" element={<Characteristics />} />
          <Route path="/agregar-caracteristica" element={<AddCharacteristic />} />
        </Routes>
      </main>
    </div>
  );
}

export default AdminRoutes;
// AdminRoutes.js
import React from 'react';
import { Route, Routes } from 'react-router-dom';
import AdminDashboard from './AdminDashboard/AdminDashboard';
import AddTeacherPage from './AddTeacherPage/AddTeacherPage';
import ListTeacherPage from './ListTeachersPage/ListTeachersPage';
import Navbar from "../../Components/Admin/Navbar/Navbar"

function AdminRoutes() {
  return (
    <>
    <Navbar/>
    <Routes>
      <Route path="/" element={<AdminDashboard />} />
      <Route path="/agregar-profesor" element={<AddTeacherPage />} />
      <Route path="/profesores" element={<ListTeacherPage />} />
    </Routes>
    </>
  );
}

export default AdminRoutes;
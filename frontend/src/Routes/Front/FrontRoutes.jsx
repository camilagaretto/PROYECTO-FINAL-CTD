import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from './Home/Home';
import Navbar from "../../Components/Navbar/Navbar"
import TeacherDetail from './TeacherDetail/TeacherDetail';

function FrontRoutes() {
  return (
    <>
    <Navbar/>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/teacher" element={<TeacherDetail />} />
    </Routes>
    </>
  );
}

export default FrontRoutes;
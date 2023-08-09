import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from './Home/Home';
import Navbar from "../../Components/Navbar/Navbar"

function FrontRoutes() {
  return (
    <>
    <Navbar/>
    <Routes>
      <Route path="/" element={<Home />} />
    </Routes>
    </>
  );
}

export default FrontRoutes;
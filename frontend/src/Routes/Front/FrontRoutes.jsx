import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from './Home/Home';
import Navbar from "../../Components/Navbar/Navbar"
import TeacherDetail from './TeacherDetail/TeacherDetail';
import RegisterUser from './RegisterUser/RegisterUser';
import Footer from "../../Components/Footer/Footer";
import Login from './Login/Login';
import UserDetail from './UserDetail/UserDetail';
import Category from './Category';
import Favourites from './Favourites/Favourites';
import Summary from './Summary';
import Confirm from './Confirm';
import History from './History/History';

function FrontRoutes() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/teacher/:id" element={<TeacherDetail />} />
        <Route path="/user/:id" element={<UserDetail />} />
        <Route path="/register" element={<RegisterUser />} />
        <Route path="/login" element={<Login />} />
        <Route path="/search/:subject/:dateTime" element={<Category />} />
        <Route path="/summary/:teacherId/:startDate/:endDate" element={<Summary />} />
        <Route path="/confirmation" element={<Confirm />} />
        <Route path="/user/:id/favourites" element={<Favourites />} />
        <Route path="/user/:id/appointments" element={<History />} />
      </Routes>
      <Footer />
    </>
  );
}

export default FrontRoutes;
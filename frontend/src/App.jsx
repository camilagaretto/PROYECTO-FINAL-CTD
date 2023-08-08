import { BrowserRouter, Route, Routes } from "react-router-dom"
import Navbar from "./Components/Navbar/Navbar"
import FrontRoutes from './Routes/Front/FrontRoutes'; 
import AdminRoutes from './Routes/Admin/AdminRoutes';
import Footer from "./Components/Footer/Footer";

function App() {

  return (
    <>
     <BrowserRouter>
        <Navbar/>
        <Routes>
          <Route path="/" element={<FrontRoutes/>}/>
          <Route path="/administracion/*" element={<AdminRoutes/>}/>
        </Routes>
        <Footer/>
     </BrowserRouter>
    </>
  )
}

export default App

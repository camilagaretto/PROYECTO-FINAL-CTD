import { BrowserRouter, Route, Routes } from "react-router-dom"
import FrontRoutes from './Routes/Front/FrontRoutes'; 
import AdminRoutes from './Routes/Admin/AdminRoutes';
import Footer from "./Components/Footer/Footer";

function App() {

  return (
    <>
     <BrowserRouter>
        <Routes>
          <Route path="/*" element={<FrontRoutes/>}/>
          <Route path="/administracion/*" element={<AdminRoutes/>}/>
        </Routes>
        <Footer/>
     </BrowserRouter>
    </>
  )
}

export default App

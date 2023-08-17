import { BrowserRouter, Route, Routes } from "react-router-dom"
import FrontRoutes from './Routes/Front/FrontRoutes'; 
import AdminRoutes from './Routes/Admin/AdminRoutes';

function App() {

  return (
    <>
     <BrowserRouter>
        <Routes>
          <Route path="/*" element={<FrontRoutes/>}/>
          <Route path="/admin/*" element={<AdminRoutes/>}/>
        </Routes>
     </BrowserRouter>
    </>
  )
}

export default App

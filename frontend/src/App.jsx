import { BrowserRouter, Route, Routes } from "react-router-dom"
import Navbar from "./Components/Navbar/Navbar"
import Home from "./Routes/Home/Home"

function App() {

  return (
    <>
     <BrowserRouter>
        <Navbar/>
        <Routes>
          <Route path="/" element={<Home/>}/>
        </Routes>
     </BrowserRouter>
    </>
  )
}

export default App

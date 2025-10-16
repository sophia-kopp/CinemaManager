import './App.css'

import {Route, Routes} from "react-router-dom";
import AllHalls from "./CinemaHalls/AllHalls.tsx";

function App() {

  return (
    <>
    <h1>Cinema Manager</h1>
        <Routes>
            <Route path={"/allHalls"} element={<AllHalls/>}/>
        </Routes>
    </>
  )
}

export default App

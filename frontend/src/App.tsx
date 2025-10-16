import './App.css'

import {Route, Routes} from "react-router-dom";
import AllHalls from "./CinemaHalls/AllHalls.tsx";
import NewHallForm from "./CinemaHalls/NewHallForm.tsx";

function App() {

  return (
    <>
    <h1>Cinema Manager</h1>
        <Routes>
            <Route path={"/allHalls"} element={<AllHalls/>}/>
            <Route path={"/newHall"} element={<NewHallForm/>}/>
            <Route path={"/editHall/:id"} element={<NewHallForm/>}/>
        </Routes>
    </>
  )
}

export default App

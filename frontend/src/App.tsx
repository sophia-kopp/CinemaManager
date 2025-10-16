import './App.css'

import {Route, Routes} from "react-router-dom";
import AllHalls from "./CinemaHalls/AllHalls.tsx";
import NewHallForm from "./CinemaHalls/NewHallForm.tsx";
import Header from "./MainPage/Header.tsx";
import Footer from "./MainPage/Footer/Footer.tsx";

function App() {

    return (
        <>
            <Header/>
            <div className={"main"}>
            <Routes>
                <Route path={"/allHalls"} element={<AllHalls/>}/>
                <Route path={"/newHall"} element={<NewHallForm/>}/>
            </Routes>
            </div>
            <Footer/>
        </>
    )
}

export default App

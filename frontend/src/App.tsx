import './App.css'

import {Route, Routes} from "react-router-dom";
import AllHalls from "./CinemaHalls/AllHalls.tsx";
import NewHallForm from "./CinemaHalls/NewHallForm.tsx";
import Header from "./MainPage/Header/Header.tsx";
import Footer from "./MainPage/Footer/Footer.tsx";
import Home from "./MainPage/Home.tsx";
import AllMovies from "./Movies/AllMovies.tsx";

function App() {

    return (
        <>
            <Header/>
            <div className={"main"}>
            <Routes>
                <Route path={"/"} element={<Home/>}/>
                <Route path={"/allHalls"} element={<AllHalls/>}/>
                <Route path={"/newHall"} element={<NewHallForm/>}/>
                <Route path={"/editHall/:id"} element={<NewHallForm/>}/>
                <Route path={"/allMovies"} element={<AllMovies/>}/>
            </Routes>
            </div>
            <Footer/>
        </>
    )
}

export default App

import './App.css'

import {Route, Routes} from "react-router-dom";
import AllHalls from "./CinemaHalls/AllHalls.tsx";
import NewHallForm from "./CinemaHalls/NewHallForm.tsx";
import Header from "./MainPage/Header/Header.tsx";
import Footer from "./MainPage/Footer/Footer.tsx";
import Home from "./MainPage/Home.tsx";
import AllMovies from "./Movies/AllMovies.tsx";
import AllPresentations from "./Schedule/AllPresentations.tsx";
import PresentationForm from "./Schedule/PresentationForm.tsx";
import AllFavMovies from "./Movies/AllFavMovies.tsx";
import ProtectedRoute from "./MainPage/ProtectedRoute.tsx";
import {useState} from "react";
import Login from "./MainPage/Login.tsx";
import AllReservations from "./Reservation/AllReservations.tsx";
import ReservationForm from "./Reservation/ReservationForm.tsx";

function App() {

    const [user, setUser] = useState<string | undefined | null>(undefined);

    return (
        <>
            <Header/>
            <div className={"main"}>
                <Routes>
                    <Route path={"/login"} element={<Login setUser={setUser}/>}/>
                    <Route element={<ProtectedRoute user={user}/>}>
                        <Route path={"/"} element={<Home user={user ?? ""}/>}/>
                        <Route path={"/allHalls"} element={<AllHalls/>}/>
                        <Route path={"/newHall"} element={<NewHallForm/>}/>
                        <Route path={"/editHall/:id"} element={<NewHallForm/>}/>
                        <Route path={"/allMovies"} element={<AllMovies/>}/>
                        <Route path={"/allFavMovies"} element={<AllFavMovies/>}/>
                        <Route path={"/allPresentations"} element={<AllPresentations/>}/>
                        <Route path={"/newPresentation"} element={<PresentationForm/>}/>
                        <Route path={"/editPresentation/:id"} element={<PresentationForm/>}/>
                        <Route path={"/allReservations"} element={<AllReservations/>}/>
                        <Route path={"/newReservation/:id"} element={<ReservationForm/>}/>
                    </Route>
                </Routes>
            </div>
            <Footer/>
        </>
    )
}

export default App

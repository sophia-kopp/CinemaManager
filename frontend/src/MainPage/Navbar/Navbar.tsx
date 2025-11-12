import {Link} from "react-router-dom";
import './Navbar.css';
import {GiHearts, GiClapperboard, GiHouse, GiTicket, GiVideoCamera, GiTheater, GiExitDoor} from "react-icons/gi";

export default function Navbar() {

    function logout() {

        const host: string = window.location.host === "localhost:5173" ?
            "http://localhost:8080"
            :
            window.location.origin;
        window.open(host + "/logout", "_self")
    }

    return (
        <div className={"navbar"}>
            <div className={"links"}>
                <Link to={"/"} className={"nav-button"}><GiHouse/> Home</Link>
                <Link to={"/allPresentations"} className={"nav-button"}><GiClapperboard/> Presentations</Link>
                <Link to={"/allMovies"} className={"nav-button"}><GiVideoCamera/> All Movies</Link>
                <Link to={"/allFavMovies"} className={"nav-button"}><GiHearts/> Favourite Movies</Link>
                <Link to={"/allHalls"} className={"nav-button"}><GiTheater/> Halls</Link>
                <Link to={"/allReservations"} className={"nav-button"}><GiTicket/> Reservations</Link>
            </div>
            <div className={"button"}>
                <button onClick={logout}><GiExitDoor/> Logout</button>
            </div>
        </div>
    )
}
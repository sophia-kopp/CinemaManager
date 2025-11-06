import {Link} from "react-router-dom";
import './Navbar.css';

export default function Navbar(){

    function logout(){

            const host: string = window.location.host === "localhost:5173" ?
                "http://localhost:8080"
                :
                window.location.origin;
            window.open(host + "/logout", "_self")
    }

    return (
        <div className={"navbar"}>
            <Link to={"/"} className={"nav-button"}>Home</Link>
            <Link to={"/allPresentations"} className={"nav-button"}>Presentations</Link>
            <Link to={"/allMovies"} className={"nav-button"}>All Movies</Link>
            <Link to={"/allFavMovies"} className={"nav-button"}>Favourite Movies</Link>
            <Link to={"/allHalls"} className={"nav-button"}>Halls</Link>
            <button onClick={logout}>Logout</button>
        </div>
    )
}
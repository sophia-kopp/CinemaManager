import {Link} from "react-router-dom";
import './Navbar.css';

export default function Navbar(){
    return (
        <div className={"navbar"}>
            <Link to={"/"} className={"nav-button"}>Home</Link>
            <Link to={"/allPresentations"} className={"nav-button"}>Presentations</Link>
            <Link to={"/allMovies"} className={"nav-button"}>Movies</Link>
            <Link to={"/allHalls"} className={"nav-button"}>Halls</Link>
        </div>
    )
}
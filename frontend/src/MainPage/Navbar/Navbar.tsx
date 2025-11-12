import {Link} from "react-router-dom";
import './Navbar.css';
import axios from "axios";
import {useEffect, useState} from "react";
import {GiHearts, GiClapperboard, GiHouse, GiTicket, GiVideoCamera, GiTheater, GiExitDoor} from "react-icons/gi";


export default function Navbar() {

    const [userRole, setUserRole] = useState<string>("");

    function logout() {

        const host: string = window.location.host === "localhost:5173" ?
            "http://localhost:8080"
            :
            window.location.origin;
        window.open(host + "/logout", "_self")
    }

    const loadUser = () => {
        axios.get("api/auth/me")
            .then(r => {
                setUserRole(r.data.role)
            })
            .catch((e) => console.log(e));
    }

    useEffect(() => {
        loadUser()
    }, []);

    return (
        <div className={"navbar"}>
            {userRole === "GUEST" &&
                <div className={"links"}>
                    <Link to={"/"} className={"nav-button"}>Home</Link>
                    <Link to={"/allPresentations"} className={"nav-button"}>Presentations</Link>
                    <Link to={"/allReservations"} className={"nav-button"}>Reservations</Link>
                </div>
            }
            {userRole === "ADMIN" &&
                <div className={"links"}>
                    <Link to={"/"} className={"nav-button"}>Home</Link>
                    <Link to={"/allPresentations"} className={"nav-button"}>Presentations</Link>
                    <Link to={"/allMovies"} className={"nav-button"}>All Movies</Link>
                    <Link to={"/allFavMovies"} className={"nav-button"}>Favourite Movies</Link>
                    <Link to={"/allHalls"} className={"nav-button"}>Halls</Link>
                    <Link to={"/allReservations"} className={"nav-button"}>Reservations</Link>
                </div>
            }
            <div className={"button"}>
                <button onClick={logout}><GiExitDoor/> Logout</button>
            </div>
        </div>
    )
}
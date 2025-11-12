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
            <div className={"links"}>
                <Link to={"/"} className={"nav-button"}><GiHouse/> Home</Link>
                <Link to={"/allPresentations"} className={"nav-button"}><GiClapperboard/> Presentations</Link>
                {userRole === "ADMIN" &&
                    <Link to={"/allMovies"} className={"nav-button"}><GiVideoCamera/> All Movies</Link>
                }
                {userRole === "ADMIN" &&
                    <Link to={"/allFavMovies"} className={"nav-button"}><GiHearts/> Favourite Movies</Link>
                }
                {userRole === "ADMIN" &&
                    <Link to={"/allHalls"} className={"nav-button"}><GiTheater/> Halls</Link>
                }
                <Link to={"/allReservations"} className={"nav-button"}><GiTicket/> Reservations</Link>
            </div>

            <div className={"button"}>
                <button onClick={logout}><GiExitDoor/> Logout</button>
            </div>
        </div>
    )
}
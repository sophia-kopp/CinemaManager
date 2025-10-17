import {useEffect, useState} from "react";
import type {CinemaHall} from "../model/CinemaHall.ts";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import HallCard from "./HallCard/HallCard.tsx";
import './AllHalls.css'

export default function AllHalls() {

    const [allHalls, setAllHalls] = useState<CinemaHall[]>([]);
    const nav = useNavigate();
    const [deleteHall, setDeleteHall] = useState<string>("");

    useEffect(() => {
        getAllHalls()
    }, [deleteHall]);

    function getAllHalls() {
        axios.get("/api/halls")
            .then(response => setAllHalls(response.data))
            .catch(e => console.log(e))
    }

    function onGoToNewHall() {
        nav("/newHall");
    }

    return (
        <div className={"all-halls"}>
            <h2>All Cinema Halls</h2>
            <button className={"new-hall"} onClick={onGoToNewHall}>Create new Hall</button>
            {allHalls.map(hall =>
                <HallCard key={hall.id} hall={hall} deleteHall={setDeleteHall}/>
            )}
        </div>
    )
}
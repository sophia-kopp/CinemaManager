import {useEffect, useState} from "react";
import type {CinemaHall} from "../model/CinemaHall.ts";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function AllHalls(){

    const [allHalls, setAllHalls] = useState<CinemaHall[]>([]);
    const nav = useNavigate();

    useEffect(() => {
        getAllHalls()
    }, []);

    function getAllHalls(){
        axios.get("/api/halls")
            .then(response => setAllHalls(response.data))
            .catch(e=> console.log(e))
    }

    function onGoToNewHall(){
        nav("/newHall");
    }
    return (
        <>
            {allHalls.map(hall =>
                <h2>{hall.name}</h2>
            )}
            <button onClick={onGoToNewHall}>Create new Hall</button>
        </>
    )
}
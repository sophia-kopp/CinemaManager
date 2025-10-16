import {useEffect, useState} from "react";
import type {CinemaHall} from "../model/CinemaHall.ts";
import axios from "axios";

export default function AllHalls(){

    const [allHalls, setAllHalls] = useState<CinemaHall[]>([]);

    useEffect(() => {
        getAllHalls()
    }, []);

    function getAllHalls(){
        axios.get("/api/halls")
            .then(response => setAllHalls(response.data))
            .catch(e=> console.log(e))
    }

    return (
        <>
            {allHalls.map(hall =>
                <h2>{hall.name}</h2>
            )}
        </>
    )
}
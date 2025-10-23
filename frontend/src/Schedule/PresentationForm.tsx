import {useEffect, useState} from "react";
import type {Movie} from "../model/Movie.ts";
import type {CinemaHall} from "../model/CinemaHall.ts";
import axios from "axios";

export default function PresentationForm() {
    const [movies, setMovies] = useState<Movie[]>([]);
    const [halls, setHalls] = useState<CinemaHall[]>([]);

    function loadData(){
        //axios.get("/api/favouriteMovies").then().catch()
        axios.get("api/halls")
            .then(r => setHalls(r.data))
            .catch(e=> console.log(e))
    }

    useEffect(() => {
        loadData()
    }, []);

    return (
        <form>
            <label>
                <input/>
            </label>
        </form>
    )
}
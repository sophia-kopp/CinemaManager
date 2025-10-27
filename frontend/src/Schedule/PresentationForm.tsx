import {type FormEvent, useEffect, useState} from "react";
import type {CinemaHall} from "../model/CinemaHall.ts";
import axios from "axios";
import './PresentationForm.css';
import type {FavouriteMovie} from "../model/FavouriteMovie.ts";
import type {PresentationDto} from "../model/Presentation.ts";


export default function PresentationForm() {
    const [favMovies, setFavMovies] = useState<FavouriteMovie[]>([]);
    const [halls, setHalls] = useState<CinemaHall[]>([]);
    const [selectedMovieId, setSelectedMovieId] = useState<string>("");
    const [selectedHallId, setSelectedHallId] = useState<string>("");
    const [startDate, setStartDate] = useState<string>("");
    const [endDate, setEndDate] = useState<string>("");

    function loadHalls() {
        axios.get("api/halls")
            .then(r => setHalls(r.data))
            .catch(e => console.log(e));
    }

    function loadMovies() {
        axios.get("/api/favouriteMovies")
            .then(r => setFavMovies(r.data))
            .catch(e => console.log(e));
    }

    function submitPresentation(e: FormEvent) {
        e.preventDefault()
        const presentation: PresentationDto = {     movieId: selectedMovieId,
            cinemaHallId: selectedHallId,
            startsAt: new Date(startDate),
            endsAt: new Date(endDate),
           }
        console.log("e: ", e)
        axios.post("/api/presentations", presentation)
            .then(r=> console.log(r))
            .catch(e=> console.log(e));
    }

    useEffect(() => {
        loadHalls()
        loadMovies()
    }, []);

    return (
        <form onSubmit={submitPresentation} className={"presentation-form"}>
            <label>Movie:
                <select onChange={e=> setSelectedMovieId(e.target.id)}>
                    {favMovies.map(m =>
                        <option value={m.id}>{m.name}</option>
                    )}
                </select>
            </label>
            <label>Hall:
                <select onChange={e=> setSelectedHallId(e.target.id)}>
                    {halls.map(h =>
                        <option value={h.id}>{h.name}</option>
                    )}
                </select>
            </label>
            <label>Starts at:
                <input aria-label="Date and time" type="datetime-local"
                       onChange={e=> setStartDate(e.target.value)}/>
            </label>
            <label>Ends at:
                <input aria-label="Date and time" type="datetime-local"
                       onChange={e=> setEndDate(e.target.value)}/>
            </label>
            <button type={"submit"}>Save Presentation</button>
        </form>
    )
}
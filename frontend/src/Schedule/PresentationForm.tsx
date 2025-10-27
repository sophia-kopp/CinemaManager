import {type FormEvent, useEffect, useState} from "react";
import type {CinemaHall} from "../model/CinemaHall.ts";
import axios from "axios";
import './PresentationForm.css';
import type {FavouriteMovie} from "../model/FavouriteMovie.ts";
import type {PresentationDto} from "../model/Presentation.ts";
import {useNavigate} from "react-router-dom";


export default function PresentationForm() {

    const nav = useNavigate();

    const [favMovies, setFavMovies] = useState<FavouriteMovie[]>([]);
    const [halls, setHalls] = useState<CinemaHall[]>([]);

    const [selectedMovieId, setSelectedMovieId] = useState<string>("");
    const [selectedHallId, setSelectedHallId] = useState<string>("");
    const [startDate, setStartDate] = useState<string>("");
    const [endDate, setEndDate] = useState<string>("");

    const [validForm, setValidForm] = useState<boolean>(false);

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

    function validateForm() {
        if (endDate !== "" && startDate !== "" && selectedHallId !== "" && selectedMovieId !== "") {
            setValidForm(true);
        } else {
            setValidForm(false);
        }
    }

    function submitPresentation(e: FormEvent) {
        e.preventDefault()
        const presentation: PresentationDto = {
            movieId: selectedMovieId,
            cinemaHallId: selectedHallId,
            startsAt: new Date(startDate),
            endsAt: new Date(endDate),
        }
        console.log("e: ", e)
        axios.post("/api/presentations", presentation)
            .then(() => nav("/allPresentations"))
            .catch(e => console.log(e));
    }

    useEffect(() => {
        loadHalls()
        loadMovies()
    }, []);

    useEffect(() => {
        validateForm()
    }, [selectedMovieId, selectedHallId, startDate, endDate]);

    return (
        <form onSubmit={submitPresentation} className={"presentation-form"}>
            <label>Movie:
                <select value={selectedMovieId} onChange={e => setSelectedMovieId(e.target.value)}>
                    <option value={""}>Select a movie...</option>
                    {favMovies.map(m =>
                        <option value={m.id}>{m.name}</option>
                    )}
                </select>
            </label>
            <label>Hall:
                <select onChange={e => setSelectedHallId(e.target.value)}>
                    <option value={""}>Select a hall...</option>
                    {halls.map(h =>
                        <option value={h.id}>{h.name}</option>
                    )}
                </select>
            </label>
            <label>Starts at:
                <input aria-label="Date and time" type="datetime-local"
                       onChange={e => setStartDate(e.target.value)}/>
            </label>
            <label>Ends at:
                <input aria-label="Date and time" type="datetime-local"
                       onChange={e => setEndDate(e.target.value)}/>
            </label>
            <button disabled={!validForm} type={"submit"}>Save Presentation</button>
        </form>
    )
}
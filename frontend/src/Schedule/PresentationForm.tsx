import {type FormEvent, useEffect, useState} from "react";
import type {CinemaHall} from "../model/CinemaHall.ts";
import axios from "axios";
import './PresentationForm.css';
import type {FavouriteMovie} from "../model/FavouriteMovie.ts";
import type {PresentationDto} from "../model/Presentation.ts";
import {useNavigate, useParams} from "react-router-dom";


export default function PresentationForm() {

    const param = useParams()
    const nav = useNavigate();

    const [favMovies, setFavMovies] = useState<FavouriteMovie[]>([]);
    const [halls, setHalls] = useState<CinemaHall[]>([]);

    const [selectedMovie, setSelectedMovie] = useState<string>("");
    const [selectedHall, setSelectedHall] = useState<CinemaHall>({id: "", name: "", rows: 0, seatsPerRow: 0});
    const [selectedHallName, setSelectedHallName] = useState<string>("");
    const [startDate, setStartDate] = useState<string>("");
    const [duration, setDuration] = useState<number>(0);

    const [validForm, setValidForm] = useState<boolean>(false);

    function loadHalls() {
        console.log("getHalls")
        axios.get("/api/halls")
            .then(r => setHalls(r.data))
            .catch(e => console.log(e));
    }

    function loadMovies() {
        axios.get("/api/favouriteMovies")
            .then(r => setFavMovies(r.data))
            .catch(e => console.log(e));
    }

    function loadExistingPresentation() {
        axios.get("/api/presentations/" + param.id)
            .then(r => {
                    setSelectedHall(r.data.cinemaHall)
                    setSelectedMovie(r.data.movieName)
                    setStartDate(r.data.startsAt)
                    setDuration(r.data.duration)
                    setValidForm(true);
                }
            )
            .catch(e => console.log(e));

    }

    function validateForm() {
        if (duration !== 0 && startDate !== "" && selectedHall.id !== "" && selectedMovie !== "") {
            setValidForm(true);
        } else {
            setValidForm(false);
            setValidForm(false);
        }
    }

    function updateSelectedHall() {
        setSelectedHall(halls.filter(h=> h.name === selectedHallName)[0]);
    }

    function submitPresentation(e: FormEvent) {
        e.preventDefault()
        const presentation: PresentationDto = {
            movieName: selectedMovie,
            cinemaHall: selectedHall,
            startsAt: new Date(startDate),
            duration: duration,
        }

        if (param.id === undefined) {
            axios.post("/api/presentations", presentation)
                .then((r) => {
                    nav("/allPresentations")
                    console.log(r.data)
                })
                .catch(e => console.log(e));
        } else {
            axios.put("/api/presentations/" + param.id, presentation)
                .then(() => nav("/allPresentations"))
                .catch(e => console.log(e));
        }
    }

    useEffect(() => {
        loadHalls()
        loadMovies()
        if (param.id !== undefined) {
            loadExistingPresentation()
        }
    }, []);


    useEffect(() => {
        validateForm()
    }, [selectedMovie, selectedHall, startDate, duration]);

    useEffect(() => {
        updateSelectedHall();
    }, [selectedHallName]);

    return (
        <form onSubmit={submitPresentation} className={"presentation-form"}>
            <label>Movie:
                <select value={selectedMovie} onChange={e => setSelectedMovie(e.target.value)}>
                    <option value={""}>Select a movie...</option>
                    {favMovies.map(m =>
                        <option value={m.name}>{m.name}</option>
                    )}
                </select>
            </label>
            <label>Hall:
                <select value={selectedHallName} onChange={e =>
                    setSelectedHallName(e.target.value)}>
                    <option value={""}>Select a hall...</option>
                    {halls.map(h =>
                        <option value={h.name}>{h.name}</option>
                    )}
                </select>
            </label>
            <label>Starts at:
                <input value={startDate} aria-label="Date and time" type="datetime-local"
                       onChange={e => setStartDate(e.target.value)}/>
            </label>
            <label>Duration in minutes:
                <input value={duration} type="number"
                       onChange={e => setDuration(Number(e.target.value))}/>
            </label>
            <p>Ends at: </p>
            <button disabled={!validForm} type={"submit"}>Save Presentation</button>
        </form>
    )
}
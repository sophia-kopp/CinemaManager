import {useState} from "react";
import type {Movie} from "../model/Movie.ts";
import axios from "axios";
import MovieCard from "./MovieCard/MovieCard.tsx";

export default function AllMovies(){
    const [movies, setMovies] = useState<Movie[]>([]);

    function loadAllMovies(){
        axios.get("api/movies")
            .then(r=> setMovies(r.data))
            .catch(e=> console.log(e))
    }

    return (
        <>
            <h3>All Movies</h3>
            <button onClick={loadAllMovies}>Go Get Them!</button>
            {movies.map(m=>
                <MovieCard movie={m}/>
            )}
        </>
    )
}
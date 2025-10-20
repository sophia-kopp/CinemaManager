import {useState} from "react";
import type {Movie} from "../model/Movie.ts";
import MovieCard from "./MovieCard/MovieCard.tsx";
import MoviePagination from "./MoviePagination/MoviePagination.tsx";

export default function AllMovies(){
    const [movies, setMovies] = useState<Movie[]>([]);

    return (
        <>
            <h3>All Movies</h3>
            <MoviePagination setMovies={setMovies}/>
            {movies.map(m=>
                <MovieCard movie={m}/>
            )}
            <MoviePagination setMovies={setMovies}/>
        </>
    )
}
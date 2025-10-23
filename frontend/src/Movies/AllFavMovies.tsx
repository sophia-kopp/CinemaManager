import {useEffect, useState} from "react";
import type {FavouriteMovie} from "../model/FavouriteMovie.ts";
import axios from "axios";

export default function AllFavMovies() {
    const [favMovies, setFavMovies] = useState<FavouriteMovie[]>([]);

    function loadAllFavMovies() {
        axios.get("api/favouriteMovies")
            .then(r => setFavMovies(r.data))
            .catch(e => console.log(e))
    }

    useEffect(() => {
        loadAllFavMovies();
    }, []);

    return (
        <>
            <h3>All Movies</h3>
            {favMovies.map(m =>
                <h4>{m.name}</h4>
            )}
        </>
    )
}
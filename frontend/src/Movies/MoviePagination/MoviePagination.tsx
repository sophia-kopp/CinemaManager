import {useEffect, useState} from "react";
import type {Movie} from "../../model/Movie.ts";
import axios from "axios";
import './MoviePagination.css'

type MoviePaginationProps = {
    setMovies: (movies: Movie[]) => void;
}

export default function MoviePagination(props: Readonly<MoviePaginationProps>) {
    const [moviesLoaded, setMoviesLoaded] = useState<boolean>(false);
    const [pageIndex, setPageIndex] = useState<number>(1);

    function getAnteriorPage() {
        if (pageIndex <= 1) {
            setPageIndex(1);
        } else{
            setPageIndex(pageIndex - 1);
        }
    }

    function getNextPage() {
        if (pageIndex >= 500) {
            setPageIndex(500);
        } else {
            setPageIndex(pageIndex + 1);
        }
    }

    function loadPage() {
        axios.get("api/movies/" + pageIndex)
            .then(r => {
                //setMovies(r.data)
                props.setMovies(r.data)
                setMoviesLoaded(true)
            })
            .catch(e => console.log(e))
    }

    useEffect(() => {
        loadPage()
    }, [pageIndex]);


    return (
        <div className={"pagination-buttons"}>
            {moviesLoaded && <button onClick={getAnteriorPage}>Anterior Page</button>}
            {moviesLoaded && <h4>Page: {pageIndex}</h4>}
            {moviesLoaded && <button onClick={getNextPage}>Next Page</button>}


        </div>
    )
}
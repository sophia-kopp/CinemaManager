import type {Movie} from "../../model/Movie.ts";
import './MovieCard.css'
import axios from "axios";

type MovieCardProps={
    movie: Movie
}
export default function MovieCard(props: Readonly<MovieCardProps>){

   function onAddToFavourite(){
       axios.post("api/favouriteMovies")
           .then(r=>console.log(r.data))
           .catch(e=>console.log(e));
   }

    return (
        <div className={"movie-card"}>
            <button onClick={onAddToFavourite}>Favourite</button>
            <img src={props.movie.poster_path} alt={"movie-img"}/>
            <h4>{props.movie.title}</h4>
            <p>Release date: {props.movie.release_date}</p>
            <p>Votes: {props.movie.vote_count} - Evaluation: {props.movie.vote_average}</p>
            <p>adult? {props.movie.adult}</p>
            <p>Original Language: {props.movie.original_language}</p>
            <p>{props.movie.overview}</p>
        </div>
    )
}
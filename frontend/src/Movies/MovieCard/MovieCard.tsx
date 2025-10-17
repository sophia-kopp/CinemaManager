import type {Movie} from "../../model/Movie.ts";
import './MovieCard.css'

type MovieCardProps={
    movie: Movie
}
export default function MovieCard(props: Readonly<MovieCardProps>){
    return (
        <div className={"movie-card"}>
            <img src={props.movie.poster_path}/>
            <h4>{props.movie.title}</h4>
            <p>Release date: {props.movie.release_date}</p>
            <p>Votes: {props.movie.vote_count} - Evaluation: {props.movie.vote_average}</p>
            <p>adult? {props.movie.adult}</p>
            <p>Original Language: {props.movie.original_language}</p>
            <p>{props.movie.overview}</p>
        </div>
    )
}
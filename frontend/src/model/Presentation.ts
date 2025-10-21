import type {Movie} from "./Movie.ts";
import type {CinemaHall} from "./CinemaHall.ts";

export type Presentation = {
    id: string,
    movie: Movie,
    startsAt: Date,
    endAt: Date,
    cinemaHall: CinemaHall
}
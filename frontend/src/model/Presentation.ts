import type {CinemaHall} from "./CinemaHall.ts";

export type Presentation = {
    id: string,
    movieName: string,
    startsAt: Date,
    duration: number,
    cinemaHall: CinemaHall,
}
export type PresentationDto = {
    movieName: string,
    cinemaHall: CinemaHall
    startsAt: Date,
    duration: number,
}
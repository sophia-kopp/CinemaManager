export type Presentation = {
    id: string,
    movieId: string,
    startsAt: Date,
    endsAt: Date,
    cinemaHallId: string
}
export type PresentationDto = {
    movieId: string,
    cinemaHallId: string
    startsAt: Date,
    endsAt: Date,
}
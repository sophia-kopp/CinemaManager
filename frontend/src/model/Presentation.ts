export type Presentation = {
    id: string,
    movieName: string,
    startsAt: Date,
    endsAt: Date,
    cinemaHallName: string
}
export type PresentationDto = {
    movieName: string,
    cinemaHallName: string
    startsAt: Date,
    endsAt: Date,
}
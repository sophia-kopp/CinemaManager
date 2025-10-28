export type Presentation = {
    id: string,
    movieName: string,
    startsAt: Date,
    duration: number,
    cinemaHallName: string
}
export type PresentationDto = {
    movieName: string,
    cinemaHallName: string
    startsAt: Date,
    duration: number,
}
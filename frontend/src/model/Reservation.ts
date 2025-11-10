import type {Presentation} from "./Presentation.ts";
import type {SeatPosition} from "./SeatPosition.ts";

export type Reservation = {
    id: string,
    presentation: Presentation
    amountOfSeats: number,
    seatPositions: SeatPosition[]
    price: number
}

export type ReservationDto = {
    presentation: Presentation
    amountOfSeats: number,
    seatPositions: SeatPosition[]
    price: number
}
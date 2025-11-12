import axios from "axios";
import type {Reservation} from "../model/Reservation.ts";

type ReservationCardProps = {
    reservation: Reservation
    updateReservations: (reservationId: string) => void
}

export default function ReservationCard(props: Readonly<ReservationCardProps>){
    function deleteReservation(){
        axios.delete("api/reservations/" + props.reservation.id)
            .then(()=> props.updateReservations(props.reservation.id))
            .catch(e=> console.log(e));
    }

    return (
        <div>
            <p>Id: {props.reservation.id}</p>
            <p>MovieName: {props.reservation.presentation.movieName}</p>
            <p>Starts at: {props.reservation.presentation.startsAt.toString()}</p>
            <p>Amount of Seats: {props.reservation.amountOfSeats}</p>
            <p>Price: {props.reservation.price}</p>
            {props.reservation.seatPositions.map(seat =>
                <p key={seat.row + seat.seatNumber}>Row: {seat.row} - Seat: {seat.seatNumber}</p>
            )}
            <button onClick={deleteReservation}>Delete</button>
        </div>
    )
}
import {useEffect, useState} from "react";
import type {Reservation} from "../model/Reservation.ts";
import axios from "axios";

export default function AllReservations() {

    const [reservations, setReservations] = useState<Reservation[]>([]);

    function loadAllReservations() {
        axios.get("api/reservations")
            .then(r => setReservations(r.data))
            .catch(e => console.log(e))
    }

    useEffect(() => {
        loadAllReservations();
    }, []);

    return (
        <>
            <h3>All Reservations</h3>
            {reservations.map(reservation =>
                <div>
                    <p>Id: {reservation.id}</p>
                    <p>MovieName: {reservation.presentation.movieName}</p>
                    <p>Starts at: {reservation.presentation.startsAt.toString()}</p>
                    <p>Amount of Seats: {reservation.amountOfSeats}</p>
                    <p>Price: {reservation.price}</p>
                     {reservation.seatPositions.map(seat =>
                         <p>Row: {seat.row} - Seat: {seat.seatNumber}</p>
                    )}
                </div>

            )}
        </>
    )
}
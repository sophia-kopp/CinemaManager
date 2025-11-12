import {useEffect, useState} from "react";
import type {Reservation} from "../model/Reservation.ts";
import axios from "axios";
import "./AllReservations.css";
import { GiPerson } from "react-icons/gi";

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
                <div className={"reservation"}>
                    <p>Id: {reservation.id}</p>
                    <h4>{reservation.presentation.movieName}</h4>
                    <p>Price: {reservation.price} $</p>
                    <p>Date: {reservation.presentation.startsAt.toString().substring(0,10)} -
                        Starts at: {reservation.presentation.startsAt.toString().substring(11,16)}</p>
                    <p><GiPerson /> {reservation.amountOfSeats} Person(s)</p>
                    <p>Seats:</p>
                    {reservation.seatPositions.map(seat =>
                         <p>Row: {seat.row.toUpperCase()} - Seat: {seat.seatNumber}</p>
                    )}
                </div>

            )}
        </>
    )
}
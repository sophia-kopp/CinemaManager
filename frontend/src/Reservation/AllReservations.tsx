import {useEffect, useState} from "react";
import type {Reservation} from "../model/Reservation.ts";
import axios from "axios";
import "./AllReservations.css";
import {GiPerson} from "react-icons/gi";

export default function AllReservations() {

    const [reservations, setReservations] = useState<Reservation[]>([]);

    function loadAllReservations() {
        axios.get("api/reservations")
            .then(r => setReservations(r.data))
            .catch(e => console.log(e))
    }

    const [isCopied, setIsCopied] = useState(false);

    useEffect(() => {
        const timeoutId = setTimeout(() => {
            setIsCopied(false);
        }, 2000);
        return () => clearTimeout(timeoutId);
    }, [copyToClipboard]);

    function copyToClipboard(id: string) {
        navigator.clipboard.writeText(id);
        console.log("Copied ID to Clipboard: ", id)
        setIsCopied(true);
    }

    useEffect(() => {
        loadAllReservations();
    }, []);

    return (
        <>
            <h3>All Reservations</h3>
            {reservations.map(reservation =>
                <div className={"reservation"}>
                    <div className={"reservation-header"}>
                        <h4>{reservation.presentation.movieName}</h4>
                        {!isCopied && <button onClick={() => copyToClipboard(reservation.id)}>📎 Copy ID</button>}
                        {isCopied && <button className={"button-copied"}>✓ Copied!</button>}
                    </div>
                    <p>Price: {reservation.price} $</p>
                    <p>Date: {reservation.presentation.startsAt.toString().substring(0, 10)} -
                        Starts at: {reservation.presentation.startsAt.toString().substring(11, 16)}</p>
                    <p><GiPerson/> {reservation.amountOfSeats} Person(s)</p>
                    <p>Seats:</p>
                    {reservation.seatPositions.map(seat =>
                        <p>Row: {seat.row.toUpperCase()} - Seat: {seat.seatNumber}</p>
                    )}
                </div>
            )}
        </>
    )
}
import axios from "axios";
import type {Reservation} from "../model/Reservation.ts";
import {GiPerson} from "react-icons/gi";
import {useEffect, useState} from "react";

type ReservationCardProps = {
    reservation: Reservation
    updateReservations: (reservationId: string) => void
}

export default function ReservationCard(props: Readonly<ReservationCardProps>) {
    function deleteReservation() {
        axios.delete("api/reservations/" + props.reservation.id)
            .then(() => props.updateReservations(props.reservation.id))
            .catch(e => console.log(e));
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
            <div className={"reservation"}>
                <div className={"reservation-header"}>
                    <h4>{props.reservation.presentation.movieName}</h4>
                    {!isCopied && <button onClick={() => copyToClipboard(props.reservation.id)}>📎 Copy ID</button>}
                    {isCopied && <button className={"button-copied"}>✓ Copied!</button>}
                </div>
                <p>Price: {props.reservation.price} $</p>
                <p>Date: {props.reservation.presentation.startsAt.toString().substring(0, 10)} -
                    Starts at: {props.reservation.presentation.startsAt.toString().substring(11, 16)}</p>
                <p><GiPerson/> {props.reservation.amountOfSeats} Person(s)</p>
                <p>Seats:</p>
                {props.reservation.seatPositions.map(seat =>
                    <p>Row: {seat.row.toUpperCase()} - Seat: {seat.seatNumber}</p>
                )}
            </div>
        </div>
    )
}
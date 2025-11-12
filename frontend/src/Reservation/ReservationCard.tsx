import axios from "axios";
import type {Reservation} from "../model/Reservation.ts";
import {useEffect, useState} from "react";
import {GiTrashCan, GiPriceTag, GiPerson} from "react-icons/gi";

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
            <div className={"reservation"}>
                <div className={"reservation-header"}>
                    <h4>{props.reservation.presentation.movieName}</h4>
                    {!isCopied && <button onClick={() => copyToClipboard(props.reservation.id)}>📎 Copy ID</button>}
                    {isCopied && <button className={"button-copied"}>✓ Copied!</button>}
                </div>
                <p>Date: {props.reservation.presentation.startsAt.toString().substring(0, 10)} -
                    Starts at: {props.reservation.presentation.startsAt.toString().substring(11, 16)}</p>
                <p><GiPerson/> {props.reservation.amountOfSeats} Person(s)</p>
                <p><GiPriceTag/> Price: {props.reservation.price} $</p>
                <div className={"seat-list"}>
                    <h5>Seats: </h5>
                    <div className={"seat-position"}>
                        {props.reservation.seatPositions.map(seat =>
                            <p key={seat.row + seat.seatNumber}>
                                Row: {seat.row.toUpperCase()} - Seat: {seat.seatNumber}</p>
                        )}
                    </div>
                </div>
                <button onClick={deleteReservation}><GiTrashCan/>Delete</button>
            </div>
        </div>
    )
}
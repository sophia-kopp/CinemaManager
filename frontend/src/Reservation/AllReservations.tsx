import {useEffect, useState} from "react";
import type {Reservation} from "../model/Reservation.ts";
import axios from "axios";
import ReservationCard from "./ReservationCard.tsx";

export default function AllReservations() {

    const [reservations, setReservations] = useState<Reservation[]>([]);

    function loadAllReservations() {
        axios.get("api/reservations")
            .then(r => setReservations(r.data))
            .catch(e => console.log(e))
    }

    function updateReservations() {
        loadAllReservations();
    }

    useEffect(() => {
        loadAllReservations();
    }, []);

    return (
        <>
            <h3>All Reservations</h3>
            {reservations.map(reservation =>
                <ReservationCard key={reservation.id} reservation={reservation}
                                 updateReservations={updateReservations}/>
            )}
        </>
    )
}
import type {CinemaHall} from "../../model/CinemaHall.ts";
import './HallCard.css';
import Seat from "./Seats/Seat.tsx";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import type {SeatPosition} from "../../model/SeatPosition.ts";

type HallCardProps = {
    hall: CinemaHall
    deleteHall?: (id: string) => void
    forReservation?: boolean
    setSeatPositions?: (seats: SeatPosition[]) => void
}
export default function HallCard(props: Readonly<HallCardProps>) {

    const nav = useNavigate();

    const [rows, setRows] = useState<string[]>([]);
    const [seatNumber, setSeatNumber] = useState<string[]>([]);
    const [activeSeat, setActiveSeat] = useState<string>("");

    const selectedSeats = [];

    function onEditHall() {
        nav("/editHall/" + props.hall.id);
    }

    function deleteHall() {
        axios.delete("/api/halls/" + props.hall.id)
            .then(() => deleteHall())
            .catch(e => console.log(e))
    }

    function loadSeatNumber() {
        const seatNumbersArray: string[] = [];
        for (let i = 1; i <= props.hall.seatsPerRow; i++) {
            seatNumbersArray.push(String(i));
        }
        setSeatNumber(seatNumbersArray);
    }

    function loadRowLetter() {
        const rowLettersArray: string[] = [];
        for (let i = 1; i <= props.hall.seatsPerRow; i++) {
            rowLettersArray.push(String.fromCodePoint(96 + i));
        }
        setRows(rowLettersArray);
    }

    function onSelectSeatPositions(e: string) {
        if (selectedSeats.includes(e)) {
            selectedSeats.splice(selectedSeats.indexOf(e), 1)
            setActiveSeat("")
        } else {
            selectedSeats.push(e)
            setActiveSeat("activeSeat")
        }
        console.log(selectedSeats)
    }

    function onConfirmSeatPosition(e) {
        e.preventDefault();
        console.log("set selectedSeat", selectedSeats);
        props.setSeatPositions(selectedSeats);
    }

    useEffect(() => {
        loadRowLetter();
        loadSeatNumber();
    }, []);

    return (
        <div className={"hall-card"}>
            <h3>{props.hall.name}</h3>
            <div className={"rows"}>
                <div className={"screen"}>
                    <h5>Screen</h5>
                </div>
                {[...rows].map((row) =>
                    <div key={row} className={"seatsPerRow"}>
                        {row.toUpperCase()}
                        {[...seatNumber].map((seat) => {
                                return <div key={seat} onClick={() => onSelectSeatPositions(row + seat)}>
                                    <Seat activeSeat={activeSeat} seatNumber={seat}/>
                                </div>
                            }
                        )}
                        {row.toUpperCase()}
                    </div>
                )}
            </div>
            {props.forReservation &&
                <button onClick={onConfirmSeatPosition}>Confirm Seats</button>}
            {props.forReservation === false &&
                <div className={"buttons"}>
                    <button onClick={onEditHall}>Edit</button>
                    <button onClick={deleteHall}>Delete</button>
                </div>
            }
        </div>
    )
}
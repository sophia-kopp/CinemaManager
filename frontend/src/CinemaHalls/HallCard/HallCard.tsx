import type {CinemaHall} from "../../model/CinemaHall.ts";
import './HallCard.css';
import Seat from "./Seats/Seat.tsx";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {type FormEvent, useEffect, useState} from "react";
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

    const [selectedSeats, setSelectedSeats] = useState<string[]>([]);
    const [displayedSeats, setDisplayedSeats] = useState<string[]>([]);


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
            const currentSeats = selectedSeats.splice(selectedSeats.indexOf(e), 1)
            setSelectedSeats(currentSeats);
            const element = document.getElementById(e)
            element.style.backgroundColor = "darkblue"
            console.log("e", element)
        } else {
            const currentSeats = selectedSeats;
            currentSeats.push(e)
            setSelectedSeats(currentSeats);
            const element = document.getElementById(e)
            element.style.backgroundColor = "green"
        }
        console.log(selectedSeats)
    }


    function loadDisplayedSeats(){

    }

    function onConfirmSeatPosition(e: FormEvent) {
        e.preventDefault();
        if (selectedSeats !== undefined) {
            props.setSeatPositions(selectedSeats.map((s): SeatPosition =>
                ({
                    row: s.substring(0, 1),
                    seatNumber: s.substring(1)
                })));
        }
    }

    useEffect(() => {
        loadRowLetter();
        loadSeatNumber();
    }, []);

    useEffect(() => {
        loadDisplayedSeats()
    }, [selectedSeats]);


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
                                return <div className={"seats"} key={seat} id={row+seat} onClick={() => onSelectSeatPositions(row + seat)}>
                                    <Seat seatNumber={seat}/>
                                </div>
                            }
                        )}
                        {row.toUpperCase()}
                    </div>
                )}
            </div>
            {props.forReservation &&
                <div className={"selected-seats"}>
                    {selectedSeats.map(x =>
                        <p>Row: {x.substring(0, 1).toUpperCase()} - Seat: {x.substring(1)}  </p>)}
                    <button onClick={onConfirmSeatPosition}>Confirm Seats</button>
                </div>
            }
            {props.forReservation === false &&
                <div className={"buttons"}>
                    <button onClick={onEditHall}>Edit</button>
                    <button onClick={deleteHall}>Delete</button>
                </div>
            }
        </div>
    )
}
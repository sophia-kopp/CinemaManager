import {type FormEvent, useEffect, useState} from "react";
import type {Presentation} from "../model/Presentation.ts";
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import PresentationCard from "../Schedule/PresentationCard/PresentationCard.tsx";
import HallCard from "../CinemaHalls/HallCard/HallCard.tsx";
import type {CinemaHall} from "../model/CinemaHall.ts";
import type {SeatPosition} from "../model/SeatPosition.ts";
import "./ReservationForm.css";


export default function ReservationForm() {

    const [presentation, setPresentation] = useState<Presentation>({
        id: "",
        startsAt: new Date(),
        movieName: "",
        duration: 0,
        cinemaHall: {id: "", name: "", rows: 0, seatsPerRow: 0},
    });
    const [price, setPrice] = useState<number>(0);
    const [discount, setDiscount] = useState<number>(0);
    const [priceTotal, setPriceTotal] = useState<number>(0);
    const [amountOfSeats, setAmountOfSeats] = useState<number>(0);
    const [hall, setHall] = useState<CinemaHall | undefined>(undefined);
    const [seatPositions, setSeatPositions] = useState<SeatPosition[]>([]);
    const [disableSubmitButton, setDisableSubmitButton] = useState<boolean>(false);

    const param = useParams();
    const nav = useNavigate();

    function loadPresentation() {
        axios.get("/api/presentations/" + param.id)
            .then(r => setPresentation(r.data))
            .catch(e => console.log(e))
    }

    function loadHall() {
        if (presentation) {
            axios.get("/api/halls/" + presentation.cinemaHall.id)
                .then(r => setHall(r.data))
                .catch(e => console.log(e))
        }
    }

    function saveReservation(e: FormEvent) {
        e.preventDefault();
        axios.post("/api/reservations",
            {
                presentation: presentation,
                amountOfSeats: seatPositions.length,
                seatPositions: seatPositions,
                price: price
            })
            .then(() => nav("/allReservations"))
            .catch(e => console.log(e))
    }

    function onDisableSubmitButton() {
        if (seatPositions.length === 0) {
            setDisableSubmitButton(true)
        } else {
            setDisableSubmitButton(false)
        }
    }

    useEffect(() => {
        loadPresentation()
    }, []);

    useEffect(() => {
        if (presentation) {
            loadHall()
        }
    }, [presentation]);

    useEffect(() => {
        setPrice(amountOfSeats * 10)
        setDiscount(amountOfSeats * 10)
    }, [amountOfSeats]);
    useEffect(() => {
        setAmountOfSeats(seatPositions.length)

    }, [seatPositions]);

    useEffect(() => {
        setPriceTotal(price - discount)
    }, [discount, price]);
    useEffect(() => {
        onDisableSubmitButton()
    }, [seatPositions]);

    return (
        <div className={"reservation-form"}>
            <PresentationCard presentation={presentation} displayInfo={true}/>
            <form onSubmit={saveReservation}>
                {hall !== undefined &&
                    <HallCard hall={hall} forReservation={true} setSeatPositions={setSeatPositions}/>
                }
                <button disabled={disableSubmitButton} type={"submit"}>Reserve</button>
            </form>
            <p>Amount of Seats: {amountOfSeats}</p>
            <p>Price: {price}</p>
            <p>Discount: {price}</p>
            <p>Total: {priceTotal}</p>
        </div>
    )
}
import type {Presentation} from "../../model/Presentation.ts";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import './PresentationCard.css';
import {GiPencil, GiTrashCan, GiClapperboard} from "react-icons/gi";

type PresentationCardProps = {
    presentation: Presentation
    deletePresentation?: (id: string) => void
    displayInfo?: boolean
}

export default function PresentationCard(props: Readonly<PresentationCardProps>) {

    const nav = useNavigate();
    const [date, setDate] = useState<string>("");

    function deletePresentation() {
        axios.delete("api/presentations/" + props.presentation.id)
            .then(() => deletePresentation())
            .catch(e => console.log(e))
    }

    function editPresentation() {
        nav("/editPresentation/" + props.presentation.id);
    }

    function calculateEndsAt() {
        setDate((props.presentation.startsAt.toString().substring(0, 10)));
    }

    function onMakeReservation() {
        nav("/newReservation/" + props.presentation.id);
    }


    useEffect(() => {
        calculateEndsAt()
    }, []);

    return (
        <div className={"presentation-card"}>
            <p>Movie: {props.presentation.movieName}</p>
            <p>Day: {date}</p>
            <p>Starts at: {props.presentation.startsAt.toString().substring(11, 16)}</p>
            <p>Duration: {props.presentation.duration}</p>
            {!props.displayInfo &&
                <div>
                    <button onClick={deletePresentation}>
                        <GiTrashCan/> Delete
                    </button>
                    <button onClick={editPresentation}>
                        <GiPencil/> Edit
                    </button>
                    <button onClick={onMakeReservation}>
                        <GiClapperboard/> Book this movie
                    </button>
                </div>
            }
        </div>
    )
}
import type {Presentation} from "../../model/Presentation.ts";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

type PresentationCardProps = {
    presentation: Presentation
    deletePresentation: (id: string) => void
}

export default function PresentationCard(props: Readonly<PresentationCardProps>) {

    const nav= useNavigate();
    const [date, setDate] = useState<string>("");

    function deletePresentation() {
        axios.delete("api/presentations/" + props.presentation.id)
            .then(() => deletePresentation())
            .catch(e => console.log(e))
    }
    function editPresentation(){
        nav("/editPresentation/" + props.presentation.id);
    }

function calculateEndsAt(){
        setDate((props.presentation.startsAt.toString().substring(0, 10)));
}

    useEffect(() => {
        calculateEndsAt()
    }, []);

    return (
        <div>
            <p>Movie: {props.presentation.movieName}</p>
            <p>Day: {date}</p>
            <p>Starts at: {props.presentation.startsAt.toString()}</p>
            <p>Ends at: {props.presentation.startsAt.toString()}</p>
            <p>Duration: {props.presentation.duration}</p>
            <button onClick={deletePresentation}>Delete</button>
            <button onClick={editPresentation}>Edit</button>
        </div>
    )
}
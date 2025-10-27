import type {Presentation} from "../../model/Presentation.ts";
import axios from "axios";
import {useNavigate} from "react-router-dom";

type PresentationCardProps = {
    presentation: Presentation
    deletePresentation: (id: string) => void
}

export default function PresentationCard(props: Readonly<PresentationCardProps>) {

    const nav= useNavigate();

    function deletePresentation() {
        axios.delete("api/presentations/" + props.presentation.id)
            .then(() => deletePresentation())
            .catch(e => console.log(e))
    }


    return (
        <div>
            <p>Movie: {props.presentation.movieName}</p>
            <p>Movie: {props.presentation.startsAt}</p>
            <p>Movie: {props.presentation.endsAt}</p>
            <button onClick={deletePresentation}>Delete</button>
        </div>
    )
}
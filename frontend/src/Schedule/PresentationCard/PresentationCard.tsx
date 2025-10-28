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
    function editPresentation(){
        nav("/editPresentation/" + props.presentation.id);
    }


    return (
        <div>
            <p>Movie: {props.presentation.movieName}</p>
            <p>Starts at: {props.presentation.startsAt}</p>
            <p>Duration: {props.presentation.duration}</p>
            <button onClick={deletePresentation}>Delete</button>
            <button onClick={editPresentation}>Edit</button>
        </div>
    )
}
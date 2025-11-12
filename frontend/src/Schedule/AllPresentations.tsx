import {useEffect, useState} from "react";
import type {Presentation} from "../model/Presentation.ts";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import PresentationCard from "./PresentationCard/PresentationCard.tsx";
import './AllPresentations.css';

export default function AllPresentations() {
    const [presentations, setPresentations] = useState<Presentation[]>([]);
    const [deletePresentation, setDeletePresentation] = useState<string>("");

    const nav = useNavigate();

    useEffect(() => {
        getAllPresentations();
    }, []);

    useEffect(() => {
        getAllPresentations();
    }, [deletePresentation]);

    function getAllPresentations() {
        axios.get("api/presentations")
            .then(r => setPresentations(r.data))
            .catch(e => console.log(e));
    }

    function addNewPresentation() {
        nav("/newPresentation");
    }

    return (
        <div className={"all-presentations"}>
            <div className={"presentations-header"}>

            <h3>All Presentations</h3>
            <button onClick={addNewPresentation}>Add New Presentation</button>
            </div>
            {presentations.map(p =>
                <PresentationCard presentation={p} deletePresentation={setDeletePresentation}/>
            )}
        </div>
    )
}
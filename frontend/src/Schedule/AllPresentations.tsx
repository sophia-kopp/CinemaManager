import {useEffect, useState} from "react";
import type {Presentation} from "../model/Presentation.ts";
import {useNavigate} from "react-router-dom";
import axios from "axios";

export default function AllPresentations(){
    const [presentations, setPresentations] = useState<Presentation[]>([]);

    const nav = useNavigate();

    useEffect(() => {
        getAllPresentations();
    }, []);

    function getAllPresentations(){
        axios.get("api/presentations")
            .then(r=> setPresentations(r.data))
            .catch(e=> console.log(e));
    }

    function addNewPresentation(){
        nav("/newPresentation");
    }

    return (
        <>
            <h3>All Presentations</h3>
            <button onClick={addNewPresentation}>Add New Presentation</button>
            {presentations.map(p=>
                <p>{p.movie}</p>
            )}
        </>
    )
}
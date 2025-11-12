import {useEffect, useState} from "react";
import type {Presentation} from "../model/Presentation.ts";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import PresentationCard from "./PresentationCard/PresentationCard.tsx";

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
    const [userRole, setUserRole] = useState<string>("");

    const loadUser = () => {
        axios.get("api/auth/me")
            .then(r => {
                setUserRole(r.data.role)
            })
            .catch((e) => console.log(e));
    }

    useEffect(() => {
        loadUser()
    }, []);

    return (
        <>
            <h3>All Presentations</h3>
            {userRole==="ADMIN" &&
                <button onClick={addNewPresentation}>Add New Presentation</button>
            }
            {presentations.map(p =>
                <PresentationCard presentation={p} deletePresentation={setDeletePresentation}/>
            )}
        </>
    )
}
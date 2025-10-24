import {type FormEvent, useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import type {CinemaHallDto} from "../model/CinemaHall.ts";

export default function NewHallForm() {

    const [name, setName] = useState<string>("");
    const [rows, setRows] = useState<number>(0);
    const [seatsPerRow, setSeatsPerRow] = useState<number>(0);
    const [hall, setHall] = useState<CinemaHallDto>();

    const nav = useNavigate();
    const param = useParams();

    function getExistingHall() {
        axios.get("/api/halls/" + param.id)
            .then(r => {
                setHall(r.data);
            })
            .catch(e => console.log(e))
    }

    function handlePostForm(e: FormEvent) {
        e.preventDefault();

        if (hall === undefined) {
            axios.post("/api/halls", {
                name: name,
                rows: rows,
                seatsPerRow: seatsPerRow
            })
                .then(response => console.log(response))
                .then(() => nav("/allHalls"))
                .catch(e => console.log(e));
        }
        if (hall !== undefined) {
            axios.put("/api/halls/" + param.id, {
                name: name,
                rows: rows,
                seatsPerRow: seatsPerRow
            })
                .then(response => console.log(response))
                .then(() => nav("/allHalls"))
                .catch(e => console.log(e));
        }


    }

    useEffect(() => {
        if (param !== null) {
            getExistingHall()
        }
    }, []);

    useEffect(() => {
        setName(hall?.name ?? "");
        setRows(hall?.rows ?? 0);
        setSeatsPerRow(hall?.seatsPerRow ?? 0);
    }, [hall]);

    return (
        <form onSubmit={handlePostForm}>
            <label>Name:
                <input defaultValue={name} onChange={e => setName(e.target.value)}/>
            </label>
            <label>How many rows do you want?
                <input defaultValue={hall?.rows} onChange={e => setRows(Number(e.target.value))}/>
            </label>
            <label>How many seats per row do you want?
                <input defaultValue={hall?.seatsPerRow} onChange={e => setSeatsPerRow(Number(e.target.value))}/>
            </label>
            {hall === undefined && <button type={"submit"}>Submit</button>}
            {hall !== undefined && <button type={"submit"}>Edit</button>}
        </form>
    )
}
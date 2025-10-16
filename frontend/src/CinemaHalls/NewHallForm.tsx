import {type FormEvent, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function NewHallForm(){

    const [name, setName] = useState<string>("");
    const [rows, setRows] = useState<string>("");
    const [seatsPerRow, setSeatsPerRow] = useState<string>("");

    const nav = useNavigate();

    function addNewHall(e:FormEvent){
        e.preventDefault();

        axios.post("/api/halls", {name: name, rows: parseInt(rows), seatsPerRow: parseInt(seatsPerRow)})
            .then(response => console.log(response))
            .then(()=>nav("/allHalls"))
            .catch(e=> console.log(e));
    }

    return (
        <>
            <form onSubmit={addNewHall}>
                <label>Name:
                    <input onChange={e=> setName(e.target.value)}/>
                </label>
                <h2>{name}</h2>
                <label>How many rows do you want?
                    <input onChange={e=> setRows(e.target.value)}/>
                </label>
                <h2>{rows}</h2>
                <label>How many seats per row do you want?
                    <input onChange={e=> setSeatsPerRow(e.target.value)}/>
                </label>
                <h2>{seatsPerRow}</h2>
                <button type={"submit"}>Submit</button>
            </form>
        </>
    )
}
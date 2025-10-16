import type {CinemaHall} from "../../model/CinemaHall.ts";
import './HallCard.css';
import Seats from "./Seats/Seats.tsx";
import axios from "axios";

type HallCardProps = {
    hall: CinemaHall
    deleteHall: (id: string)=>void
}
export default function HallCard(props: Readonly<HallCardProps>) {

    function deleteHall(){
        axios.delete("/api/halls/"+ props.hall.id)
            .then(() => deleteHall())
            .catch(e => console.log(e))
    }

    return (
        <div className={"hall-card"}>
            <h3>Id: {props.hall.id}</h3>
            <h3>Name: {props.hall.name}</h3>
            <div className={"rows"}>
                <hr/>
                {[...Array(props.hall.rows)].map((row) =>
                    <div className={"seatsPerRow"} key={row}>
                        {[...Array(props.hall.seatsPerRow)].map((seat) =>
                            <Seats key={seat}/>
                        )}
                    </div>
                )}
            </div>
            <button onClick={deleteHall}>Delete</button>
        </div>
    )
}
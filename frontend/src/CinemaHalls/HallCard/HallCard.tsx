import type {CinemaHall} from "../../model/CinemaHall.ts";
import './HallCard.css';
import Seat from "./Seats/Seat.tsx";
import axios from "axios";

type HallCardProps = {
    hall: CinemaHall
    deleteHall: (id: string) => void
}
export default function HallCard(props: Readonly<HallCardProps>) {

    function deleteHall() {
        axios.delete("/api/halls/" + props.hall.id)
            .then(() => deleteHall())
            .catch(e => console.log(e))
    }

    return (
        <div className={"hall-card"}>
            <h3>Id: {props.hall.id}</h3>
            <h3>{props.hall.name}</h3>
            <div className={"rows"}>
                <div className={"screen"}>
                    <h5>Screen</h5>
                </div>
                {[...Array(props.hall.rows)].map((row) =>
                    <div key={row} className={"seatsPerRow"}>
                        {[...Array(props.hall.seatsPerRow)].map((seat) =>
                            <Seat key={seat}/>
                        )}
                    </div>
                )}
            </div>
            <div className={"buttons"}>
                <button onClick={deleteHall}>Delete</button>
            </div>
        </div>
    )
}
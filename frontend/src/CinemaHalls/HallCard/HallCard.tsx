import type {CinemaHall} from "../../model/CinemaHall.ts";
import './HallCard.css';
import Seats from "./Seats/Seats.tsx";

type HallCardProps = {
    hall: CinemaHall
}
export default function HallCard(props: Readonly<HallCardProps>) {
    return (
        <div className={"hall-card"}>
            <h3>Id: {props.hall.id}</h3>
            <h3>Name: {props.hall.name}</h3>
            <div className={"rows"}>
                <hr/>
                {[...Array(props.hall.rows)].map(() =>
                    <div className={"seatsPerRow"}>
                        {[...Array(props.hall.seatsPerRow)].map(() =>
                            <Seats/>
                        )}
                    </div>
                )}
            </div>
        </div>
    )
}
import type {CinemaHall} from "../../model/CinemaHall.ts";
import './HallCard.css';

type HallCardProps = {
    hall: CinemaHall
}
export default function HallCard(props: Readonly<HallCardProps>) {
    return (
        <div className={"hall-card"}>
            <h3>Id: {props.hall.id}</h3>
            <h3>Name: {props.hall.name}</h3>
        </div>
    )
}
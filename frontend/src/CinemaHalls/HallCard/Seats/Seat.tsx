import './Seats.css'

export type SeatProps  = {
    seatNumber: string
    activeSeat: string
}

export default function Seat(props: Readonly<SeatProps>) {
    return (
        <span className="circle" id={props.activeSeat}>{props.seatNumber}</span>
    )
}
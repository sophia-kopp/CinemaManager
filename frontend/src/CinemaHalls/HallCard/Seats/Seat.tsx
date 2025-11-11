import './Seats.css'

export type SeatProps  = {
    seatNumber: string
}

export default function Seat(props: Readonly<SeatProps>) {
    return (
        <span className="circle">{props.seatNumber}</span>
    )
}
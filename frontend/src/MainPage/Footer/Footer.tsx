import './Footer.css';
import {Link} from "react-router-dom";

export default function Footer() {
    return (
        <div className={"footer"}>
            <Link to={"/"}>Impressum</Link>
        </div>
    )
}
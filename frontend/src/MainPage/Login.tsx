import axios from "axios";
import {useEffect} from "react";
import {useNavigate} from "react-router-dom";

type LoginProps = {
    setUser: (user: string | undefined | null) => void
}

export default function Login(props: Readonly<LoginProps>) {

    const nav = useNavigate();

    function login() {
        const host: string = window.location.host === "localhost:5173" ?
            "http://localhost:8080"
            :
            window.location.origin;
        window.open(host + "/oauth2/authorization/github", "_self")
    }

    const loadUser = () => {
        axios.get("api/auth/me")
            .then(r => {
                props.setUser(r.data.username)
                nav("/")
            })
            .catch(() => props.setUser(null));
    }

    useEffect(() => {
        loadUser()
    }, []);

    return (
        <>
            <h2>Welcome!</h2>
            <p>Please log in:</p>
            <button onClick={login}>Login</button>
        </>
    )
}
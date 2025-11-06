import axios from "axios";
import {useEffect} from "react";
export default function Home() {

    function login() {
        const host: string = window.location.host === "localhost:5173" ?
            "http://localhost:8080"
            :
            window.location.origin;
        window.open(host + "/oauth2/authorization/github", "_self")
    }

    const loadUser = ()=>{
        axios.get("api/auth/me")
            .then(r=>console.log(r.data))
            .catch(e=> console.log(e));
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
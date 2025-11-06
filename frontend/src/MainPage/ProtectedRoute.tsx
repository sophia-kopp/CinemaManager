import {Navigate, Outlet} from "react-router-dom";

type ProtectedRouteProps = {
    user: string | undefined | null
}

export default function ProtectedRoute(props: Readonly<ProtectedRouteProps>) {



    return (
        props.user ? <Outlet/> : <Navigate to={"/login"}/>
    )
}
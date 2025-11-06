type HomeProps = {
    user: string
}

export default function Home(props:Readonly<HomeProps>) {


    return (
        <>
            <h2>Welcome {props.user}!</h2>
        </>
    )
}
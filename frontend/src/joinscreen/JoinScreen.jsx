import {Component} from "react";
import {Link} from "react-router-dom";

export class JoinScreen extends Component {

    render() {

        return (
            <>
                <form className={"screen"} onSubmit={(event) => {
                    this.joinGame(event)
                }}>
                    <div className={"screenHeading"}>
                        <h3>{"Join a Game"}</h3>
                    </div>
                    <label>Game Code: <input name={"gameCode"}/></label>
                    <button type={"submit"}>Join</button>
                </form>
                <Link to={"/host"}>Host</Link>
            </>
        );
    }

    joinGame(e) {

        e.preventDefault()
        let sessionCode = e.target["gameCode"].value
        fetch("catch.me.roboter5123.com/api/session/" + sessionCode)
            .then((response) => response.json())
            .catch((error) => console.log(error.stack))
            .then((session) => {
                return {code: session["sessionCode"], game: session["gameName"], role: "player"}
            })
            .then((session) => this.props.joinGame(session))
    }
}


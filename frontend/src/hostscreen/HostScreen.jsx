import {Component} from "react";
import {Games} from "./Games";
import {https} from "../configurations/ApiUrl";

export class HostScreen extends Component {

    render() {

        if (this.props.session.code === undefined) {

            return (
                <div className={"screen"}>
                    <div className={"screenHeading"}>
                        <h3>{"Host a Game"}</h3>
                    </div>
                    <Games games={this.props.games} hostGame={this.hostGame.bind(this)}></Games>
                </div>
            );
        }
    }

    hostGame(gameName) {

        fetch(https+"session", {method: "POST", body: gameName})
            .then((response) => response.json())
            .then((session) => {return {code: session["sessionCode"], game: session["gameName"], role: "player"}})
            .then((session) => this.props.joinGame(session))

    }
}


import {Component} from "react";
import {Games} from "./Games";

export class HostScreen extends Component {

    render() {

        if (this.props.session.code === undefined){

            return (
                <Games games={this.props.games} hostGame={this.hostGame.bind(this)}></Games>
            );
        }
    }

    hostGame(gameName) {

        fetch("/session",{method:"POST",body:gameName})
            .then((response)=> response.json())
            .then((session)=>{return {code:session["sessionCode"], game:session["gameName"], role:"player"}})
            .then((session)=>this.props.joinGame(session))

    }
}


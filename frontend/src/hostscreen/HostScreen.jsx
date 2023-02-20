import {Component} from "react";
import {Games} from "./Games";
import {Chat} from "./chat/Chat";

export class HostScreen extends Component {

    render() {

        if (this.props.session.code === undefined){

            return (
                <Games games={this.props.games} hostGame={this.hostGame.bind(this)}></Games>
            );
        }

        switch (this.props.session.game){

            case "chat":
                return <Chat code={this.props.session.code}></Chat>
        }
    }

    hostGame(gameName) {

        fetch("/session",{method:"POST",body:gameName})
            .then((response)=> response.json())
            .then((session)=>{return {code:session["sessionCode"], game:session["gameName"], host:true}})
            .then((session)=>this.props.joinGame(session))

    }
}


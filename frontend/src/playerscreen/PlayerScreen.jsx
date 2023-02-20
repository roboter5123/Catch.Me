import {Component} from "react";

export class PlayerScreen extends Component {

    render() {

        return (
            <form className={"screen"} onSubmit={(event)=>{this.joinGame(event)}}>
                <label>Game Code: <input name={"gameCode"}/></label>
                <button type={"submit"}>Join</button>
            </form>
        );
    }

    joinGame(e){

        e.preventDefault()
        let sessionCode = e.target["gameCode"].value
        fetch("/session/"+sessionCode)
            .then((response)=> response.json())
            .catch((error)=>console.log(error.stack))
            .then((session)=>{console.log(session)
                return {code:session["sessionCode"], game:session["gameName"], host:false}})
            .then((session)=>this.props.joinGame(session))
    }
}


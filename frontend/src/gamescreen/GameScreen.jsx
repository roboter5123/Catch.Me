import {Component} from "react";
import {Chat} from "./Chat";

export class GameScreen extends Component {
    render() {

        console.log(this.props.session)
        switch (this.props.session.game) {

            case "chat":
                return (
                    <div className={"screen"}>
                        <Chat session={this.props.session}/>
                    </div>)
        }
    }
}
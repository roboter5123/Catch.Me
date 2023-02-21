import {Component} from "react";
import {Chat} from "./Chat";

export class GameScreen extends Component {

    render() {

        return (
            <div className={"screen"}>
                <div className={"screenHeading"}>
                    <h3>{"Chat"}</h3>
                    <h4>{this.props.session.code}</h4>
                </div>
                {this.renderSwitch()}
            </div>)


    }

    renderSwitch() {

        switch (this.props.session.game) {

            case "chat":
                return <Chat session={this.props.session} disconnect={this.props.disconnect}/>

            default:
                return <div></div>

        }
    }
}

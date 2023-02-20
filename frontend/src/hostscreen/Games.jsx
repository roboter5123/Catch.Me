import { thumbnails } from "../thumbnails.js"
import {Component} from "react";

export class Games extends Component {
    render() {
        return (
            <div id={"games"} className={"screen"}>
                {this.props.games.map((name) =>
                    <Game key={name} name={name} hostGame={()=>this.props.hostGame(name)}></Game>
                )}
            </div>
        );
    }
}

export function Game(props){

    return(
        <div onClick={props.hostGame} className={"gameThumb"}>
            <h3 className={"gameThumbName"}>{props.name}</h3>
            <img src={thumbnails[props.name]} className={"gameThumbNailImage"} alt={props.name}/>
        </div>
    )


}
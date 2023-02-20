import {Component} from "react";
import {HostScreen} from "./hostscreen/HostScreen";
import {JoinScreen} from "./joinscreen/JoinScreen";
import "./style.css"
import {GameScreen} from "./gamescreen/GameScreen";

export default class App extends Component {

    constructor(props) {
        super(props);
        this.state =
            {
                session:
                    {
                        code: undefined,
                        game: undefined,
                        role: undefined
                    },
                games: [],
                subdomain: window.location.host.split(".")[0]
            }
    }

    componentDidMount() {

        this.getGames();
    }

    getGames() {

        fetch("/games")
            .then((response) => response.json())
            .then((data) => {
                this.setState({games: data})
            })
    }

    joinGame(session) {
        console.log(session)
        this.setState({session: session})
    }

    renderGameScreen(){

        return (
            <div className={"App"}>
                <GameScreen session={this.state.session}/>
            </div>
        )
    }

    renderHostScreen() {

        return (
            <div className="App">
                <HostScreen session={this.state.session} games={this.state.games}
                            joinGame={this.joinGame.bind(this)}/>
            </div>
        )
    }

    renderJoinScreen() {

        return (
            <div className="App">
                <JoinScreen joinGame={this.joinGame.bind(this)}/>
            </div>
        );
    }

    render() {

        if (this.state.session.code !== undefined) {

            this.renderGameScreen()
        }

        if (this.state.subdomain === "host") {

            this.renderHostScreen()
        }

        return this.renderJoinScreen()
    }


}
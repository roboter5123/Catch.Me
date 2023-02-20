import {Component} from "react";
import {HostScreen} from "./hostscreen/HostScreen";
import {PlayerScreen} from "./playerscreen/PlayerScreen";
import "./style.css"
import {GameScreen} from "./GameScreen";

export default class App extends Component {

    constructor(props) {
        super(props);
        this.state =
            {
                session:
                    {
                        code: undefined,
                        game: undefined,
                        host: undefined
                    },
                games: [],
                subdomain: window.location.host.split(".")[0]
            }
    }

    componentDidMount() {

        this.getGames();
    }

    render() {

        if (this.state.subdomain === "host") {

            return (
                <div className="App">
                    <HostScreen session={this.state.session} games={this.state.games}
                                joinGame={this.joinGame.bind(this)}>
                    </HostScreen>
                </div>)

        } else if (this.state.session.code !== undefined) {

            return (
                <div className={"App"}>
                    <GameScreen session={this.state.session}></GameScreen>
                </div>
            )
        } else {
            return (
                <div className="App">
                    <PlayerScreen joinGame={this.joinGame.bind(this)}/>
                </div>
            );
        }
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
}
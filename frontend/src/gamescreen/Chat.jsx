import {Component} from "react";
import {Client} from '@stomp/stompjs';
import websocket from 'websocket';

Object.assign(global, {WebSocket: websocket.w3cwebsocket});

Object.assign(global, {WebSocket});


export class Chat extends Component {

    constructor(props) {
        super(props);
        this.state = {connection: null}
    }

    componentDidMount() {

        this.initializeChat();
    }

    render() {

        if (this.state.player === undefined) {

            return (
                <form>
                    <input id={"playerName"} type={"text"}/>
                    <input type={"button"} onClick={this.setPlayer.bind(this)}
                           onTouchStart={this.setPlayer.bind(this)}/>
                </form>)
        }

        return (
            <form>
                <div id={"history"}></div>
                <input id={"message"} type={"text"}/> <input type={"button"} onClick={this.sendMessage.bind(this)}
                                                             onTouchStart={this.sendMessage.bind(this)}/>
            </form>
        );
    }

    initializeChat() {

        console.log("initializing")

        const client = new Client({

            brokerURL: "ws://catch.me.roboter5123.com/api/stomp-endpoint",
            debug: (str) => console.log(str)
        })
        client.onWebSocketClose = this.disconnect
        client.activate()
        let newState = this.state
        newState.connection = client;
        this.setState(newState);
    }

    setPlayer() {

        let player = {name: document.getElementById("playerName").value}
        let newState = this.state
        newState.player = player
        this.setState(newState);
        let message = {player: this.state.player, command:{type:"addPlayer", args:[this.state.player.name]}}
        console.log(message)
        this.state.connection.publish({destination: '/app/' + this.props.session.code, body: JSON.stringify(message)})
        this.subscribe(this.state.connection)
    }

    subscribe(client) {

        let sessionCode = this.props.session.code
        client.subscribe("/topic/" + sessionCode, (frame) => this.receiveMessage(frame))
    }

    receiveMessage(frame) {

        console.log(frame)
        let chatMessage = JSON.parse(frame.body);

        if (chatMessage.messages !== undefined) {

            chatMessage.messages.forEach((message) => this.appendSingleMessage(message))
            return
        }

        this.appendSingleMessage(chatMessage)

    }

    appendSingleMessage(chatMessage) {

        let appendMessage = document.createElement("p");
        appendMessage.innerText = chatMessage.player.name + ": " + chatMessage.message;

        document.getElementById("history").append(appendMessage);
    }

    sendMessage(e) {

        e.preventDefault()
        let sessionCode = this.props.session.code;
        let message =
            {
                player: this.state.player,
                command:
                    {
                        type: "addMessage",
                        args: [document.getElementById("message").value]
                    }
            }
        this.state.connection.publish({destination: '/app/' + sessionCode, body: JSON.stringify(message)});
    }

    disconnect() {

        this.props.disconnect()
    }




}
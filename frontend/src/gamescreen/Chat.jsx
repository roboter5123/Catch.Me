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

        if (this.state.connection !== null) {

            return
        }

        this.setState({connection: this.initializeChat()})
    }

    render() {

        return (
            <form>
                <div id={"history"}></div>
                <input id={"message"} type={"text"}/> <input type={"button"} onClick={this.sendMessage.bind(this)}/>
            </form>
        );
    }

    initializeChat() {

        const client = new Client({

            brokerURL: "ws://localhost:8080/stomp-endpoint",
            debug: (str) => console.log(str)
        })

        client.onConnect = this.subscribe.bind(this, client)
        client.onWebSocketClose = this.disconnect
        client.activate()
        return client
    }

    subscribe(client) {

        let sessionCode = this.props.session.code
        client.subscribe("/topic/" + sessionCode, (frame) => this.receiveMessage(frame))
    }

    disconnect() {

        this.props.disconnect()
    }

    sendMessage(e) {

        e.preventDefault()
        let sessionCode = this.props.session.code;
        let message =
            {
                player:
                    {
                        name: ""
                    },
                command:
                    {
                        type: "add",
                        args: [document.getElementById("message").value]
                    }
            }
        this.state.connection.publish({destination: '/app/' + sessionCode, body: JSON.stringify(message)});
    }

    receiveMessage(frame) {

        let chatMessage = JSON.parse(frame.body);

        if (chatMessage.messages !== undefined){

            chatMessage.messages.forEach((message)=> this.appendSingleMessage(message))
            return
        }

        this.appendSingleMessage(chatMessage)

    }

    appendSingleMessage(chatMessage){

        let appendMessage = document.createElement("p");
        appendMessage.innerText = chatMessage.player.name + ": " + chatMessage.message;

        document.getElementById("history").append(appendMessage);
    }


}
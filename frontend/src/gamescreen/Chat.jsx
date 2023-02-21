import {Component} from "react";
import {Client} from '@stomp/stompjs';
import websocket from 'websocket';

Object.assign(global, { WebSocket: websocket.w3cwebsocket });

Object.assign(global, {WebSocket});


export class Chat extends Component {

    constructor(props) {
        super(props);
        this.state = {connection:null}
    }

    componentDidMount() {

        if (this.state.connection !== null){

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

    subscribe(client){

        let sessionCode = this.props.session.code
        client.subscribe("/topic/"+ sessionCode,(frame)=> {

            document.getElementById("history").innerText = JSON.parse(frame.body).body
        })
    }

    disconnect(){

        this.props.disconnect()
    }

    sendMessage(e) {

        e.preventDefault()
        let sessionCode = this.props.session.code;
        let message = {command: "send",args: document.getElementById("message").value}
        console.log(message)
        this.state.connection.publish({ destination: '/topic/'+ sessionCode, body: JSON.stringify(message)});
    }

    receiveMessage(message) {

        console.log(message)
        let appendMessage = message.body+"<br>"
        let history = document.getElementById("history")
        history.append(appendMessage)
    }
}
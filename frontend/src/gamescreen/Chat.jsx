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

    render() {

        return (
            <div></div>
        );
    }

    componentDidMount() {

        this.initializeChat()
    }

    initializeChat() {

        let sessionCode = this.props.session.code
        const client = new Client({

            brokerURL: "ws://localhost:8080/stomp-endpoint",
            debug: (str) => console.log(str)
        })

        client.onConnect = subscribe
        client.activate()
        this.state.connection = client


        function subscribe(){

            client.subscribe("/topic/abc",(msg) => console.log(msg))
        }
    }
}
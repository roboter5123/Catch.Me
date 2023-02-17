let stompClient = null
let gamecode = null;

document.getElementById("createChat").addEventListener("click", async () => {

    fetch("http://localhost:8080/session",
        {
            method: "POST",
            mode: "same-origin",
            body: "chat"
        }).then((response) => response.json()).then((data) => {
        gamecode = data["sessionCode"]
        let socket = new SockJS("http://localhost:8080/stomp-endpoint");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, (frame) => {

            stompClient.subscribe("/topic/" + gamecode, (response) => {
                response = JSON.parse(response.body).body
                document.getElementById("messages").innerText = response
            })
        })

        document.getElementById("sessionCode").innerText = "SessionCode: " + gamecode
    })
})

document.getElementById("sendMessage").addEventListener("click", () => {

        let message = {body : document.getElementById("Message").value}
        stompClient.send("/app/"+gamecode, {}, JSON.stringify(message));

    }
)
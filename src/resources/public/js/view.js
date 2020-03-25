'use strict';

const webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chatapp");

/**
 * Entry point into chat room
 */
window.onload = function() {

    webSocket.onclose =   ()    => alert("WebSocket connection closed");
    webSocket.onmessage = (msg) => updateChatRoom(msg);
    $("#btn-msg").click(()      => sendMessage($("#message").val()));

};

/**
 * Send a message to the server.
 * @param msg  The message to send to the server.
 */
function sendMessage(msg) {
    if (msg !== "") {
        webSocket.send(msg);
        $("#message").val("");
    }
}

/**
 * Update the chat room with a message.
 * @param message  The message to update the chat room with.
 */
function updateChatRoom(message) {
    // TODO convert the data to JSON and append the message to the chat area
    $('#chatArea').append(JSON.parse(message.data).userMessage);
}

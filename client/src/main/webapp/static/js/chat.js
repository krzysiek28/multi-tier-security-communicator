'use strict';

var conversationForm = document.querySelector('#conversationForm');


var stompClient = null;
var username = null;

function connect() {

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);

}


function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);

}


function onError(error) {
    alert('Could not connect to WebSocket server. Please refresh this page to try again!');
}


function sendMessage(user, conversation, event) {


    var messageInput = document.querySelector('#message')
    var content = messageInput.value.trim()

    var message = {user: user, content: content, conversation: conversation}

    console.log("message content to "+ JSON.stringify(message))
    console.log("message class to "+message.type)

     stompClient.send("/newMessage", {}, JSON.stringify(message));
     messageInput.value = '';

     event.preventDefault();

     return false;
}


function onMessageReceived(payload) {

    var message = JSON.parse(payload.body);

    var conversationName = document.getElementById("conversationName").textContent.trim();

    if(message.conversation != conversationName){
        return;
    }

    var conversation = document.getElementById("conversation");
    var row = conversation.insertRow(0);

    var userCell = row.insertCell(0);
    var messageCell = row.insertCell(1);

    userCell.innerHTML = message.user;
    messageCell.innerHTML = message.content;

    console.log("conversation to "+conversation)
    console.log("conversation to "+conversation.innerHTML)



}

connect()
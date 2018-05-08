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


function sendMessage(event) {

    var messageInput = document.querySelector('#message')
    var messageContent = messageInput.value.trim()

     stompClient.send("/newMessage", {}, messageContent);
     messageInput.value = '';

    event.preventDefault();
    return false;
}


function onMessageReceived(payload) {

    var message = JSON.parse(payload.body);



    document.querySelector('#conversation').value = document.querySelector('#conversation').value+message.user +":" + message.content +'\n'
}

connect()
let usernamePage = document.querySelector('#username-page');
let chatPage = document.querySelector('#chat-page');
let messageForm = document.querySelector('#messageForm');
let messageInput = document.querySelector('#message');
let messageArea = document.querySelector('#messageArea');
let connectingElement = document.querySelector('.connecting');
let chatRoomId =null;
let stompClient = null;
let username = null;

let colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function onConnected() {
    chatRoomId= document.getElementById('chat_room_Id').value;
    console.log(chatRoomId);
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/'+ chatRoomId, onMessageReceived);

    // Tell your username to the server
    stompClient.send("/pub/chat_addUser/" + chatRoomId,
        {},
        JSON.stringify(
		        		{	sender: username, 
		        			type: 'JOIN',
                            chatRoomId:chatRoomId
		        		}
		        	)
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    chatRoomId= document.getElementById('chat_room_Id').value;
    let messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        let chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/pub/chat_sendMessage/"+ chatRoomId, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);
    let messageElement = document.createElement('li');
    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' 님이 입장하셨습니다.';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' 님이 퇴장 하셨습니다.!';
    } else {
        messageElement.classList.add('chat-message');

        let avatarElement = document.createElement('i');
        let avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        let usernameElement = document.createElement('span');
        let usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    let textElement = document.createElement('p');
    let messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    let hash = 0;
    for (let i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    let index = Math.abs(hash % colors.length);
    return colors[index];
}

messageForm.addEventListener('submit', sendMessage, true)
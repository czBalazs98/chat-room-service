# Chat Room Service

Backend part of my Chat Room Application. 
You can check the frontend part here: https://github.com/czBalazs98/chat-room-app

## To Run the Application
1. Start the MySQL service from docker-compose.yml
2. Start the application

## Tables in Database
Tables are created based on the entity classes.

| Table name       | Description                                                             |
|------------------|-------------------------------------------------------------------------|
| chat_message     | Stored chat messages, joined to chat_room by chat_room_id field         |
| chat_message_seq | ID sequence for chat_message table                                      |
| chat_room        | Stored chat rooms                                                       |
| chat_room_seq    | ID sequence for chat_room table                                         |
| chat_room_tags   | Stored traits of a chat_room, joined to chat_room by chat_room_id field |

## Endpoints
You can check and test the endpoints here: `http://{host-where-the-app-runs}/swagger-ui.html`

## Websocket
The application is using WebSocket for the real-time messaging. You can test the websocket using
Postman or other tools with the following URL: `ws://{host-where-the-app-runs}/chat/{chat-room-id}`, where
the {chat-room-id} parameter is a number that is the id of the chat room the messages are sent to.
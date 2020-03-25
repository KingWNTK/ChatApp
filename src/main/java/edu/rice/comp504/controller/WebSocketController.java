package edu.rice.comp504.controller;

import edu.rice.comp504.model.parse.*;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Create a web socket for the server.
 */
@WebSocket
public class WebSocketController {

    /**
     * Called when navigating to app.
     *
     * @param user user
     */
    @OnWebSocketConnect
    public void onConnect(Session user) {

    }

    /**
     * Called when closing the web socket.
     *
     * @param user user
     */
    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) throws IOException {
        ChatAppController.handleClose(user);
    }

    /**
     * Called when sent a message from a client instance.
     *
     * @param user    user
     * @param message the message received from the client, should be a Json file
     */
    @OnWebSocketMessage
    public void onMessage(Session user, String message) throws IOException {
        JSONObject msg = new JSONObject(message);
        String type = msg.getString("type");

        if (type == null) {
            return;
        }

        if (type.equals("CHECK_USER")) {
            CheckUserRequest cur = new CheckUserRequest(message);
            ChatAppController.handleCheckUser(user, cur.getUsername());
        } else if (type.equals("REGISTER_USER")) {
            RegisterUserRequest rur = new RegisterUserRequest(message);
            ChatAppController.handleRegisterUser(user, rur.getUserParse());
        } else if (type.equals("SEND_MSG")) {
            SendMsgRequest smr = new SendMsgRequest(message);
            ChatAppController.handleSendMsg(user, smr);
        } else if (type.equals("JOIN_ROOM")) {
            JoinRoomRequest cur = new JoinRoomRequest(message);
            ChatAppController.handleJoinRoomRequest(user, cur.getRoomname());
        } else if (type.equals("EXIT_ROOM")) {
            ExitRoomRequest cur = new ExitRoomRequest(message);
            ChatAppController.handleExitRoomRequest(user, cur.getRoomname(), "voluntarily left room");
        } else if (type.equals("CREATE_ROOM")) {
            CreateRoomRequest cur = new CreateRoomRequest(message);
            ChatAppController.handleCreateRoomRequest(user, cur.getRoom());
        } else if (type.equals("UPDATE_ROOM")) {
            UpdateRoomRequest cur = new UpdateRoomRequest(message);
            ChatAppController.handleEditRoomRequest(user, cur);
        } else if (type.equals("HEARTBEAT")) {
            HeartbeatRequest cur = new HeartbeatRequest(message);
            ChatAppController.handleHeartbeatRequest(user, cur.getTimestamp());
        }
    }
}

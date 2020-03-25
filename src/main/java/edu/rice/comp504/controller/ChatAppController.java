package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.AppContext;
import edu.rice.comp504.model.Room;
import edu.rice.comp504.model.User;
import edu.rice.comp504.model.cmd.*;
import edu.rice.comp504.model.parse.RoomParse;
import edu.rice.comp504.model.parse.SendMsgRequest;
import edu.rice.comp504.model.parse.UpdateRoomRequest;
import edu.rice.comp504.model.parse.UserParse;
import edu.rice.comp504.model.response.*;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static spark.Spark.*;

/**
 * The chat app controller communicates with all the clients on the web socket.
 */
public class ChatAppController {
    private static AppContext app = AppContext.getOnly();

    /**
     * Chat App entry point.
     *
     * @param args The command line arguments
     */

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFiles.location("/public");
        Gson gson = new Gson();
        webSocket("/chatapp", WebSocketController.class);
        init();
    }

    /**
     * Handler for the client request to create a user.
     *
     * @param session the session
     */
    public static void handleRegisterUser(Session session, UserParse pUserParse) throws IOException {
        Gson gson = new Gson();
        for (User u : app.getUsers()) {
            if (u.getId().compareTo(pUserParse.getUsername()) == 0) {
                UserExistResponse uer = new UserExistResponse(pUserParse.getUsername());
                session.getRemote().sendString(uer.getJsonRepresentation(gson));
                return;
            }
        }

        HashSet<RoomParse> rRoom = new HashSet<>();
        for (Room r : app.getRoomModels()) {
            if (r.checkAge(pUserParse.getAge()) && r.checkArea(pUserParse.getArea()) && r.checkSchool(pUserParse.getSchool())) {
                rRoom.add(new RoomParse(r.getOwner().getId(), r.getRoomId(), r.getAgeMin(), r.getAgeMax(), r.getAreas(), r.getSchools(), r.getAllUserIds(), r.getMessageForUser(pUserParse.getUsername())));
            }
        }
        User u = new User(session, pUserParse.getUsername(), pUserParse.getAge(), pUserParse.getArea(), pUserParse.getSchool());
        app.addUser(u);
        app.addSessionUser(session, u);

        InitResponse iniR = new InitResponse(pUserParse, rRoom);
        session.getRemote().sendString(iniR.getJsonRepresentation(gson));
    }

    /**
     * check whether user is valid.
     *
     * @param session  session
     * @param username username
     * @throws IOException exception
     */
    public static void handleCheckUser(Session session, String username) throws IOException {
        Gson gson = new Gson();
        ArrayList<User> users = app.getUsers();
        int status = 0;
        User tempU = null;
        for (User u : users) {
            if (username.compareTo(u.getId()) == 0 && u.getSession().isOpen()) {
                status = 2;
                break;
            }
            if (username.compareTo(u.getId()) == 0 && !u.getSession().equals(session)) {
                status = 1;
                tempU = u;
                break;
            }
        }

        if (status == 0) {
            NoUserResponse nur = new NoUserResponse(username);
            session.getRemote().sendString(nur.getJsonRepresentation(gson));
        } else if (status == 2) {
            UserInUseResponse uiu = new UserInUseResponse(username);
            session.getRemote().sendString(uiu.getJsonRepresentation(gson));
        } else if (status == 1) {
            HashSet<RoomParse> rRoom = new HashSet<>();
            for (Room r : app.getRoomModels()) {
                if (r.check(tempU)) {
                    rRoom.add(new RoomParse(r.getOwner().getId(), r.getRoomId(), r.getAgeMin(), r.getAgeMax(), r.getAreas(), r.getSchools(), r.getAllUserIds(), r.getMessageForUser(tempU.getId())));
                }
            }

            tempU.setSession(session);
            app.addSessionUser(session, tempU);
            InitResponse ir = new InitResponse(new UserParse(tempU.getId(), tempU.getAge(), tempU.getSchool(), tempU.getArea()), rRoom);
            session.getRemote().sendString(ir.getJsonRepresentation(gson));
        }
    }

    /**
     * close the handle.
     *
     * @param session session
     */
    public static void handleClose(Session session) {
        User curUser = app.findUser(session);
        AbstractCmd cmd = new CloseCmd(app);
        cmd.execute(curUser);
    }

    /**
     * send the message.
     *
     * @param session session
     * @param smr     message request
     */
    public static void handleSendMsg(Session session, SendMsgRequest smr) {
        User curUser = app.findUser(session);
        AbstractCmd cmd = new SendMsgCmd(app, smr);
        cmd.execute(curUser);
    }

    /**
     * create room request.
     *
     * @param session session
     * @param newRoom new room
     */
    public static void handleCreateRoomRequest(Session session, RoomParse newRoom) {
        User curUser = app.findUser(session);
        AbstractCmd cmd = new CreateRoomCmd(app, newRoom);
        cmd.execute(curUser);
    }

    /**
     * join room request.
     *
     * @param session  session
     * @param roomname room name
     */
    public static void handleJoinRoomRequest(Session session, String roomname) {
        User curUser = app.findUser(session);
        AbstractCmd cmd = new JoinRoomCmd(app, roomname);
        cmd.execute(curUser);
    }

    /**
     * exit room request.
     *
     * @param session  session
     * @param roomname room name
     * @param reason   reason
     */
    public static void handleExitRoomRequest(Session session, String roomname, String reason) {
        User curUser = app.findUser(session);
        AbstractCmd cmd = new ExitRoomCmd(app, roomname, reason);
        cmd.execute(curUser);
    }

    /**
     * exit room request.
     *
     * @param session session
     * @param request update room request
     */
    public static void handleEditRoomRequest(Session session, UpdateRoomRequest request) {
        User curUser = app.findUser(session);
        AbstractCmd cmd = new EditRoomCmd(app, request);
        cmd.execute(curUser);
    }

    /**
     * heart beat request.
     *
     * @param session     session
     * @param requestTime request time
     * @throws IOException exception
     */
    public static void handleHeartbeatRequest(Session session, long requestTime) throws IOException {
        HeartbeatResponse res = new HeartbeatResponse();
        session.getRemote().sendString(res.getJsonRepresentation(new Gson()));
    }

    /**
     * Get the heroku assigned port number.
     *
     * @return The heroku assigned port number
     */
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set.
    }
}

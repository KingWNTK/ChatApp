package edu.rice.comp504.model.cmd;

import com.google.gson.Gson;
import edu.rice.comp504.model.AppContext;
import edu.rice.comp504.model.Message;
import edu.rice.comp504.model.Room;
import edu.rice.comp504.model.User;
import edu.rice.comp504.model.response.RemoveRoomResponse;
import edu.rice.comp504.model.response.ResponseAdapter;
import edu.rice.comp504.model.response.SendMsgResponse;
import edu.rice.comp504.model.response.UpdateRoomUsersResponse;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

/**
 * The concrete command class for exiting a chatroom.
 */
public class ExitRoomCmd extends AbstractCmd {
    private AppContext app;
    private String roomname;
    private String reason;

    /**
     * constructor.
     *
     * @param app the app context
     * @param roomname the room to exit
     * @param reason reason for leaving
     */
    public ExitRoomCmd(AppContext app, String roomname, String reason) {
        this.app = app;
        this.roomname = roomname;
        this.reason = reason;
    }

    /**
     * The userModel to be executed on.
     *
     * @param curUser execute this cmd on this userModel.
     */
    @Override
    public void execute(User curUser) {
        try {
            Session session = curUser.getSession();
            Gson gson = new Gson();
            // add the room to the user list
            Room exitRoom = app.getRoomModel(roomname);

            if (curUser == null || exitRoom == null || !exitRoom.getUsers().contains(curUser)) {
                return;
            }

            // delete this user from the users list
            exitRoom.removeUser(curUser);

            //Check if the user is owner, if he is and the room is not empty,
            //assign the room to the first user in the user list

            if (exitRoom.getOwner().getId().equals(curUser.getId())) {
                if (exitRoom.isEmpty()) {
                    app.removeRoom(exitRoom);
                    ResponseAdapter res = new RemoveRoomResponse(exitRoom.getRoomId());
                    for (User user : app.getUsers()) {
                        if (exitRoom.check(user)) {
                            user.sendToSession(res.getJsonRepresentation(gson));

                        }
                    }
                    return;
                } else {
                    exitRoom.setOwner(exitRoom.getFirstUser());
                }
            }
            Message newSysMsg = new Message(curUser.getId(), null, true, reason, true, 0);
            exitRoom.addMessage(newSysMsg);

            ResponseAdapter updateRes = new UpdateRoomUsersResponse(exitRoom.getOwner().getId(), exitRoom.getAllUserIdList(), exitRoom.getRoomId());
            ResponseAdapter sendRes = new SendMsgResponse(exitRoom.getRoomId(), newSysMsg);

            curUser.sendToSession(updateRes.getJsonRepresentation(gson));
            for (User user : exitRoom.getUsers()) {
                user.sendToSession(updateRes.getJsonRepresentation(gson));
                user.sendToSession(sendRes.getJsonRepresentation(gson));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

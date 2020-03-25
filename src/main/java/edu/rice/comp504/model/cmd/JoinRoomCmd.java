package edu.rice.comp504.model.cmd;

import com.google.gson.Gson;
import edu.rice.comp504.model.AppContext;
import edu.rice.comp504.model.Message;
import edu.rice.comp504.model.Room;
import edu.rice.comp504.model.User;
import edu.rice.comp504.model.parse.RoomParse;
import edu.rice.comp504.model.response.JoinRoomResponse;
import edu.rice.comp504.model.response.ResponseAdapter;
import edu.rice.comp504.model.response.SendMsgResponse;
import edu.rice.comp504.model.response.UpdateRoomUsersResponse;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.HashSet;

/**
 * The concrete command class for joining a chatroom.
 */
public class JoinRoomCmd extends AbstractCmd {
    private AppContext app;
    private String roomname;

    /**
     * constructor.
     *
     * @param app the app context
     * @param roomname the room to join
     */
    public JoinRoomCmd(AppContext app, String roomname) {
        this.app = app;
        this.roomname = roomname;
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
            Room joinRoom = app.getRoomModel(roomname);

            if (curUser == null || joinRoom == null || !joinRoom.check(curUser)) {
                return;
            }

            joinRoom.addUser(curUser);

            //send a sys msg to inform users that a new user has joined the room.
            Message newSysMsg = new Message(curUser.getId(), null, true, "joined room", true, 0);
            joinRoom.addMessage(newSysMsg);

            HashSet<String> allUserIds = joinRoom.getAllUserIds();

            RoomParse responseRoom = new RoomParse(joinRoom.getOwner().getId(), joinRoom.getRoomId(), joinRoom.getAgeMin(), joinRoom.getAgeMax(),
                    joinRoom.getAreas(), joinRoom.getSchools(), allUserIds, joinRoom.getMessageForUser(curUser.getId()));
            JoinRoomResponse res = new JoinRoomResponse(responseRoom);
            session.getRemote().sendString(res.getJsonRepresentation(gson));

            //Tell everyone in the room that a new user has entered.
            ResponseAdapter sendRes = new SendMsgResponse(joinRoom.getRoomId(), newSysMsg);
            ResponseAdapter updateRes = new UpdateRoomUsersResponse(joinRoom.getOwner().getId(), joinRoom.getAllUserIdList(), joinRoom.getRoomId());
            for (User user : joinRoom.getUsers()) {
                if (!user.getId().equals(curUser.getId())) {
                    user.sendToSession(updateRes.getJsonRepresentation(gson));
                    user.sendToSession(sendRes.getJsonRepresentation(gson));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

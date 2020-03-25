package edu.rice.comp504.model.cmd;

import com.google.gson.Gson;
import edu.rice.comp504.model.AppContext;
import edu.rice.comp504.model.Message;
import edu.rice.comp504.model.Room;
import edu.rice.comp504.model.User;
import edu.rice.comp504.model.parse.RoomParse;
import edu.rice.comp504.model.response.AddRoomResponse;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.HashSet;

/**
 * The concrete command class for creating a chatroom.
 */
public class CreateRoomCmd extends AbstractCmd {
    private AppContext app;
    private RoomParse newRoom;

    /**
     * constructor.
     *
     * @param app the app context
     * @param newRoom room to be created
     */
    public CreateRoomCmd(AppContext app, RoomParse newRoom) {
        this.app = app;
        this.newRoom = newRoom;
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
            User owner = curUser;
            HashSet<User> users = new HashSet<>();

            // check does the room has been created or not
            if (owner == null || app.isExistedRoom(newRoom.getRoomname())) {
                return;
            }

            // user models
            users.add(owner);
            Room newRoomModel = new Room(newRoom.getAgeMin(), newRoom.getAgeMax(), newRoom.getAreas(), newRoom.getSchools(), users, newRoom.getMsgsHash(), owner, newRoom.getRoomname());

            newRoomModel.addMessage(new Message(owner.getId(), null, true, "created room", true, 0));

            // check whether the room is available to this user
            newRoom.setMsgs(newRoomModel.getMessages());
            if (newRoomModel.check(owner)) {
                app.addRoom(newRoomModel);
                for (User user : app.getUsers()) {
                    if (newRoomModel.check(user)) {
                        AddRoomResponse newRes = new AddRoomResponse(newRoom);
                        user.sendToSession(newRes.getJsonRepresentation(gson));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

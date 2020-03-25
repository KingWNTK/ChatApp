package edu.rice.comp504.model.cmd;

import com.google.gson.Gson;
import edu.rice.comp504.model.AppContext;
import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.Message;
import edu.rice.comp504.model.Room;
import edu.rice.comp504.model.User;
import edu.rice.comp504.model.parse.RoomParse;
import edu.rice.comp504.model.parse.UpdateRoomRequest;
import edu.rice.comp504.model.response.*;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The concrete command class for editing a chatroom.
 */
public class EditRoomCmd extends AbstractCmd {
    private AppContext app;
    private UpdateRoomRequest request;

    /**
     * constructor.
     *
     * @param app the app context
     * @param request the update room request
     */
    public EditRoomCmd(AppContext app, UpdateRoomRequest request) {
        this.app = app;
        this.request = request;
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
            Room previousRoom = app.getRoomModel(request.getRoomname());

            if (curUser == null || previousRoom == null || !curUser.getId().equals(previousRoom.getOwner().getId())) {
                return;
            }

            HashSet<User> preUsers = new HashSet<>();
            for (User user : app.getUsers()) {
                if (previousRoom.check(user)) {
                    preUsers.add(user);
                }
            }

            HashSet<String> preAreas = (HashSet<String>) previousRoom.getAreas().clone();
            HashSet<String> preSchools = (HashSet<String>) previousRoom.getSchools().clone();
            int preAgeMin = previousRoom.getAgeMin();
            int preAgeMax = previousRoom.getAgeMax();

            // the first thing to do is to kick all the users that does not meet requirements
            previousRoom.setAreas(request.getAreas());
            previousRoom.setSchools(request.getSchools());
            previousRoom.setAgeMax(request.getAgeMax());
            previousRoom.setAgeMin(request.getAgeMin());

            ArrayList<User> rmList = new ArrayList<>();
            for (User each : previousRoom.getUsers()) {
                if (previousRoom.check(each)) {
                    // the user should not be kicked out
                    // then do nothing
                    continue;
                } else {
                    // the owner was kicked out, we do not allow such situation occurs
                    if (previousRoom.getOwner().getId().equals(each.getId())) {
                        //rollback changes
                        previousRoom.setAgeMax(preAgeMax);
                        previousRoom.setAgeMin(preAgeMin);
                        previousRoom.setAreas(preAreas);
                        previousRoom.setSchools(preSchools);
                        return;
                    } else {
                        rmList.add(each);
                    }
                }
            }
            // get the list of the all users in the room
            // send the update room msg to all the users in the list
            EditRoomResponse editRoomRes = new EditRoomResponse(previousRoom.getRoomId(), previousRoom.getAgeMin(), previousRoom.getAgeMax(), previousRoom.getAreaName(), previousRoom.getSchoolName());
            Message newSysMsg = new Message(curUser.getId(), null, true, "updated room constraint", true, 0);
            previousRoom.addMessage(newSysMsg);

            for (User each : previousRoom.getUsers()) {
                each.sendToSession(editRoomRes.getJsonRepresentation(gson));
                ResponseAdapter sendRes = new SendMsgResponse(previousRoom.getRoomId(), newSysMsg);
                each.sendToSession(sendRes.getJsonRepresentation(gson));
            }

            //kick unqualified users from room
            ResponseAdapter removeRes = new RemoveRoomResponse(previousRoom.getRoomId());
            for (User each : rmList) {
                String reason = "is kicked out due to constraint change";
                ChatAppController.handleExitRoomRequest(each.getSession(), previousRoom.getRoomId(), reason);
                each.sendToSession(removeRes.getJsonRepresentation(gson));
            }

            //remove the room from the available room list of users
            for (User user : preUsers) {
                if (!previousRoom.check(user)) {
                    user.sendToSession(removeRes.getJsonRepresentation(gson));
                }
            }

            //add the room to available room list of user.
            for (User user : app.getUsers()) {
                if (!preUsers.contains(user) && previousRoom.check(user)) {
                    RoomParse resRoom = new RoomParse(previousRoom.getOwner().getId(), previousRoom.getRoomId(), previousRoom.getAgeMin(), previousRoom.getAgeMax(),
                            previousRoom.getAreas(), previousRoom.getSchools(), previousRoom.getAllUserIds(), previousRoom.getMessageForUser(user.getId()));
                    AddRoomResponse newRes = new AddRoomResponse(resRoom);
                    user.sendToSession(newRes.getJsonRepresentation(gson));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

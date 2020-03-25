package edu.rice.comp504.model.cmd;

import com.google.gson.Gson;
import edu.rice.comp504.model.AppContext;
import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.Message;
import edu.rice.comp504.model.Room;
import edu.rice.comp504.model.User;
import edu.rice.comp504.model.parse.SendMsgRequest;
import edu.rice.comp504.model.response.SendMsgResponse;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The concrete command class for sending messages.
 */
public class SendMsgCmd extends AbstractCmd {
    private AppContext app;
    private SendMsgRequest smr;

    /**
     * constructor.
     *
     * @param app the app context
     * @param smr the Send Message Request
     */
    public SendMsgCmd(AppContext app, SendMsgRequest smr) {
        this.app = app;
        this.smr = smr;
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
            Message msg = smr.getMsg();
            Room room = app.getRoomModel(smr.getRoomname());
            String pattern = "(.*)[Hh][Aa][Tt][Ee](.*)";
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(msg.getText());

            if (msg.getToAll() && !room.getOwner().getId().equals(msg.getFrom())) {
                return;
            }

            if (msg.getFrom() != null && msg.getTo() != null && msg.getFrom().equals(msg.getTo())) {
                return;
            }

            if (matcher.find()) {
                String reason = "is kicked out due to sensitive message";
                ArrayList<Room> rmList = new ArrayList<>();
                for (Room rm : app.getRoomModels()) {
                    if (rm.getUsers().contains(curUser)) {
                        rmList.add(rm);
                    }
                }
                for (Room rm : rmList) {
                    ChatAppController.handleExitRoomRequest(session, rm.getRoomId(), reason);
                }

                return;
            }
            room.addMessage(msg);
            if (msg.getToAll() || msg.getIsSysMsg()) {
                for (User u : room.getUsers()) {
                    SendMsgResponse smres = new SendMsgResponse(smr.getRoomname(), msg);
                    u.sendToSession(smres.getJsonRepresentation(gson));

                }
            } else {
                for (User u : room.getUsers()) {
                    if (u.getId().compareTo(msg.getTo()) == 0 || u.getId().compareTo(msg.getFrom()) == 0) {
                        SendMsgResponse smres = new SendMsgResponse(smr.getRoomname(), msg);
                        u.sendToSession(smres.getJsonRepresentation(gson));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

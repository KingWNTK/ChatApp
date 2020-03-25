package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.AppContext;
import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.Room;
import edu.rice.comp504.model.User;
import org.eclipse.jetty.websocket.api.Session;

/**
 * The concrete command class for closing a session.
 */
public class CloseCmd extends AbstractCmd {
    private AppContext app;

    /**
     * constructor.
     *
     * @param app the app context
     */
    public CloseCmd(AppContext app) {
        this.app = app;
    }

    /**
     * The userModel to be executed on.
     *
     * @param curUser execute this cmd on this userModel.
     */
    @Override
    public void execute(User curUser) {
        Session session = curUser.getSession();
        for (Room room : app.getRoomModels()) {
            if (room.getAllUserIds().contains(curUser.getId())) {
                ChatAppController.handleExitRoomRequest(session, room.getRoomId(), "left room due to connection lost");
            }
        }
        app.closeSession(session);
    }
}

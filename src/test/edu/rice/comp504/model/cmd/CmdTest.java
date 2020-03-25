package edu.rice.comp504.model.cmd;

import edu.rice.comp504.controller.ChatAppController;
import edu.rice.comp504.model.AppContext;
import edu.rice.comp504.model.Message;
import edu.rice.comp504.model.Room;
import edu.rice.comp504.model.User;
import edu.rice.comp504.model.parse.SendMsgRequest;
import edu.rice.comp504.model.parse.UpdateRoomRequest;
import junit.framework.TestCase;
import org.eclipse.jetty.websocket.api.Session;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.HashSet;

@PrepareForTest(ChatAppController.class)
public class CmdTest extends TestCase {

    public void testCmd() {
        Session session = Mockito.mock(Session.class);
        AppContext app = new AppContext();
        HashSet<String> areas = new HashSet<>();
        HashSet<String> schools = new HashSet<>();
        HashSet<User> users = new HashSet<>();
        HashSet<String> userIds = new HashSet<>();
        HashSet<Message> msg = new HashSet<>();
        areas.add("USA");
        schools.add("Rice");
        userIds.add("123");
        User user = new User(session, "123", 1, "USA", "Rice");
        msg.add(new Message("aaa", "bbb", true, "text", true, 1));
        users.add(user);
        Room room = new Room(1, 18, areas, schools, users, msg, user, "0");
        app.addRoom(room);
        app.addUser(user);
        app.addSessionUser(session, user);
        PowerMockito.mockStatic(ChatAppController.class);
//        Mockito.doNothing().when(any(ChatAppController.class)).handleExitRoomRequest(session, anyString(), anyString());

//        CloseCmd closeCmd = new CloseCmd(app);
//        closeCmd.execute(user);
//
//        RoomParse roomParse = Mockito.mock(RoomParse.class);
//        CreateRoomCmd createRoomCmd = new CreateRoomCmd(app, roomParse);
//        createRoomCmd.execute(user);

//        UpdateRoomRequest updateRoomRequest = Mockito.mock(UpdateRoomRequest.class);
//        EditRoomCmd editRoomCmd = new EditRoomCmd(app, updateRoomRequest);
//        editRoomCmd.execute(user);

        ExitRoomCmd exitRoomCmd = new ExitRoomCmd(app, "aaa", "bbb");
        exitRoomCmd.execute(user);

        JoinRoomCmd joinRoomCmd = new JoinRoomCmd(app, "aaa");
        joinRoomCmd.execute(user);

        String data = "{\"type\":\"SEND_MSG\",\"roomname\":\"0\",\"msg\":{\"from\":\"tz\",\"to\":null,\"toAll\":true,\"isSysMsg\":false,\"timestamp\":157,\"text\":\"ww\"}}";
        SendMsgRequest sendMsgRequest = new SendMsgRequest(data);
        SendMsgCmd sendMsgCmd = new SendMsgCmd(app, sendMsgRequest);
        sendMsgCmd.execute(user);

        String data1 = "{\"type\":\"SEND_MSG\",\"roomname\":\"0\",\"msg\":{\"from\":\"123\",\"to\":null,\"toAll\":true,\"isSysMsg\":false,\"timestamp\":157,\"text\":\"ww\"}}";
        sendMsgRequest = new SendMsgRequest(data1);
        sendMsgCmd = new SendMsgCmd(app, sendMsgRequest);
        sendMsgCmd.execute(user);

        String data2 = "{\"type\":\"SEND_MSG\",\"roomname\":\"0\",\"msg\":{\"from\":\"123\",\"to\":\"123\",\"toAll\":true,\"isSysMsg\":false,\"timestamp\":157,\"text\":\"ww\"}}";
        sendMsgRequest = new SendMsgRequest(data2);
        sendMsgCmd = new SendMsgCmd(app, sendMsgRequest);
        sendMsgCmd.execute(user);

        String data3 = "{\"type\":\"SEND_MSG\",\"roomname\":\"0\",\"msg\":{\"from\":\"123\",\"to\":\"1\",\"toAll\":true,\"isSysMsg\":false,\"timestamp\":157,\"text\":\"123\"}}";
        sendMsgRequest = new SendMsgRequest(data3);
        sendMsgCmd = new SendMsgCmd(app, sendMsgRequest);
        sendMsgCmd.execute(user);
    }

}

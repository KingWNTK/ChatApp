package edu.rice.comp504.model;
import edu.rice.comp504.model.parse.*;
import junit.framework.TestCase;
import org.mockito.Mockito;
import org.eclipse.jetty.websocket.api.Session;

import java.util.HashSet;

public class ParseTest extends TestCase {

    public void testCheckUserRequest() {
        CheckUserRequest cur = new CheckUserRequest("{\"type\" : \"Test\", \"username\" : \"Test\"}");
        assertEquals("Test", cur.getType());
        assertEquals("Test", cur.getUsername());
    }

    public void testCreateRoomRequest() {
        String data = "{\"type\" : \"test\", \"room\" : {\"owner\" : \"test\", \"roomname\" : \"test\", \"ageMin\":1, \"ageMax\":99, \"areas\" : [\"test\"], \"schools\" : [\"test\"], \"users\" : [\"test\"], \"msgs\" : [{\"from\":\"aaa\"}]}}";
        CreateRoomRequest crr = new CreateRoomRequest(data);
        crr.getType();
        crr.getRoom();
    }

    public void testExitRoom() {
        String data = "{\"type\" : \"Test\", \"roomname\" : \"Test\"}";
        ExitRoomRequest err = new ExitRoomRequest(data);
        err.getRoomname();
        err.getType();
    }

    public void testHeartbeat() {
        String data = "{\"type\" : \"Test\", \"timestamp\" : 1}";
        HeartbeatRequest h = new HeartbeatRequest(data);
        h.getTimestamp();
        h.getType();
    }

    public void testJoinRoom() {
        String data = "{\"type\" : \"Test\", \"roomname\" : \"test\"}";
        JoinRoomRequest jrr = new JoinRoomRequest(data);
        jrr.getRoomname();
        jrr.getType();
    }

    public void testRoomParse() {
        HashSet<String> hs = new HashSet<>();
        hs.add("test");
        Message message = new Message("aaa", "bbb", true, "text", true, 1);
        HashSet<Message> hsm = new HashSet<>();
        hsm.add(message);
        RoomParse rp = new RoomParse("test", "test", 1, 99, hs, hs, hs, hsm);
        rp.setMsgs(hsm);
        rp.getMsgs();
        rp.getMsgsHash();
        assertEquals(1, rp.getAreas().size());
        assertEquals(1, rp.getSchools().size());
        assertEquals(1, rp.getUsers().size());
        assertEquals(99, rp.getAgeMax());
        assertEquals(1, rp.getAgeMin());
        assertEquals("test", rp.getOwner());
        assertEquals("test", rp.getRoomname());
        String data = "{\"owner\" : \"test\", \"roomname\" : \"test\", \"ageMin\":1, \"ageMax\":99, \"areas\" : [\"test\"], \"schools\" : [\"test\"], \"users\" : [\"test\"], \"msgs\" : [{\"from\":\"aaa\"}]}";

        rp.parse(data);

    }

    public void testUserParseRequest(){
        UserParse cur = new UserParse("TEST", 18, "Rice", "TEST");
        assertEquals("TEST", cur.getUsername());
        assertEquals("TEST", cur.getArea());
        assertEquals(18, cur.getAge());
        assertEquals("Rice", cur.getSchool());
        cur.parse("{\"username\" : \"Test\", \"school\" : \"Test\",\"area\" : \"Test\", \"age\" :18}");
    }

    public void testUpdateRoomRequest(){
        String data = "{\"type\":\"UPDATE_ROOM\",\"roomname\":\"room1\",\"ageMin\":15,\"ageMax\":80,\"areas\":[\"Asia\"],\"schools\":[\"Rice University\",\"Harvard University\"]}";
        UpdateRoomRequest cur = new UpdateRoomRequest(data);
        assertEquals("room1", cur.getRoomname());
        assertEquals("UPDATE_ROOM", cur.getType());
        assertEquals(80, cur.getAgeMax());
        assertEquals(15, cur.getAgeMin());
        assertEquals("Asia", cur.getAreas()[0]);
        assertEquals("Rice University", cur.getSchools()[0]);
    }

    public void testSendMsgRequest(){
        String data = "{\"type\":\"SEND_MSG\",\"roomname\":\"19\",\"msg\":{\"from\":\"tz\",\"to\":null,\"toAll\":true,\"isSysMsg\":false,\"timestamp\":157,\"text\":\"ww\"}}";
        SendMsgRequest cur = new SendMsgRequest(data);
        assertEquals("SEND_MSG", cur.getType());
        assertEquals("19", cur.getRoomname());

        cur.getMsg();
        assertEquals("tz", cur.getMsg().getFrom());
        assertEquals(null, cur.getMsg().getTo());
        assertEquals("ww", cur.getMsg().getText());
        assertEquals(true, cur.getMsg().getToAll());
        assertEquals(false, cur.getMsg().getIsSysMsg());
        assertEquals((long)157, cur.getMsg().getTimestamp());

    }

    public void testRegisterUserRequest(){
        String data = "{\"type\":\"REGISTER_USER\",\"user\":{\"username\":\"tz\",\"age\":18,\"area\":\"North America\",\"school\":\"Rice University\"}}";
        RegisterUserRequest cur = new RegisterUserRequest(data);
        assertEquals("REGISTER_USER", cur.getType());
        assertEquals(18, cur.getUserParse().getAge());
        assertEquals("tz", cur.getUserParse().getUsername());
        assertEquals("North America", cur.getUserParse().getArea());
        assertEquals("Rice University", cur.getUserParse().getSchool());
    }

}



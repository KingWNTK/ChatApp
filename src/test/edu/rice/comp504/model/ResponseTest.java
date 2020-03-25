package edu.rice.comp504.model;
import edu.rice.comp504.model.response.*;
import junit.framework.TestCase;

public class ResponseTest extends TestCase {

    public void testUserInUseResponse() {
        UserInUseResponse user = new UserInUseResponse("aaa");
        assertEquals("USER_IN_USE", user.getType());
        assertEquals("aaa", user.getUsername());
    }

    public void testUpdateRoomUsersResponse() {
        String[] users = {"a","b","c"};
        UpdateRoomUsersResponse user = new UpdateRoomUsersResponse("owner", users, "room");
        assertEquals("UPDATE_ROOM_USERS", user.getType());
        assertEquals("owner", user.getOwner());
        assertEquals("owner", user.getOwner());
        assertEquals("a", user.getUsers()[0]);

    }
    //add more testcases
}
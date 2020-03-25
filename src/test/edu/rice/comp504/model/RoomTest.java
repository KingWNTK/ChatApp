package edu.rice.comp504.model;
import junit.framework.TestCase;
import org.mockito.Mockito;
import org.eclipse.jetty.websocket.api.Session;

import java.util.HashSet;

public class RoomTest extends TestCase {

    public void testRoom() {
        HashSet<String> areas = new HashSet<>();
        HashSet<String> schools = new HashSet<>();
        HashSet<User> users = new HashSet<>();
        HashSet<String> userIds = new HashSet<>();
        HashSet<Message> msg = new HashSet<>();
        areas.add("USA");
        schools.add("Rice");
        userIds.add("123");
        Session session = Mockito.mock(Session.class);
        User user = new User(session, "123", 1, "USA", "Rice");
        msg.add(new Message("aaa", "bbb", true, "text", true, 1));
        users.add(user);
        String[] userId = new String[]{"123"};
        String[] areaArray = new String[]{"USA"};
        String[] schoolArray = new String[]{"Rice"};
        Room room = new Room(1, 18, areas, schools, users, msg, user, "0");
        room.addMessage(new Message("aaa", "bbb", true, "text", true, 1));
        room.addUser(user);
        room.setAreas(areas);
        room.setSchools(schools);
        room.setOwner(user);
        room.setAgeMax(18);
        room.setAgeMin(1);
        room.setSchools(schoolArray);
        room.setAreas(areaArray);
        assertEquals(room.checkAge(16), true);
        assertEquals(room.check(user), true);
        assertEquals(room.getRoomId(), "0");
        assertEquals(room.getAgeMax(), 18);
        assertEquals(room.getOwner(), user);
        assertEquals(room.getAgeMin(), 1);
        assertEquals(room.getUsers(), users);
        assertEquals(room.getAllUserIds(), userIds);
        assertEquals(room.getAreas(), areas);
        assertEquals(room.getAllUserIdList()[0], userId[0]);
        assertEquals(room.getMessageForUser("123"), msg);
        assertEquals(room.getFirstUser(), user);
        assertEquals(room.isEmpty(), false);
        assertEquals(room.getAreaName()[0], areaArray[0]);
        assertEquals(room.getSchoolName()[0], schoolArray[0]);
    }
}



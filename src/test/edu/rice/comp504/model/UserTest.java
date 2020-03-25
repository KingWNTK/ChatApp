package edu.rice.comp504.model;

import junit.framework.TestCase;
import org.eclipse.jetty.websocket.api.Session;
import org.mockito.Mockito;

public class UserTest extends TestCase {
    public void testUser() {
        Session session = Mockito.mock(Session.class);
        User user = new User(session, "123", 1, "USA", "Rice");
        user.setSession(session);
        assertEquals(user.getSession(), session);
        assertEquals(user.getAge(), 1);
        assertEquals(user.getId(), "123");
        assertEquals(user.getArea(), "USA");
        assertEquals(user.getSchool(), "Rice");
    }
}

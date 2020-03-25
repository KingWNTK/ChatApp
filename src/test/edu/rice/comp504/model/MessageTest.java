package edu.rice.comp504.model;
import junit.framework.TestCase;


public class MessageTest extends TestCase {

    public void testMessage() {
        Message message = new Message("aaa", "bbb", true, "text", true, 1);
        String testString = "{\"from\":\"aaa\"}";
        Message message1 = new Message(testString);
        assertEquals(message.getFrom(), "aaa");
        assertEquals(message.getIsSysMsg(), true);
        assertEquals(message.getText(), "text");
        assertEquals(message.getTimestamp(), 1);
        assertEquals(message.getTo(), "bbb");
        assertEquals(message.getToAll(), true);
    }

}
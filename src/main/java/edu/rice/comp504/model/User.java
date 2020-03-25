package edu.rice.comp504.model;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

/**
 * The user class, representing the user.
 */
public class User {
    /**
     * UserModel ID.
     */
    private String id;
    /**
     * Websocket session.
     */
    private Session session;
    /**
     * UserModel's age.
     */
    private int age;
    /**
     * UserModel's area.
     */
    private String area;
    /**
     * UserModel's school.
     */
    private String school;

    /**
     * Constructor.
     *
     * @param id            UserModel ID
     * @param age           UserModel's age
     * @param area          UserModel's area
     * @param school        UserModel's school
     */
    public User(Session session, String id, int age, String area, String school) {
        this.id = id;
        this.age = age;
        this.area = area;
        this.school = school;
        this.session = session;
    }

    /**
     * Get UserModel Id.
     *
     * @return UserModel Id
     */
    public String getId() {
        return id;
    }

    /**
     * Get age.
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Get area.
     *
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * Get school.
     *
     * @return school
     */
    public String getSchool() {
        return school;
    }

    /**
     * Get session.
     *
     * @return session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Set session.
     *
     * @param s session
     */
    public void setSession(Session s) {
        this.session = s;
    }

    /**
     * Send the string message to the session.
     *
     * @param s string
     * @throws IOException exception
     */
    public void sendToSession(String s) throws IOException {
        if (session.isOpen()) {
            session.getRemote().sendString(s);
        }
    }
}
package edu.rice.comp504.model;

import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Manage the context of the chat app.
 */
public class AppContext {
    /**
     * singleton variable.
     */
    private static AppContext only = null;

    /**
     * The list of existing userModels, should remove the head when the list is too long.
     */
    private ArrayList<User> users = new ArrayList<>();
    /**
     * The list of existing roomModels, should remove the head when the list is too long.
     */
    private ArrayList<Room> roomModels = new ArrayList<>();

    /**
     * room map.
     */
    private HashMap<String, Room> roomMap = new HashMap<>();

    /**
     * user session map.
     */
    private Map<Session, User> sessionUserMap = new HashMap<>();

    /**
     * The singleton method.
     *
     * @return the only app context
     */
    public static AppContext getOnly() {
        if (only == null) {
            only = new AppContext();
        }
        return only;
    }

    /**
     * Get all the roomModels.
     *
     * @return the roomModels.
     */
    public ArrayList<Room> getRoomModels() {
        return roomModels;
    }

    /**
     * add session user.
     *
     * @param s    session
     * @param user user
     */
    public void addSessionUser(Session s, User user) {
        sessionUserMap.put(s, user);
    }

    /**
     * close session.
     *
     * @param s session
     */
    public void closeSession(Session s) {
        sessionUserMap.remove(s, findUser(s));
    }

    /**
     * find user.
     *
     * @param s session
     * @return user
     */
    public User findUser(Session s) {
        return sessionUserMap.get(s);
    }

    /**
     * Get all the userModels.
     *
     * @return the userModels.
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Add a user to the user list.
     *
     * @param u the user
     */
    public void addUser(User u) {
        users.add(u);
    }

    /**
     * Add a room to the room list.
     *
     * @param r the room
     */
    public void addRoom(Room r) {
        roomMap.put(r.getRoomId(), r);
        roomModels.add(r);
    }

    /**
     * Remove a room from the room list.
     *
     * @param r the room
     */
    public void removeRoom(Room r) {
        roomModels.remove(r);
        roomMap.remove(r.getRoomId());
    }

    /**
     * get room model.
     *
     * @param roomId room id
     * @return room model
     */
    public Room getRoomModel(String roomId) {
        return roomMap.get(roomId);
    }

    /**
     * return whether it is existed room.
     *
     * @param roomId room id
     * @return whether it is existed room
     */
    public boolean isExistedRoom(String roomId) {
        return roomMap.containsKey(roomId);
    }
}

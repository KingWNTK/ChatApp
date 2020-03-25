package edu.rice.comp504.model.response;

import com.google.gson.Gson;

/**
 * The UpdateRoomUsersResponse class.
 */
public class UpdateRoomUsersResponse implements ResponseAdapter {
    /**
     * Message type.
     */
    String type;
    /**
     * Owner.
     */
    String owner;
    /**
     * Room name.
     */
    String roomname;
    /**
     * users.
     */
    String[] users;

    /**
     * Constructor.
     *
     * @param owner    owner
     * @param users    user
     * @param roomname roomname
     */
    public UpdateRoomUsersResponse(String owner, String[] users, String roomname) {
        this.type = "UPDATE_ROOM_USERS";
        this.owner = owner;
        this.users = users;
        this.roomname = roomname;
    }

    /**
     * Get type.
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Get Owner.
     *
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Get users.
     *
     * @return users
     */
    public String[] getUsers() {
        return users;
    }

    /**
     * get a JSON string to respond.
     *
     * @param gson a Gson instance
     * @return a JSON representation
     */
    @Override
    public String getJsonRepresentation(Gson gson) {
        return gson.toJson(this);
    }
}

package edu.rice.comp504.model.response;

import com.google.gson.Gson;
import edu.rice.comp504.model.parse.RoomParse;
import edu.rice.comp504.model.parse.UserParse;

import java.util.HashSet;

/**
 * The InitResponse class.
 */
public class InitResponse implements ResponseAdapter {
    /**
     * Message type.
     */
    private String type;

    private UserParse user;
    /**
     * rooms.
     */
    private HashSet<RoomParse> rooms;

    /**
     * Constructor.
     *
     * @param user           user
     * @param availableRooms available rooms
     */
    public InitResponse(UserParse user, HashSet<RoomParse> availableRooms) {
        this.type = "INIT";
        this.user = user;
        this.rooms = availableRooms;
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
     * Get user.
     *
     * @return user
     */
    public UserParse getUser() {
        return user;
    }

    /**
     * Get rooms.
     *
     * @return rooms
     */
    public HashSet<RoomParse> getRooms() {
        return rooms;
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

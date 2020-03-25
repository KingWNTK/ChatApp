package edu.rice.comp504.model.response;

import com.google.gson.Gson;
import edu.rice.comp504.model.parse.RoomParse;

/**
 * The JoinRoomResponse class.
 */
public class JoinRoomResponse implements ResponseAdapter {
    /**
     * Message type.
     */
    private String type;
    /**
     * Room.
     */
    private RoomParse room;

    /**
     * Constructor.
     *
     * @param room room
     */
    public JoinRoomResponse(RoomParse room) {
        this.type = "JOIN_ROOM";
        this.room = room;
    }

    /**
     * Get type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Get room.
     *
     * @return the room
     */
    public RoomParse getRoom() {
        return room;
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

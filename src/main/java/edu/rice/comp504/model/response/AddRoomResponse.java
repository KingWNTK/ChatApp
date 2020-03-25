package edu.rice.comp504.model.response;

import com.google.gson.Gson;
import edu.rice.comp504.model.parse.RoomParse;

/**
 * The AddRoomResponse class.
 */
public class AddRoomResponse implements ResponseAdapter {
    /**
     * Message type.
     */
    String type;
    /**
     * Room.
     */
    RoomParse room;

    /**
     * Add Room Response.
     *
     * @param room room
     */
    public AddRoomResponse(RoomParse room) {
        this.type = "ADD_ROOM";
        this.room = room;
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
     * Get room.
     *
     * @return room
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

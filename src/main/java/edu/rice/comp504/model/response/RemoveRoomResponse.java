package edu.rice.comp504.model.response;

import com.google.gson.Gson;

/**
 * The RemoveRoomResponse class.
 */
public class RemoveRoomResponse implements ResponseAdapter {
    /**
     * Message type.
     */
    String type;
    /**
     * Room name.
     */
    String roomname;

    /**
     * Constructor.
     *
     * @param roomname room name
     */
    public RemoveRoomResponse(String roomname) {
        this.type = "REMOVE_ROOM";
        this.roomname = roomname;
    }


    /**
     * Get type.
     *
     * @return tyoe
     */
    public String getType() {
        return type;
    }

    /**
     * Get roomname.
     *
     * @return roomname
     */
    public String getRoomname() {
        return roomname;
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

package edu.rice.comp504.model.response;

import com.google.gson.Gson;

/**
 * The EditRoomResponse class.
 */
public class EditRoomResponse implements ResponseAdapter {
    /**
     * Message type.
     */
    String type;
    /**
     * Room name.
     */
    String roomname;
    /**
     * Minimum age.
     */
    int ageMin;
    /**
     * Maximum age.
     */
    int ageMax;
    /**
     * Areas.
     */
    String[] areas;
    /**
     * Schools.
     */
    String[] schools;

    /**
     * Constructor.
     *
     * @param roomname room name
     * @param ageMin   the minimum age
     * @param ageMax   the maximum age
     * @param areas    the location
     * @param schools  the school
     */
    public EditRoomResponse(String roomname, int ageMin, int ageMax, String[] areas, String[] schools) {
        this.type = "EDIT_ROOM";
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.roomname = roomname;
        this.areas = areas;
        this.schools = schools;
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

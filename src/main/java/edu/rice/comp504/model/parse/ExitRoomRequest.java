package edu.rice.comp504.model.parse;

import com.google.gson.Gson;

/**
 * The class that makes users exit room.
 */
public class ExitRoomRequest {
    /**
     * request type.
     */
    private String type;
    /**
     * room name.
     */
    private String roomname;

    /**
     * constructor.
     * @param data parse data
     */
    public ExitRoomRequest(String data) {
        parse(data);
    }

    /**
     * parse data.
     * @param data data
     */
    public void parse(String data) {
        Gson gson = new Gson();
        JoinRoomRequest temp = gson.fromJson(data, JoinRoomRequest.class);
        this.type = temp.getType();
        this.roomname = temp.getRoomname();
    }

    /**
     * get type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * get room name.
     * @return room name
     */
    public String getRoomname() {
        return roomname;
    }
}

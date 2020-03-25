package edu.rice.comp504.model.parse;

import com.google.gson.Gson;

/**
 * The class that makes users join the rooms.
 */
public class JoinRoomRequest {
    /**
     * request type.
     */
    private String type;
    private String roomname;

    /**
     * parse data.
     * @param data data
     */
    public JoinRoomRequest(String data) {
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
     * @return request type
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

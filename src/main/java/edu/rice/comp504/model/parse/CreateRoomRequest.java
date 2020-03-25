package edu.rice.comp504.model.parse;

import com.google.gson.Gson;

/**
 * The class that creates room.
 */
public class CreateRoomRequest {
    /**
     * request type.
     */
    private String type;
    /**
     * room info.
     */
    private RoomParse room;

    /**
     * constructor.
     *
     * @param data create data
     */
    public CreateRoomRequest(String data) {
        parse(data);
    }

    /**
     * parse data.
     *
     * @param data data
     */
    public void parse(String data) {
        Gson gson = new Gson();
        CreateRoomRequest temp = gson.fromJson(data, CreateRoomRequest.class);
        this.type = temp.getType();
        this.room = temp.getRoom();
    }

    /**
     * get type.
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * get room.
     *
     * @return room
     */
    public RoomParse getRoom() {
        return room;
    }
}

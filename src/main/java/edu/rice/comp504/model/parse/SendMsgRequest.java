package edu.rice.comp504.model.parse;

import com.google.gson.Gson;
import edu.rice.comp504.model.Message;

/**
 * The class that sends messages.
 */
public class SendMsgRequest {
    /**
     * request type.
     */
    private String type;
    /**
     * room name.
     */
    private String roomname;
    /**
     * message.
     */
    private Message msg;

    /**
     * constructor.
     *
     * @param data parse data
     */
    public SendMsgRequest(String data) {
        parse(data);
    }

    /**
     * parse data.
     *
     * @param data data.
     */
    public void parse(String data) {
        Gson gson = new Gson();
        SendMsgRequest temp = gson.fromJson(data, SendMsgRequest.class);
        this.type = temp.getType();
        this.roomname = temp.getRoomname();
        this.msg = temp.getMsg();
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
     * get room name.
     *
     * @return room name
     */
    public String getRoomname() {
        return roomname;
    }

    /**
     * get messages.
     *
     * @return messages
     */
    public Message getMsg() {
        return msg;
    }
}

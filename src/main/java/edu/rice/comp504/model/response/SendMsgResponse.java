package edu.rice.comp504.model.response;

import com.google.gson.Gson;
import edu.rice.comp504.model.Message;

/**
 * The SendMsgResponse class.
 */
public class SendMsgResponse implements ResponseAdapter {
    /**
     * Message type.
     */
    String type;
    /**
     * Room name.
     */
    String roomname;
    /**
     * Message content.
     */
    Message msg;

    /**
     * Constructor.
     *
     * @param roomname room name
     * @param msg      message
     */
    public SendMsgResponse(String roomname, Message msg) {
        this.type = "SEND_MSG";
        this.roomname = roomname;
        this.msg = msg;
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
     * Get roomname.
     *
     * @return roomname
     */
    public String getRoomname() {
        return roomname;
    }

    /**
     * Get message.
     *
     * @return message
     */
    public Message getMsg() {
        return msg;
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

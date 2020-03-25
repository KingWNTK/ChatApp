package edu.rice.comp504.model;

import com.google.gson.Gson;

import java.util.Date;

/**
 * The message class representing the messages we send between the frontend and backend.
 */
public class Message {
    /**
     * sender.
     */
    private String from;
    /**
     * receiver.
     */
    private String to;
    /**
     * whether it is toAll message.
     */
    private boolean toAll;
    /**
     * text context.
     */
    private String text;
    /**
     * whether it is system message.
     */
    private boolean isSysMsg;
    /**
     * timestamp.
     */
    private long timestamp;

    /**
     * constructor.
     *
     * @param from sender
     * @param to receiver
     * @param toAll whether it is toAll message
     * @param text text context
     * @param isSysMsg whether it is system message
     * @param timestamp timestamp
     */
    public Message(String from, String to, boolean toAll, String text, boolean isSysMsg, long timestamp) {
        this.from = from;
        this.to = to;
        this.toAll = toAll;
        this.text = text;
        this.isSysMsg = isSysMsg;
        if (timestamp == 0) {
            Date d = new Date();
            this.timestamp = d.getTime();
        } else {
            this.timestamp = timestamp;
        }
    }

    /**
     * constructor.
     *
     * @param data parse data
     */
    public Message(String data) {
        parse(data);
    }

    /**
     * parse data.
     *
     * @param data data
     */
    public void parse(String data) {
        Gson gson = new Gson();
        Message temp = gson.fromJson(data, Message.class);

        this.from = temp.getFrom();
        this.to = temp.getTo();
        this.toAll = temp.getToAll();
        this.text = temp.getText();
        this.isSysMsg = temp.getIsSysMsg();
        this.timestamp = timestamp;
    }

    /**
     * get sender.
     *
     * @return sender
     */
    public String getFrom() {
        return from;
    }

    /**
     * get receiver.
     *
     * @return receiver
     */
    public String getTo() {
        return to;
    }

    /**
     * get text context.
     *
     * @return text context
     */
    public String getText() {
        return text;
    }

    /**
     * get toAll.
     *
     * @return get toAll
     */
    public boolean getToAll() {
        return toAll;
    }

    /**
     * get isSysMsg.
     *
     * @return isSysMsg
     */
    public boolean getIsSysMsg() {
        return isSysMsg;
    }

    /**
     * get timestamp.
     *
     * @return timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

}

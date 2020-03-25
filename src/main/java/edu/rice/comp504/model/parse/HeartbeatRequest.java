package edu.rice.comp504.model.parse;

import com.google.gson.Gson;

/**
 * The Heartbeat Configuration Class.
 */
public class HeartbeatRequest {
    /**
     * request type.
     */
    private String type;

    /**
     * timestamp.
     */
    private long timestamp;

    /**
     * constructor.
     *
     * @param data parse data
     */
    public HeartbeatRequest(String data) {
        parse(data);
    }

    /**
     * parse data.
     *
     * @param data data
     */
    private void parse(String data) {
        Gson gson = new Gson();
        HeartbeatRequest temp = gson.fromJson(data, HeartbeatRequest.class);
        this.type = temp.getType();
        this.timestamp = temp.getTimestamp();
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
     * get timestamp.
     *
     * @return timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }
}

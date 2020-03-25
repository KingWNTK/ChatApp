package edu.rice.comp504.model.response;

import com.google.gson.Gson;

import java.util.Date;

/**
 * The heartbeatResponse class.
 */
public class HeartbeatResponse implements ResponseAdapter {
    private String type;
    private long timestamp;

    /**
     * Constructor for heartbeat response.
     */
    public HeartbeatResponse() {
        this.type = "HEARTBEAT";
        Date d = new Date();
        this.timestamp = d.getTime();
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

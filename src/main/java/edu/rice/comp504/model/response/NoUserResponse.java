package edu.rice.comp504.model.response;

import com.google.gson.Gson;

/**
 * The NoUserResponse class.
 */
public class NoUserResponse implements ResponseAdapter {
    /**
     * Message type.
     */
    private String type;
    /**
     * User name.
     */
    private String username;

    /**
     * Constructor.
     *
     * @param username username
     */
    public NoUserResponse(String username) {
        this.type = "NO_USER";
        this.username = username;
    }

    /**
     * Get type.
     *
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get username.
     *
     * @return username
     */
    public String getUsername() {
        return this.username;
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

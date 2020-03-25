package edu.rice.comp504.model.response;

import com.google.gson.Gson;

/**
 * The UserInUseResponse class.
 */
public class UserInUseResponse implements ResponseAdapter {
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
    public UserInUseResponse(String username) {
        this.type = "USER_IN_USE";
        this.username = username;
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
     * Get username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
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

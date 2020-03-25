package edu.rice.comp504.model.response;

import com.google.gson.Gson;

/**
 * The UserExistResponse class.
 */
public class UserExistResponse implements ResponseAdapter {
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
    public UserExistResponse(String username) {
        this.type = "USER_EXIST";
        this.username = username;
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

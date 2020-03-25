package edu.rice.comp504.model.parse;

import com.google.gson.Gson;

/**
 * The class that check users' data.
 */
public class CheckUserRequest {
    /**
     * type of request.
     */
    private String type;
    /**
     * username of request.
     */
    private String username;

    /**
     * constructor.
     * @param data parse data
     */
    public CheckUserRequest(String data) {
        parse(data);
    }

    /**
     * parse data.
     * @param data message data
     */
    public void parse(String data) {
        Gson gson = new Gson();
        CheckUserRequest temp = gson.fromJson(data, CheckUserRequest.class);
        this.type = temp.getType();
        this.username = temp.getUsername();
    }

    /**
     * get type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * get username.
     * @return username
     */
    public String getUsername() {
        return username;
    }
}

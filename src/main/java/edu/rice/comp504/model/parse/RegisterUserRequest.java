package edu.rice.comp504.model.parse;

import com.google.gson.Gson;

/**
 * The class that registers users.
 */
public class RegisterUserRequest {
    /**
     * request type.
     */
    private String type;
    /**
     * user.
     */
    private UserParse user;

    /**
     * parse data.
     * @param data data
     */
    public RegisterUserRequest(String data) {
        parse(data);
    }

    /**
     * parse data.
     * @param data data
     */
    public void parse(String data) {
        Gson gson = new Gson();
        RegisterUserRequest temp = gson.fromJson(data, RegisterUserRequest.class);

        this.type = temp.getType();
        this.user = temp.getUserParse();
    }

    /**
     * get type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * get user.
     * @return user
     */
    public UserParse getUserParse() {
        return user;
    }
}

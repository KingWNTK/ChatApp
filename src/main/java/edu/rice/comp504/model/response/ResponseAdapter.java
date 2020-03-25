package edu.rice.comp504.model.response;

import com.google.gson.Gson;

/**
 * An interface to get a json representation for response to clients.
 */
public interface ResponseAdapter {

    /**
     * get a JSON string to respond.
     * @param gson an Gson instance.
     * @return a JSON representation.
     */
    public String getJsonRepresentation(Gson gson);
}

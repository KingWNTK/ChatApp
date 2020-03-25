package edu.rice.comp504.model.parse;

import com.google.gson.Gson;

/**
 * The class that updates the room information.
 */
public class UpdateRoomRequest {
    /**
     * type.
     */
    private String type;
    /**
     * room name.
     */
    private String roomname;
    /**
     * min age.
     */
    private int ageMin;
    /**
     * max age.
     */
    private int ageMax;
    /**
     * areas.
     */
    private String[] areas;
    /**
     * schools.
     */
    private String[] schools;

    /**
     * constructor.
     *
     * @param data parse data
     */
    public UpdateRoomRequest(String data) {
        parse(data);
    }

    /**
     * parse data.
     *
     * @param data data
     */
    public void parse(String data) {
        System.out.println("(****");
        System.out.println(data);
        Gson gson = new Gson();
        UpdateRoomRequest temp = gson.fromJson(data, UpdateRoomRequest.class);
        this.type = temp.getType();
        this.roomname = temp.getRoomname();
        this.ageMin = temp.getAgeMin();
        this.ageMax = temp.getAgeMax();
        this.areas = temp.getAreas();
        this.schools = temp.getSchools();
    }

    /**
     * get min age.
     *
     * @return min age
     */
    public int getAgeMin() {
        return ageMin;
    }

    /**
     * get max age.
     *
     * @return max age
     */
    public int getAgeMax() {
        return ageMax;
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
     * get room name.
     *
     * @return room name
     */
    public String getRoomname() {
        return roomname;
    }

    /**
     * get areas.
     *
     * @return areas
     */
    public String[] getAreas() {
        return areas;
    }

    /**
     * get schools.
     *
     * @return schools
     */
    public String[] getSchools() {
        return schools;
    }

}

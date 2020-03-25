package edu.rice.comp504.model.parse;

import com.google.gson.Gson;

/**
 * The class that parses user string.
 */
public class UserParse {
    /**
     * username.
     */
    private String username;
    /**
     * age.
     */
    private int age;
    /**
     * school.
     */
    private String school;
    /**
     * area.
     */
    private String area;

    /**
     * parse user.
     * @param userId user id
     * @param age age
     * @param school school
     * @param area area
     */
    public UserParse(String userId, int age, String school, String area) {
        this.username = userId;
        this.age = age;
        this.school = school;
        this.area = area;
    }

    /**
     * parse data.
     * @param data data
     */
    public void parse(String data) {
        Gson gson = new Gson();
        UserParse temp = gson.fromJson(data, UserParse.class);

        this.username = temp.getUsername();
        this.age = temp.getAge();
        this.school = temp.getSchool();
        this.area = temp.getArea();
    }

    /**
     * get user name.
     * @return user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * get school.
     * @return school
     */
    public String getSchool() {
        return school;
    }

    /**
     * get age.
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * get area.
     * @return area
     */
    public String getArea() {
        return area;
    }

}

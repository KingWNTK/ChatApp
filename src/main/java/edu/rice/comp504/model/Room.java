package edu.rice.comp504.model;

import java.util.HashSet;

/**
 * The room class, representing the chat room.
 */
public class Room {
    /**
     * min age.
     */
    private int ageMin;
    /**
     * max age.
     */
    private int ageMax;
    /**
     * areas of the chat room.
     */
    private HashSet<String> areas;
    /**
     * schools of the chat room.
     */
    private HashSet<String> schools;
    /**
     * userModels in the char room.
     */
    private HashSet<User> users;
    /**
     * history messages in the chat room.
     */
    private HashSet<Message> messages;
    /**
     * owners of the chat room.
     */
    private User owner;

    private String roomId;

    /**
     * Constructor.
     *
     * @param ageMin  Min age
     * @param ageMax  Max age
     * @param areas   Areas of the chat room
     * @param schools Schools of the chat room
     * @param users   Users in the char room
     * @param msg     History messages in the chat room
     * @param owner   owners of the chat room
     */
    public Room(int ageMin, int ageMax, HashSet<String> areas, HashSet<String> schools, HashSet<User> users, HashSet<Message> msg, User owner, String roomId) {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.areas = areas;
        this.schools = schools;
        this.users = users;
        this.messages = msg;
        this.owner = owner;
        this.roomId = roomId;
    }

    /**
     * check whether the age is valid.
     *
     * @param age age
     * @return whether the age is valid.
     */
    public boolean checkAge(int age) {
        return age >= ageMin && age <= ageMax;
    }

    /**
     * check whether the area is valid.
     *
     * @param area area
     * @return whether the area is valid
     */
    public boolean checkArea(String area) {
        return areas == null || areas.contains(area);
    }

    public boolean checkSchool(String school) {
        return schools == null || schools.contains(school);
    }

    public boolean check(User user) {
        return checkAge(user.getAge()) && checkArea(user.getArea()) && checkSchool(user.getSchool());
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
     * return valid areas.
     *
     * @return areas
     */
    public HashSet<String> getAreas() {
        return areas;
    }

    /**
     * return valid schools.
     *
     * @return schools
     */
    public HashSet<String> getSchools() {
        return schools;
    }

    /**
     * return userModels in the chat room.
     *
     * @return userModels
     */
    public HashSet<User> getUsers() {
        return users;
    }

    /**
     * return messages in the chat room.
     *
     * @return messages
     */
    public HashSet<Message> getMessages() {
        return messages;
    }

    /**
     * add a new user in the chat room.
     *
     * @param u user
     */
    public void addUser(User u) {
        users.add(u);
    }

    /**
     * remove a user in the chat room.
     *
     * @param user user
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * Get room id.
     *
     * @return room id
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Get room owner.
     *
     * @return room owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Set room owner.
     *
     * @param owner owner to be set
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Get if room is empty.
     *
     * @return whether the room is empty
     */
    public boolean isEmpty() {
        return users.size() == 0;
    }

    /**
     * Get first user.
     *
     * @return the first user
     */
    public User getFirstUser() {
        return users.iterator().next();
    }

    /**
     * Get all user IDs.
     *
     * @return all user IDs as a HashSet
     */
    public HashSet<String> getAllUserIds() {
        HashSet<String> allUserIds = new HashSet<>();
        for (User user : users) {
            allUserIds.add(user.getId());
        }
        return allUserIds;
    }

    /**
     * Get all user IDs as an array of strings.
     *
     * @return all user IDs as an array of strings
     */
    public String[] getAllUserIdList() {
        HashSet<String> allUserIds = getAllUserIds();
        String[] list = new String[allUserIds.size()];
        return allUserIds.toArray(list);
    }

    /**
     * Add a message.
     *
     * @param msg the message
     */
    public void addMessage(Message msg) {
        this.messages.add(msg);
    }

    /**
     * Get messages for a specific user.
     *
     * @param userId the userID to be retrieved
     * @return messages for the user
     */
    public HashSet<Message> getMessageForUser(String userId) {
        HashSet<Message> ret = new HashSet<>();
        for (Message msg : messages) {
            if (userId.equals(msg.getFrom()) || userId.equals(msg.getTo()) || msg.getToAll() || msg.getIsSysMsg()) {
                ret.add(msg);
            }
        }
        return ret;
    }

    /**
     * Set the area passed as a HashSet of strings.
     *
     * @param areas the area to be set
     */
    public void setAreas(HashSet<String> areas) {
        this.areas = (HashSet<String>) areas.clone();
    }

    /**
     * Set the area passed as an array of strings.
     *
     * @param areas the areas to be set
     */
    public void setAreas(String[] areas) {
        HashSet<String> newArea = new HashSet<>();
        for (String area : areas) {
            newArea.add(area);
        }
        this.areas = newArea;
    }


    /**
     * Set schools passed as a HashSet of strings.
     *
     * @param schools the schools to be set
     */
    public void setSchools(HashSet<String> schools) {
        this.schools = (HashSet<String>) schools.clone();
    }


    /**
     * Set schools passed as an array of strings.
     *
     * @param schools the schools to be set
     */
    public void setSchools(String[] schools) {
        HashSet<String> newSchool = new HashSet<>();
        for (String school : schools) {
            newSchool.add(school);
        }
        this.schools = newSchool;
    }

    /**
     * Set minimum age.
     *
     * @param minAge the minimum age
     */
    public void setAgeMin(int minAge) {
        this.ageMin = minAge;
    }

    /**
     * Set maximum age.
     *
     * @param maxAge the maximum age
     */
    public void setAgeMax(int maxAge) {
        this.ageMax = maxAge;
    }


    /**
     * Get area names.
     *
     * @return the area names
     */
    public String[] getAreaName() {
        String[] areaNames = new String[areas.size()];
        int i = 0;
        for (String each : areas) {
            areaNames[i] = each;
            i += 1;
        }
        return areaNames;
    }

    /**
     * Get school names.
     *
     * @return the school names
     */
    public String[] getSchoolName() {
        String[] schoolNames = new String[schools.size()];
        int i = 0;
        for (String each : schools) {
            schoolNames[i] = each;
            i += 1;
        }
        return schoolNames;
    }
}

package edu.rice.comp504.model.parse;

import com.google.gson.Gson;
import edu.rice.comp504.model.Message;

import java.util.*;
import java.util.HashSet;

/**
 * The class that parses the room string.
 */
public class RoomParse {
    /**
     * owner of the room.
     */
    private String owner;
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
     * areas of the chat room.
     */
    private HashSet<String> areas;
    /**
     * schools of the room.
     */
    private HashSet<String> schools;
    /**
     * users in the room.
     */
    private HashSet<String> users;
    /**
     * messages in the room.
     */
    private Set<Message> msgs = new TreeSet<>(new Comparator<Message>() {
        @Override
        public int compare(Message s1, Message s2) {
            return (int)(s1.getTimestamp() - s2.getTimestamp());
        }
    });

    /**
     * chat room.
     * @param owner owner
     * @param roomname room name
     * @param ageMin min age
     * @param ageMax max age
     * @param areas areas
     * @param schools schools
     * @param users users
     * @param msgs messages
     */
    public RoomParse(String owner, String roomname, int ageMin, int ageMax, HashSet<String> areas, HashSet<String> schools, HashSet<String> users, HashSet<Message> msgs) {
        this.owner = owner;
        this.roomname = roomname;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.areas = areas;
        this.schools = schools;
        this.users = users;
        this.msgs.addAll(msgs);
    }

    /**
     * parse data.
     * @param data data
     */
    public void parse(String data) {
        Gson gson = new Gson();
        RoomParse temp = gson.fromJson(data, RoomParse.class);

        this.owner = temp.getOwner();
        this.roomname = temp.getRoomname();
        this.ageMax = temp.getAgeMax();
        this.ageMin = temp.getAgeMin();
        this.areas = temp.getAreas();
        this.schools = temp.getSchools();
        this.users = temp.getUsers();
        this.msgs = temp.getMsgs();
    }

    /**
     * get messages.
     * @return messages
     */
    public Set<Message> getMsgs() {
        return msgs;
    }

    /**
     * get message HashSet.
     * @return message HashSet
     */
    public HashSet<Message> getMsgsHash() {
        return new HashSet<>(msgs);
    }

    /**
     * get areas.
     * @return areas
     */
    public HashSet<String> getAreas() {
        return areas;
    }

    /**
     * get schools.
     * @return schools
     */
    public HashSet<String> getSchools() {
        return schools;
    }

    /**
     * get users.
     * @return users
     */
    public HashSet<String> getUsers() {
        return users;
    }

    /**
     * get max age.
     * @return max age
     */
    public int getAgeMax() {
        return ageMax;
    }

    /**
     * get min age.
     * @return min age
     */
    public int getAgeMin() {
        return ageMin;
    }

    /**
     * get owner.
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * get room name.
     * @return room name
     */
    public String getRoomname() {
        return roomname;
    }

    /**
     * set messages.
     * @param msgs messages
     */
    public void setMsgs(HashSet<Message> msgs) {
        this.msgs.addAll(msgs);
    }
}

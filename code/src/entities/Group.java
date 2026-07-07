package entities;

import java.util.List;

public class Group {
    public final String id;
    public final String name;
    public List<User> participants;

    public Group(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(User user) {
        this.participants.add(user);
    }
}

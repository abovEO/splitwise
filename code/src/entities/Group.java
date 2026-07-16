package entities;

import java.util.List;
import java.util.UUID;

public class Group {
    private final String id;
    private final String name;
    private final List<User> participants;

    public Group( String name, List<User> members){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.participants = members;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void addMember(User user) {
        this.participants.add(user);
    }
}

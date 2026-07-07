package entities;

import java.util.UUID;

public class User {
    private final String id;
    private String name;
    private String email;
    private Transactions transactions;

    public User(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public void manageUserAccount(String name, String email) {
        if (name != null) {
            this.name = name;
        }
        if (email != null) {
            this.email = email;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Transactions getTransactions() {
        return transactions;
    }
}

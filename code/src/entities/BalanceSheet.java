package entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {
    public final User owner;
    public final Map<User, Double> balances = new ConcurrentHashMap<>();

    public BalanceSheet(User owner) {
        this.owner = owner;
    }

    public Map<User, Double> getBalances() {
        return balances;
    }

    public synchronized void adjustBalance(User otherUser, double amount) {
        if (owner.equals(otherUser)) {
            return;
        }
        balances.merge(otherUser, amount, Double::sum);
    }

    public void showBalance() {
        System.out.println("Balance Sheet for " + owner.getName() + ": ");
        if (balances.isEmpty()) {
            System.out.println("All settled up!!");
            return;
        }
        double totalOwedToOwner = 0;
        double totalOwedByOwner = 0;

        for (Map.Entry<User, Double> balance : balances.entrySet()) {
            User user = balance.getKey();
            double amount = balance.getValue();

            if (amount > 0) {
                System.out.println(user.getName() + " owes " + owner.getName() + " $" + amount);
                totalOwedToOwner += amount;
            } else {
                System.out.println(owner.getName() + " owes " + user.getName() + " $" + amount);
                totalOwedByOwner += amount;
            }

            System.out.println("Total amount owed to " + owner.getName()+ " : $" + totalOwedToOwner);
            System.out.println("Total amount owed by " + owner.getName()+ " : $" + totalOwedByOwner);
        }
    }
}

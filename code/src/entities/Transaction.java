package entities;

public class Transaction {
    public final User from;
    public final User to;
    public final double amount;

    public Transaction(User to, User from, double amount) {
        this.to = to;
        this.from = from;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return (from.getName() + " pays " + to.getName() + String.format("%2f", amount));
    }
}

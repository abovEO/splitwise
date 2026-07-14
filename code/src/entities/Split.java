package entities;

public class Split {
    public final User user;
    public final double amount;

    public Split(User user, double amount){
        this.user = user;
        this.amount = amount;
    }
}

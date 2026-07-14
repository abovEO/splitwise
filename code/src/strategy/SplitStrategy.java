package strategy;

import entities.Split;
import entities.User;

import java.util.List;

public interface SplitStrategy {
    public List<Split> calculateSplits(double totalAmount, List<User> participants, User paidBy, List<Double> splitValues);
}

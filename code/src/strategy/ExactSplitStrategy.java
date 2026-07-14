package strategy;

import entities.Split;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> participants, User paidBy, List<Double> splitValues) {
        List<Split> splits = new ArrayList<>();

        if (participants.size() != splitValues.size())
            throw new IllegalArgumentException("Number of participants and splits value should match");

        if (Math.abs(splitValues.stream().mapToDouble(Double::doubleValue).sum() - totalAmount) > 0.01)
            throw new IllegalArgumentException("Sum of splits should be equal to total amount");

        for (int i = 0; i < participants.size(); i++) {
            splits.add(new Split(participants.get(i), splitValues.get(i)));
        }
        return splits;
    }
}

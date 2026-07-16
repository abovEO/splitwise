import entities.Group;
import entities.Transaction;
import entities.User;
import entities.Expense;
import strategy.EqualSplitStrategy;
import strategy.ExactSplitStrategy;
import strategy.PercentageSplitStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        SplitwiseService service = SplitwiseService.getInstance();

        User user_1 = service.addUser("User 1", "user_1@a.com");
        User user_2 = service.addUser( "User 2", "user_2@b.com");
        User user_3 = service.addUser("User 3", "user_3@c.com");
        User user_4 = service.addUser("User 4", "user_4@d.com");

        Group group = service.addGroup("Trip Expense", List.of(user_2, user_1, user_3, user_4));

        System.out.println("--- Use Case 1: Equal Split ---");
        service.createExpense(new Expense.ExpenseBuilder()
                .setDescription("Dinner")
                .setAmount(1000.0)
                .setPaidBy(user_1)
                .setParticipants(Arrays.asList(user_1, user_2, user_3, user_4))
                .setSplitStrategy(new EqualSplitStrategy())
        );

        service.showBalanceSheet(user_1.getId());
        service.showBalanceSheet(user_2.getId());
        service.showBalanceSheet(user_3.getId());
        service.showBalanceSheet(user_4.getId());
        System.out.println();

        System.out.println("--- Use Case 2: Exact Split ---");
        service.createExpense(new Expense.ExpenseBuilder()
                .setDescription("Lunch")
                .setAmount(2000.0)
                .setPaidBy(user_2)
                .setParticipants(Arrays.asList(user_1, user_2, user_3, user_4))
                .setSplitStrategy(new ExactSplitStrategy())
                .setSplitValues(Arrays.asList(350.0,650.0,500.0,500.0))
        );

        service.showBalanceSheet(user_1.getId());
        service.showBalanceSheet(user_2.getId());
        service.showBalanceSheet(user_3.getId());
        service.showBalanceSheet(user_4.getId());
        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println("--- Use Case 2: percentage Split ---");
        service.createExpense(new Expense.ExpenseBuilder()
                .setDescription("Lunch")
                .setAmount(1200)
                .setPaidBy(user_3)
                .setParticipants(Arrays.asList(user_1, user_2, user_3))
                .setSplitStrategy(new PercentageSplitStrategy())
                .setSplitValues(Arrays.asList(30.0,40.0,30.0))
        );

        service.showBalanceSheet(user_1.getId());
        service.showBalanceSheet(user_2.getId());
        service.showBalanceSheet(user_3.getId());
        service.showBalanceSheet(user_4.getId());
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("--- Use Case 4: Simplify Group Debts for 'Trip Expense' ---");
        List<Transaction> simplifiedDebts = service.simplifyGroupDebt(group.getId());

        if (simplifiedDebts.isEmpty()) {
            System.out.println("All debts are settled within the group!");
        } else {
            simplifiedDebts.forEach(System.out::println);
        }

        System.out.println();
        System.out.println("--- Use Case 5: Partial Settlement ---");
        service.settleUp(user_3.getId(), user_1.getId(), 300);

        System.out.println();
        System.out.println("--- Balances After Partial Settlement ---");

        service.showBalanceSheet(user_1.getId());
        service.showBalanceSheet(user_2.getId());
        service.showBalanceSheet(user_3.getId());
        service.showBalanceSheet(user_4.getId());
        System.out.println();

    }
}
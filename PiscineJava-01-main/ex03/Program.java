import java.util.LinkedList;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User recipient = new User("recipient", 40);
        User sender = new User("sender", 90);

        Transaction transaction1 = new Transaction(UUID.randomUUID(), recipient, sender, Transaction.TransferCategory.DEBITS);
        Transaction transaction2 = new Transaction(UUID.randomUUID(), recipient, sender, Transaction.TransferCategory.CREDITS);
        Transaction transaction3 = new Transaction(UUID.randomUUID(), recipient, sender, Transaction.TransferCategory.DEBITS);
        Transaction transaction4 = new Transaction(UUID.randomUUID(), recipient, sender, Transaction.TransferCategory.CREDITS);
        Transaction transaction5 = new Transaction(UUID.randomUUID(), recipient, sender, Transaction.TransferCategory.DEBITS);


        transaction1.setAmount(1);
        transaction2.setAmount(-2);
        transaction3.setAmount(3);
        transaction4.setAmount(-4);
        transaction5.setAmount(5);

        recipient.getTransactionsList().addTransaction(transaction1);
        recipient.getTransactionsList().addTransaction(transaction2);
        recipient.getTransactionsList().addTransaction(transaction3);
        recipient.getTransactionsList().addTransaction(transaction4);
        recipient.getTransactionsList().addTransaction(transaction5);

        Transaction[] transactions = recipient.getTransactionsList().toArray();

        for (int i = 0; i < transactions.length; i++) {
            System.out.println(transactions[i].getAmount());
        }

        System.out.println("==========================");
        recipient.getTransactionsList().deleteTransaction(transaction1.getIdentifier());

        transactions = recipient.getTransactionsList().toArray();
        for (int i = 0; i < transactions.length; i++) {
            System.out.println(transactions[i].getAmount());
        }
    }
}

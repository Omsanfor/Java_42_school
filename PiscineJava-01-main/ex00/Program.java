import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User oleg = new User(1, "Oleg", -1);
        System.out.println(oleg.getBalance());

        User victor = new User(2, "Victor", 50);
        System.out.println(victor.getBalance());

        Transaction transaction = new Transaction(UUID.randomUUID(), oleg, victor, Transaction.TransferCategory.DEBITS);
        System.out.println(transaction.getIdentifier());
        transaction.setAmount(-50);
        transaction.setAmount(25);

        Transaction transaction1 = new Transaction(UUID.randomUUID(), oleg, victor, Transaction.TransferCategory.CREDITS);
        System.out.println(transaction1.getIdentifier());
        transaction1.setAmount(-50);
        transaction1.setAmount(25);
    }

}

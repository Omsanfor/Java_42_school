import java.util.UUID;

public class Program {
    public static void main(String[] args) throws UsersArrayList.UserNotFoundException, TransactionsService.IllegalTransactionException, TransactionsLinkedList.TransactionNotFoundException {
        User Oleg = new User("Oleg", 50);
        User Igor = new User("Igor", 100);
        User Dima = new User("Dima", 200);
        User Anna = new User("Anna", 400);
        User Vika = new User("Vika", 800);

        TransactionsService service = new TransactionsService();

        service.addUser(Oleg);
        service.addUser(Igor);
        service.addUser(Dima);
        service.addUser(Anna);

        System.out.println("=============Test 1=============");


        System.out.println("Баланс Igor = " + service.getBalance(Igor.getIdentifier())+ "\t" + "Баланс Oleg = " + service.getBalance(Oleg.getIdentifier()));
        System.out.println("Отправитель Igor, получаетль Oleg, сумма 10");
        service.makeTransferTransaction(Oleg.getIdentifier(), Igor.getIdentifier(), 10);
        System.out.println("Баланс Igor = " + Igor.getBalance() + "\t" + "Баланс Oleg = " + Oleg.getBalance());
        System.out.println("Igor - " + service.getTransactionsUser(Igor.getIdentifier())[0]);
        System.out.println("Oleg - " + service.getTransactionsUser(Oleg.getIdentifier())[0]);

        System.out.println();
        System.out.println("Отправитель Igor, получаетль Oleg, сумма -10");
        service.makeTransferTransaction(Oleg.getIdentifier(), Igor.getIdentifier(), -10);
        System.out.println("Баланс Igor = " + service.getBalance(Igor.getIdentifier())+ "\t" + "Баланс Oleg = " + service.getBalance(Oleg.getIdentifier()));
        System.out.println("Igor - " + service.getTransactionsUser(Igor.getIdentifier())[1]);
        System.out.println("Oleg - " + service.getTransactionsUser(Oleg.getIdentifier())[1]);
        System.out.println();

        System.out.println("=============Test 2=============");
        System.out.println("Удаление 1-ой транзакции Oleg - DEBITS");
        service.deleteTransaction(Oleg.getIdentifier(), Oleg.getTransactionsList().toArray()[0].getIdentifier());
        System.out.println(service.getTransactionsUser(Oleg.getIdentifier())[0]);
        System.out.println();
        System.out.println("=============Test 3=============");
        Transaction[] invalid = service.validateTransactions();
        System.out.println(invalid[0]);
    }
}
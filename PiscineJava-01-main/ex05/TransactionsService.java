import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;
    private TransactionsList unpairTransaction;

    public TransactionsService() {
        usersList = new UsersArrayList();
    }

    public void addUser(User user) {
        usersList.addUser(user);
        unpairTransaction = new TransactionsLinkedList();
    }

    public Integer getBalance(Integer id) {
        return usersList.getUserById(id).getBalance();
    }

    public void makeTransferTransaction(Integer idRecipient, Integer idSender, Integer amount) {
        if (amount == 0)
            return;

        UUID identifier = UUID.randomUUID();
        User sender = usersList.getUserById(idSender);
        User recipient = usersList.getUserById(idRecipient);

        Transaction DEBITS = new Transaction(identifier, recipient, sender, Transaction.TransferCategory.DEBITS);
        Transaction CREDITS = new Transaction(identifier, recipient, sender, Transaction.TransferCategory.CREDITS);

        if (amount > 0) {
            if (sender.getBalance() - amount < 0)
                throw new IllegalTransactionException();
            sender.setBalance(sender.getBalance() - amount);
            CREDITS.setAmount(-amount);
            sender.getTransactionsList().addTransaction(CREDITS);
            DEBITS.setAmount(amount);
            recipient.setBalance(recipient.getBalance() + amount);
            recipient.getTransactionsList().addTransaction(DEBITS);
        } else {
            if (recipient.getBalance() + amount < 0)
                throw new IllegalTransactionException();
            recipient.setBalance(recipient.getBalance() + amount);
            CREDITS.setAmount(amount);
            recipient.getTransactionsList().addTransaction(CREDITS);

            sender.setBalance(sender.getBalance() - amount);
            DEBITS.setAmount(-amount);
            sender.getTransactionsList().addTransaction(DEBITS);
        }

    }

    public Transaction[] getTransactionsUser(Integer id) {
        return usersList.getUserById(id).getTransactionsList().toArray();
    }

    public Transaction[] validateTransactions() {
        return unpairTransaction.toArray();
    }


    public void deleteTransaction(Integer userId, String UUID) {
        User user = usersList.getUserById(userId);
        user.getTransactionsList().deleteTransaction(UUID);
        for (int i = 0; i < usersList.getSize(); i++) {
            Transaction[] transactions = usersList.getUserById(i).getTransactionsList().toArray();
            int size = usersList.getUserById(i).getTransactionsList().getSize();
            for(int j = 0; j < size; j++) {
                if (transactions[j].getIdentifier().equals(UUID))
                    unpairTransaction.addTransaction(transactions[j]);
            }
        }
    }

    public UsersList getUserList() {
        return usersList;
    }

    public class IllegalTransactionException extends RuntimeException {
    }
}

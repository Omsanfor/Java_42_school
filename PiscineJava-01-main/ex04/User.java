public class User {
    private Integer identifier;
    private String name;
    private Integer balance;
    private TransactionsList transactionsList;

    public User(String name, Integer balance) {
        transactionsList = new TransactionsLinkedList();
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance < 0)
            this.balance = 0;
        else
            this.balance = balance;
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

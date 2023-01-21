public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public User(Integer identifier, String name, Integer balance) {
        this.identifier = identifier;
        this.name = name;
        if (balance < 0)
            this.balance = 0;
        else
            this.balance = balance;
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
}

import java.util.UUID;

public class Transaction {
    private String identifier;
    private User recipient;
    private User sender;
    private TransferCategory category;
    private Integer amount;


    public Transaction(UUID identifier, User recipient, User sender, TransferCategory category) {
        this.identifier = identifier.toString();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        if (category.equals(TransferCategory.DEBITS) && amount > 0) {
            this.amount = amount;
        } else if (category.equals(TransferCategory.CREDITS) && amount < 0) {
            this.amount = amount;
        } else {
            System.out.println("The amount does not match the category");
        }
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public String getIdentifier() {
        return identifier;
    }

    public TransferCategory getCategory() {
        return category;
    }

    public enum TransferCategory {
        DEBITS, CREDITS
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "identifier='" + identifier + '\'' +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", category=" + category +
                ", amount=" + amount +
                '}';
    }
}
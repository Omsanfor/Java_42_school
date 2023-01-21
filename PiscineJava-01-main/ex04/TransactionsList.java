public interface TransactionsList {
    public void addTransaction(Transaction transaction);
    public void deleteTransaction(String identifier);
    public Transaction[] toArray();
    public int getSize();
}

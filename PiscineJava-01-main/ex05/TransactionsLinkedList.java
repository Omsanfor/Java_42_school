public class TransactionsLinkedList implements TransactionsList{
    private Node head;
    private Node tail;
    private int size;

    private static class Node {
        private final Transaction value;
        private Node next;
        private Node prev;
        public Node(Transaction value) {
            this.value = value;
        }
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (head == null) {
            Node firstNode = new Node(transaction);
            head = firstNode;
            size++;
            return ;
        }
        Node addNote = new Node(transaction);
        if (tail == null) {
            tail = addNote;
            tail.prev = head;
            head.next = tail;
            size++;
            return;
        }
        addNote.prev = tail;
        tail.next = addNote;
        tail = addNote;
        size++;
    }

    @Override
    public void deleteTransaction(String identifier){
        if (head == null)
            return ;
        if (head.value.getIdentifier() == identifier) {
            head = head.next;
            head.prev = null;
            size--;
            return ;
        }

        if (tail.value.getIdentifier() == identifier) {
            tail = tail.prev;
            tail.next = null;
            size--;
            return ;
        }

        Node currentNode = head;
        while(currentNode.next != null) {
            if (currentNode.next.value.getIdentifier().equals(identifier)) {
                currentNode.next = currentNode.next.next;
                currentNode.next.prev = currentNode;
                size--;
                return;
            }
            currentNode = currentNode.next;
        }
        throw new TransactionNotFoundException();
    }

    public int getSize() {
        return size;
    }

    @Override
    public Transaction[] toArray() {
        if (head == null)
            return null;
        Transaction[] transactions = new Transaction[size];
        Node currentNode = head;
        for(int i = 0;currentNode != null; i++) {
            transactions[i] = currentNode.value;
            currentNode = currentNode.next;
        }
        return transactions;
    }

    public Transaction getTransactionByUUID(String UUID) {
        Node t = head;
        while(t != null) {
            if (t.value.getIdentifier().equals(UUID))
                return t.value;
            t = t.next;
        }
        return null;
    }

    public class TransactionNotFoundException extends RuntimeException {

    }
}

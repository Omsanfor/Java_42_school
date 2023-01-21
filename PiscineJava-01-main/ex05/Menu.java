import java.util.Scanner;
public class Menu {
    private Scanner scanner;
    private TransactionsService service;
    private String typeMode;

    public Menu(String typeMode) {
        scanner = new Scanner(System.in);
        service = new TransactionsService();
        this.typeMode = typeMode;
    }

    public void start() {
        int exec = 0;
        while(exec != 7) {
            exec = getMenu();
            switch (exec) {
                case 1:
                    addUser();
                    break;
                case 2:
                    getBalance();
                    break;
                case 3:
                    makeTransfer();
                    break;
                case 4:
                    getAllTransactionsByUserId();
                    break;
                case 5:
                    if (typeMode.equals("--profile=dev"))
                        _removeTransfer();
                    break;
                case 6:
                    if (typeMode.equals("--profile=dev"))
                        _checkTransferValidity();
                    break;
            }
            System.out.println("---------------------------------------------------------");
        }
    }

    private void _checkTransferValidity() {
        Transaction [] transaction = {};
        try {
            transaction = service.validateTransactions();
        } catch (Exception e) {}
        if (transaction.length == 0) {
            System.out.println("All transactions are valid");
        } else {
            for (int i = 0; i < transaction.length; i++) {
                System.out.println(transaction[i].getRecipient().getName() + "(id = " +
                (transaction[i].getRecipient().getIdentifier() + 1) + ") " + "has an unacknowledged transfer id = " +
                        transaction[i].getIdentifier() + " from " + transaction[i].getSender().getName() +
                        "(id = " + (transaction[i].getSender().getIdentifier() + 1) + ")");
            }
        }
    }

    private void _removeTransfer() {
        String data;
        String [] data_split;
        Integer idUser;
        String transferId;
        Integer size;
        Transaction [] transactionsUser;
        String [] saveData = new String[3];
        System.out.println("Enter a user ID and a transfer ID:");
        scanner.nextLine();
        data = scanner.nextLine();
        while(true) {
            try {
                data_split = data.split(" ");
                idUser = Integer.parseInt(data_split[0]);
                transferId = data_split[1].trim();
                size = service.getUserList().getUserById(idUser - 1).getTransactionsList().getSize();
                transactionsUser = service.getTransactionsUser(idUser - 1);
                for (int i = 0; i < size; i++) {
                    if (transactionsUser[i].getIdentifier().equals(transferId)) {
                        saveData[0] = transactionsUser[i].getRecipient().getName();
                        saveData[1] = String.valueOf(transactionsUser[i].getRecipient().getIdentifier() + 1);
                        saveData[2] = String.valueOf(transactionsUser[i].getAmount());
                        break;
                    }
                }
                service.deleteTransaction(idUser - 1, transferId);
                break;
            } catch (Exception e) {
                System.out.println("Error - Enter a user ID and a transfer ID:");
                data = scanner.nextLine();
            }
        }
        System.out.println("Transfer To " + saveData[0] + "(id = " +saveData[1] + ") " + saveData[2] + " removed");
    }

    private void getAllTransactionsByUserId() {
        String data;
        Integer id;
        Transaction [] userTransaction;
        Integer balance;
        Integer size;
        System.out.println("Enter a user ID:");
        scanner.nextLine();
        data = scanner.nextLine();
        while(true) {
            try {
                id = Integer.parseInt(data);
                userTransaction = service.getTransactionsUser(id - 1);
                size = service.getUserList().getUserById(id - 1).getTransactionsList().getSize();
                break;
            }catch (Exception e) {
                System.out.println("Error - Enter a user ID:");
                data = scanner.nextLine();
            }
        }
        if (size == 0) {
            System.out.println("Transaction list - Empty");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println("To " + userTransaction[i].getRecipient().getName()
                        + "(id = " + (userTransaction[i].getRecipient().getIdentifier() + 1) + ") " + userTransaction[i].getAmount()
                        + " with id = " + userTransaction[i].getIdentifier());
            }
        }
    }

    private void makeTransfer() {
        String data;
        Integer id_sender;
        Integer id_recipient;
        Integer amount;
        String [] data_split;
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount:");
        scanner.nextLine();
        data = scanner.nextLine();
        while(true) {
            try {
                data_split= data.split(" ");
                id_sender = Integer.parseInt(data_split[0]);
                id_recipient = Integer.parseInt(data_split[1]);
                amount = Integer.parseInt(data_split[2]);
                service.getBalance(id_sender - 1);
                service.getBalance(id_recipient - 1);
                if (amount == 0)
                    throw new Exception();
                service.makeTransferTransaction(id_recipient - 1, id_sender - 1, amount);
                break;
            } catch (Exception e) {
                System.out.println("Error - Enter a sender ID, a recipient ID, and a transfer amount:");
                data = scanner.nextLine();
            }
        }
        System.out.println("The transfer is completed");
    }

    private void getBalance() {
        String data;
        Integer id;
        Integer balance;
        System.out.println("Enter a user ID:");
        scanner.nextLine();
        data = scanner.nextLine();
        while(true) {
            try {
                id = Integer.parseInt(data);
                balance = service.getBalance(id - 1);
                break;
            }catch (Exception e) {
                System.out.println("Error - Enter a user ID:");
                data = scanner.nextLine();
            }
        }
        System.out.println(service.getUserList().getUserById(id - 1).getName() + " - " + balance);
    }

    private void addUser() {
        String data;
        String [] name_balance;
        String name;
        Integer balance;

        System.out.println("Enter a user name and a balance:");
        scanner.nextLine();
        data = scanner.nextLine();
        while(true) {
            try {
                name_balance = data.split(" ");
                name = name_balance[0];
                balance = Integer.parseInt(name_balance[1]);
                break;
            } catch (Exception e) {
                System.out.println("Error - Enter a user name and a balance:");
                data = scanner.nextLine();
            }
        }
        User newUser = new User(name, balance);
        service.addUser(newUser);
        System.out.println("User with id = "+ (newUser.getIdentifier() + 1) + " is added");

    }

    private int getMenu() {
        int temp = 0;
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV - remove a transfer by ID");
        System.out.println("6. DEV - check transfer validity");
        System.out.println("7. Finish execution");
        while(true) {
            if (scanner.hasNextInt()) {
                temp = scanner.nextInt();
                break;
            }
            scanner.nextLine();
        }
        return temp;
    }
}

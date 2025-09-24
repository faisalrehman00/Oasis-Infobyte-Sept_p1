import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;
    
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }
    
    public String toString() {
        return type + " : " + amount;
    }
}

class Account {
    private String userId;
    private String pin;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if(amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdraw", amount));
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Invalid or insufficient balance.");
        }
    }

    public boolean transfer(Account target, double amount) {
        if(amount > 0 && amount <= balance) {
            balance -= amount;
            target.balance += amount;
            transactionHistory.add(new Transaction("Transfer to " + target.userId, amount));
            target.transactionHistory.add(new Transaction("Transfer from " + this.userId, amount));
            System.out.println("Transfer successful.");
            return true;
        } else {
            System.out.println("Transfer failed due to insufficient funds or invalid amount.");
            return false;
        }
    }
}

public class ATMInterfaceFaisal {
    private static List<Account> accounts = new ArrayList<>();
    private static Account currentAccount = null;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        initializeAccounts();
        System.out.println("Welcome to the ATM Interface");
        login();
        if(currentAccount != null) {
            showMenu();
        }
        System.out.println("Thank you for using the ATM. Goodbye!");
    }

    private static void initializeAccounts() {
        accounts.add(new Account("user1", "1234", 5000));
        accounts.add(new Account("user2", "2345", 10000));
        accounts.add(new Account("user3", "3456", 7500));
    }

    private static void login() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        for(Account acc : accounts) {
            if(acc.getUserId().equals(userId) && acc.validatePin(pin)) {
                currentAccount = acc;
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Login failed. Invalid credentials.");
    }

    private static void showMenu() {
        int choice = 0;
        do {
            System.out.println("\nATM Menu");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                choice = 0;
            }

            switch(choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while(choice != 5);
    }

    private static void showTransactionHistory() {
        System.out.println("Transaction History:");
        List<Transaction> history = currentAccount.getTransactionHistory();
        if(history.isEmpty()) {
            System.out.println("No transactions made yet.");
        } else {
            for(Transaction t : history) {
                System.out.println(t);
            }
        }
    }

    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            currentAccount.withdraw(amount);
            System.out.println("Current Balance: " + currentAccount.getBalance());
        } catch(Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            currentAccount.deposit(amount);
            System.out.println("Current Balance: " + currentAccount.getBalance());
        } catch(Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private static void transfer() {
        System.out.print("Enter target User ID for transfer: ");
        String targetId = scanner.nextLine();

        Account targetAccount = null;
        for(Account acc : accounts) {
            if(acc.getUserId().equals(targetId)) {
                targetAccount = acc;
                break;
            }
        }

        if(targetAccount == null) {
            System.out.println("Target account not found.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            currentAccount.transfer(targetAccount, amount);
            System.out.println("Current Balance: " + currentAccount.getBalance());
        } catch(Exception e) {
            System.out.println("Invalid input.");
        }
    }
}

/* 
Sample Output:

Welcome to the ATM Interface
Enter User ID: user1
Enter PIN: 1234
Login successful.

ATM Menu
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option: 3
Enter amount to deposit: 2000
Deposit successful.
Current Balance: 7000.0

ATM Menu
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option: 2
Enter amount to withdraw: 1500
Withdrawal successful.
Current Balance: 5500.0

ATM Menu
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option: 4
Enter target User ID for transfer: user2
Enter amount to transfer: 500
Transfer successful.
Current Balance: 5000.0

ATM Menu
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option: 1
Transaction History:
Deposit : 2000.0
Withdraw : 1500.0
Transfer to user2 : 500.0

ATM Menu
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option: 5
Exiting...
Thank you for using the ATM. Goodbye!
*/

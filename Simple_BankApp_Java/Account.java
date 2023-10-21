import java.util.Scanner;

public class Account {
    // class variables
    int balance;
    int prevTransaction;
    String customerName;
    String customerId;

    // class constructor
    Account(String cname, String cid) {
        customerName = cname;
        customerId = cid;
    }

    // function for depositing money
    void deposit(int amount) {
        if (amount != 0) {
            balance = balance + amount;
            prevTransaction = amount;
        }
    }

    // function fro withdrawing money
    void withdraw(int amount) {
        if (amount != 0) {
            balance = balance - amount;
            prevTransaction = -amount;
        }
    }

    // function showing the previous transaction
    void getPreviousTransaction() {
        if (prevTransaction > 0) {
            System.out.println("Deposited: " + prevTransaction);
        } else if (prevTransaction < 0) {
            System.out.println("Withdrawn: " + Math.abs(prevTransaction));
        } else {
            System.out.println("No transaction occurred");
        }
    }

    // function calculating the interest of current funds after a number of years
    void calculateInterest(int years) {
        double interestRate = 0.0185;
        double newBalance = (balance * interestRate * years) + balance;
        System.out.println("The current interest rate is " + (100 * interestRate + "%"));
        System.out.println("After " + years + " years, your balance will be: " + newBalance);
    }

    // function showing the main menu
    void showMenu() {
        char option = '\0';
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + customerId);
        System.out.println("Your ID is : " + customerId);
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println();
        System.out.println("A. Check your balance");
        System.out.println("B. Make a deposit");
        System.out.println("C. Make a withdrawal");
        System.out.println("D, View previous transaction");
        System.out.println("E. Calculate interest");
        System.out.println("F. Exit");

        do {
            System.out.println();
            System.out.println("Enter an option: ");
            char option1 = scanner.next().charAt(0);
            option = Character.toUpperCase(option1);
            System.out.println();

            switch (option) {
                case 'A':
                    System.out.println();
                    System.out.println("Balance = Rs." + balance);
                    System.out.println();
                    break;

                case 'B':
                    System.out.println("Enter an amount to deposit: ");
                    int amount = scanner.nextInt();
                    deposit(amount);
                    System.out.println();
                    break;

                case 'C':
                    System.out.println("Enter an amount to withdraw: ");
                    int amount2 = scanner.nextInt();
                    withdraw(amount2);
                    System.out.println();
                    break;

                case 'D':
                    getPreviousTransaction();
                    System.out.println();
                    break;

                case 'E':
                    System.out.println("Enter how many years of accured interest: ");
                    int years = scanner.nextInt();
                    calculateInterest(years);
                    System.out.println();
                    break;

                case 'F':
                    System.out.println();
                    break;

                default:
                    System.out.println("Error: invalid option. Please enter A, B, C, D or E to access services");
                    break;
            }
        } while (option != 'F');
        System.out.println("Thank you for banking with us!");

        scanner.close();
    }

}
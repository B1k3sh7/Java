import java.util.*;

public class Bank {
    // all data of the customers
    private ArrayList<String> AccNo = new ArrayList<String>();
    private ArrayList<String> Nagarikta = new ArrayList<String>();
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> address = new ArrayList<String>();
    private ArrayList<Integer> pin = new ArrayList<Integer>();
    private ArrayList<Long> balance = new ArrayList<Long>();

    // methods to get the index in the list
    public int getIndex(String accno) {
        int index = -1, i = 0;
        while (i < AccNo.size()) {
            if (accno.equals(AccNo.get(i))) {
                index = i;
            }
            i++;
        }
        return index;
    }

    // getters for the list
    int get_size() {
        return this.AccNo.size();
    }

    String getName(int indexnum) {
        String result = this.name.get(indexnum);
        return result;
    }

    String getAddress(int indexnum) {
        String result = this.address.get(indexnum);
        return result;
    }

    String getNagarikta(int indexnum) {
        String result = this.Nagarikta.get(indexnum);
        return result;
    }

    // updatng address
    public void update_Address(String accno, String new_Address) {
        int index = getIndex(accno);
        if (index != -1) {
            address.set(index, new_Address); // updates the address
        } else {
            System.out.println("No user data available");
        }
    }

    // updating pin
    public void update_pin(String accno, int new_pin) {
        int index = getIndex(accno);
        if (index != -1) {
            pin.set(index, new_pin); // updates the address
        } else {
            System.out.println("No user data available");
        }
    }

    // checking the array what is inside it after giving input
    public void showArray() {
        String arrindex = AccNo.get(0);
        System.out.println(AccNo + " " + arrindex);
    }

    // verifying the details accno and pin
    public boolean verify(String accno, int pin) {
        // getting the index
        boolean result = false;
        int index = getIndex(accno);
        System.out.println("From verify: " + index);
        if (index != -1 && this.pin.get(index) == pin) {
            result = true;
        }
        return result;
    }

    // checking the user have the same details when searching
    boolean check_existing_user(String Nagarikta) {
        boolean result = false;
        for (int i = 0; i < this.Nagarikta.size(); i++) {
            if (this.Nagarikta.get(i) == Nagarikta) {
                result = true;
                break;
            }
        }
        return result;
    }

    String accountNo_generator() {
        String bank = "NepaBankX";
        int size = AccNo.size();
        String accno = bank + size;
        return accno;
    }

    // for new users
    public void add_list(String name, String address, String Nagarikta, int pin) {
        this.name.add(name);
        this.address.add(address);
        this.Nagarikta.add(Nagarikta);
        this.balance.add(0L);
        this.pin.add(pin);
        this.AccNo.add(accountNo_generator());
        System.out.println("Your account number is: " + AccNo.get(AccNo.size() - 1) + ", current balance is 0");
        showArray();
    }

    long check_balance(String accno, int pin) {
        long balance = -1;
        int index = getIndex(accno);
        balance = this.balance.get(index);
        return balance;
    }

    void withdrawAmount(String accno, int pin, long withdraw_amt) {
        int index = getIndex(accno);
        if (this.balance.get(index) >= withdraw_amt) {
            long diff = this.balance.get(index) - withdraw_amt;
            this.balance.set(index, diff); // changed the balance after the wthdraw
            System.out.println("The balance after withdraw is " + this.balance.get(index));
        }
    }

    void depositAmount(String accno, int pin, long deposit_amt) {
        int index = getIndex(accno);
        long add_amt = this.balance.get(index) + deposit_amt;
        this.balance.set(index, add_amt); // changed the balance after deposit
        System.out.println("The balance after deposit is " + this.balance.get(index));
    }

    public void hasAccount() {
        if (get_size() > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter the Account No: ");
            String accno = scanner.nextLine();
            System.out.println("\nEnter the pin: ");
            int pin = scanner.nextInt();
            // verification of accno and pin
            if (verify(accno, pin)) {
                System.out.println(
                        "Press\n1-Check balance\n2-Withdraw\n3-Deposit\n4-Change credentials\n5-Create another account");
                System.out.println("\nEnter the option: ");
                int op = scanner.nextInt();

                switch (op) {

                    case 1:
                        if (check_balance(accno, pin) != -1)
                            System.out.println("Balance: " + check_balance(accno, pin));
                        break;

                    case 2:
                        System.out.println("\nEnter the amount: ");
                        long withdraw = scanner.nextInt();
                        withdrawAmount(accno, pin, withdraw);
                        break;

                    case 3:
                        System.out.println("\nEnter the amount: ");
                        long deposit = scanner.nextInt();
                        depositAmount(accno, pin, deposit);
                        break;

                    case 4:
                        System.out.println("Press\n1-Change pin\n2-Change address");
                        int a = scanner.nextInt();
                        switch (a) {
                            case 1:
                                System.out.println("Enter the new pin(four digit): ");
                                int new_pin = scanner.nextInt();
                                update_pin(accno, new_pin);
                                break;

                            case 2:
                                System.out.println("Enter the new address: ");
                                String new_address = scanner.nextLine();
                                update_Address(accno, new_address);
                                break;

                        }
                        break;
                    case 5:
                        System.out.println("Enter the four digit pin for new account: : ");
                        int new_acc_pin = scanner.nextInt();
                        int index = getIndex(accno);
                        add_list(getName(index),
                                getAddress(index), getNagarikta(index), new_acc_pin);

                    default:
                        System.out.println("Incorrect option");
                        break;
                }

            } else
                System.out.println("Incorrect Account number or pin!");
        } else
            System.out.println("No existing user data!");
    }

    public void createNewAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter your name as per Nagarikta card");
        String name = scanner.nextLine();
        System.out.println("\nEnter the address: ");
        String address = scanner.nextLine();
        System.out.println("\nEnter the Nagarikta no: ");
        String nagariktaNum = scanner.nextLine();
        // checking whether existing user have same credentials as new user
        if (!check_existing_user(nagariktaNum)) {
            System.out.println("\nEnter a four digit pin for the account: ");
            int new_cust_pin = scanner.nextInt();
            add_list(name, address, nagariktaNum, new_cust_pin);
        } else
            System.out.println("Already a existing user");

    }

    public static void main(String[] args) {
        Bank a = new Bank();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to NepaBank Services!\n");

            System.out.println("Press\n1-If you have an account already\n2-Create a new account\n3-Exit");
            System.out.println("\nEnter the option: ");
            // for choosing the option
            int counter = 0;
            counter = scanner.nextInt();

            switch (counter) {
                case 1:
                    a.hasAccount();
                    break;

                case 2:
                    a.createNewAccount();
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
            System.out.println("Thank you!");

        }
    }

}

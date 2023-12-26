import java.util.Scanner;

public class ATM {
    private static String[] accountNumbers = { "12345", "98765", "11223" ,"56898","78945","74185","963852"};
    private static String[] accountPins = { "1111", "2222", "3333","4444","5555","6666","7777" };
    private static double[] accountBalances = { 10000.0, 20000.0, 15000.0 , 25000.0 , 40000.0 , 35000.0 , 30000.0  };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.print("Enter your account number (or 'exit' to quit): ");
            String accountNumber = scanner.nextLine();

            if (accountNumber.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            }

            int accountIndex = findAccountIndex(accountNumber);

            if (accountIndex == -1) {
                System.out.println("Invalid account number. Please try again.");
                continue;
            }

            System.out.print("Enter your ATM PIN: ");
            String pin = scanner.nextLine();

            if (!verifyPin(accountIndex, pin)) {
                System.out.println("Invalid PIN. Please try again.");
                continue;
            }

            while (true) {
                System.out.println("Please select an option:");
                System.out.println("1. Cash Withdraw");
                System.out.println("2. Balance Enquiry");
                System.out.println("3. Fund Transfer");
                System.out.println("4. Exit");

                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Enter the amount to withdraw: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        withdraw(accountIndex, amount);
                        break;
                    case 2:
                        double balance = getBalance(accountIndex);
                        System.out.println("Your balance is: " + balance);
                        break;
                    case 3:
                        System.out.print("Enter the account number to transfer funds: ");
                        String targetAccountNumber = scanner.nextLine();
                        int targetAccountIndex = findAccountIndex(targetAccountNumber);

                        if (targetAccountIndex == -1) {
                            System.out.println("Invalid target account number. Please try again.");
                            continue;
                        }

                        System.out.print("Enter the amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine();
                        transfer(accountIndex, targetAccountIndex, transferAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }

        scanner.close();
    }

    private static int findAccountIndex(String accountNumber) {
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i].equals(accountNumber)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean verifyPin(int accountIndex, String pin) {
        return accountPins[accountIndex].equals(pin);
    }

    private static void withdraw(int accountIndex, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else if (accountBalances[accountIndex] < amount) {
            System.out.println("Insufficient balance.");
        } else {
            accountBalances[accountIndex] -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + accountBalances[accountIndex]);
        }
    }

    private static double getBalance(int accountIndex) {
        return accountBalances[accountIndex];
    }

    private static void transfer(int sourceAccountIndex, int targetAccountIndex, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else if (accountBalances[sourceAccountIndex] < amount) {
            System.out.println("Insufficient balance.");
        } else {
            accountBalances[sourceAccountIndex] -= amount;
            accountBalances[targetAccountIndex] += amount;
            System.out.println("Funds transfer successful.");
            System.out.println("Your balance after transfer : " + accountBalances[sourceAccountIndex]);
			System.out.println("Recivers Account Balance after transfer :"+ accountBalances[targetAccountIndex]);
        }
    }
}

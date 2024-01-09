import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Terminal terminal = new TerminalImpl("0000");
        MessageHandler messageHandler = new ConsoleMessageHandler();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your 4-digit PIN, one digit at a time:");
        boolean pinFail = true;
        while (pinFail) {
            try {
                String pin = "";
                int i = 0;
                while (i < 4) {
                    System.out.print("Enter digit " + (i + 1) + ": ");
                    String input = scanner.next();
                    if (input.matches("\\d") && input.length() == 1) {
                        pin += input;
                        i++;
                    } else {
                        System.out.println("Invalid input. Please enter a single digit.");
                    }
                }
                terminal.enterPin(pin);
                pinFail = false;
                System.out.println("PIN is correct.");
            } catch (InvalidPinException | AccountIsLockedException e) {
                messageHandler.printInfo(e.getMessage());
            }
        }


        while (true) {
            try {
                System.out.println("Select an option:");
                System.out.println("1 - Get Balance");
                System.out.println("2 - Withdraw");
                System.out.println("3 - Deposit");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: // Get balance
                        double balance = terminal.checkBalance();
                        System.out.println("Your balance is: " + balance);
                        break;
                    case 2: // Withdraw
                        System.out.println("Enter withdraw amount: ");
                        int withdrawAmount = scanner.nextInt();
                        terminal.withdraw(withdrawAmount);
                        break;
                    case 3: // Deposit
                        System.out.println("Enter deposit amount: ");
                        int depositAmount = scanner.nextInt();
                        terminal.deposit(depositAmount);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }  catch (InputMismatchException e) {
                messageHandler.printInfo("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (InsufficientFundsException | IllegalArgumentException e) {
                messageHandler.printInfo(e.getMessage());
            }
        }
    }
}
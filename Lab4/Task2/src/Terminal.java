public interface Terminal {
    double checkBalance();
    void deposit(int amount) throws IllegalArgumentException ;
    void withdraw(int amount) throws InsufficientFundsException;
    void enterPin(String pin) throws AccountIsLockedException, InvalidPinException;
}

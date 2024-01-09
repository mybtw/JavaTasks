public class TerminalImpl implements Terminal{
    private final TerminalServer server = new TerminalServer();
    private final PinValidator pinValidator;

    public TerminalImpl(String correctPin) {
        this.pinValidator = new PinValidator(correctPin);
    }

    @Override
    public double checkBalance() {
        return server.checkBalance();
    }

    @Override
    public void deposit(int amount) throws IllegalArgumentException {
        server.deposit(amount);
    }

    @Override
    public void withdraw(int amount) throws InsufficientFundsException {
        server.withdraw(amount);
    }

    @Override
    public void enterPin(String pin) throws AccountIsLockedException, InvalidPinException {
        pinValidator.validatePin(pin);
    }

}

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PinValidator {
    private final String correctPin;
    private int failedAttempts = 0;
    private static final int MAX_ATTEMPTS = 3;

    private static final long LOCK_TIME = 10; // lock time in seconds
    private long lockTime = 0; // time when the account was locked
    private boolean isLocked = false;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    public PinValidator(String correctPin) {
        this.correctPin = correctPin;
    }

    public void validatePin(String inputPin) throws AccountIsLockedException, InvalidPinException {
        if (isLocked) {
            long timeRemaining = getTimeRemaining();
            throw new AccountIsLockedException("Account is locked. Try again in " + timeRemaining + " seconds");
        }
        if (inputPin.equals(correctPin)) {
            resetFailedAttempts();
        } else {
            failedAttempts++;
            if (failedAttempts >= MAX_ATTEMPTS) {
                lockAccount();
                throw new AccountIsLockedException("Account is locked for " + LOCK_TIME + " seconds. Please wait.");
            }
            throw new InvalidPinException("Invalid PIN. Please try again.");
        }
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void lockAccount() {
        isLocked = true;
        lockTime = System.currentTimeMillis();
        scheduler.schedule(this::unlockAccount, LOCK_TIME, TimeUnit.SECONDS);
    }

    private void unlockAccount() {
        isLocked = false;
        lockTime = 0;
        failedAttempts = 0;
    }

    private long getTimeRemaining() {
        if (isLocked) {
            long elapsedTime = System.currentTimeMillis() - lockTime;
            long timeRemaining = TimeUnit.SECONDS.convert(LOCK_TIME * 1000 - elapsedTime, TimeUnit.MILLISECONDS);
            return Math.max(timeRemaining, 0);
        }
        return 0;
    }
}

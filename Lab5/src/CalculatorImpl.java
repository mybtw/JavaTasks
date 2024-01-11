public class CalculatorImpl implements Calculator {

    public static final String MONDAY = "MONDAYY";

    int someValueForTest;

    public int getSomeValueForTest() {
        return someValueForTest;
    }

    public void setSomeValueForTest(int someValueForTest) {
        this.someValueForTest = someValueForTest;
    }

    @Override
    public int calc(int number) {
        int res = 1;
        for (int i = 2; i <= number; i++) {
            res *= i;
        }
        return res;
    }
}

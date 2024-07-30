public class FinancialForecasting {
    public static double calculate(double initialValue, double growthRate, int periods) {
        // Base case
        if (periods == 0) {
            return initialValue;
        }
        // Recursive case
        return calculate(initialValue, growthRate, periods - 1) * (1 + growthRate);
    }
}

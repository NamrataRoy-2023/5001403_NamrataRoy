public class TestingFinancialForecasting {
    public static void main(String[] args) {
        double initialValue = 1980.0;
        double growthRate = 0.08;
        int periods = 10; 

        double futureValue = FinancialForecasting.calculate(initialValue, growthRate, periods);
        System.out.println("Future Value: " + futureValue);
    }
}

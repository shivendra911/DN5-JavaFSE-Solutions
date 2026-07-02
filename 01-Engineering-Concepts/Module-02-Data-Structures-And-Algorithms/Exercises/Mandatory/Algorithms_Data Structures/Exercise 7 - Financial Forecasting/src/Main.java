// Financial Forecasting Tool
// Using recursion to predict future values based on past growth rate

public class Main {

    // Recursive method to calculate future value
    // Formula: futureValue = presentValue * (1 + growthRate) ^ years
    // Each recursive call handles one year
    public static double calculateFutureValue(double presentValue, double growthRate, int years) {

        // base case - no more years to calculate
        if (years == 0) {
            return presentValue;
        }

        // recursive case - grow by one year and call again
        double valueAfterOneYear = presentValue * (1 + growthRate);
        return calculateFutureValue(valueAfterOneYear, growthRate, years - 1);
    }

    public static void main(String[] args) {

        double initialAmount = 10000.0;  // starting investment
        double growthRate = 0.08;        // 8% annual growth
        int years = 5;

        System.out.println("=== Financial Forecasting Tool ===");
        System.out.println("Initial Investment: Rs. " + initialAmount);
        System.out.println("Annual Growth Rate: " + (growthRate * 100) + "%");
        System.out.println("Years: " + years);
        System.out.println();

        // show prediction for each year
        for (int i = 1; i <= years; i++) {
            double futureVal = calculateFutureValue(initialAmount, growthRate, i);
            System.out.printf("After %d year(s): Rs. %.2f%n", i, futureVal);
        }

        System.out.println("\n=== Analysis ===");
        System.out.println("Time Complexity: O(n) where n is number of years");
        System.out.println("Each recursive call reduces years by 1 until it reaches 0");
        System.out.println("To optimize: we can use memoization or just use a loop instead of recursion");
    }
}

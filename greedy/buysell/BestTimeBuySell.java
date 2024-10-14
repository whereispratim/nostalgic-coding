package buysell;

class BestTimeBuySell {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;  // Start with a very high value for the minimum price
        int maxProfit = 0;  // Start with a zero profit

        // Traverse the array of prices
        for (int price : prices) {
            // Update the minimum price if we find a new lower price
            if (price < minPrice) {
                minPrice = price;
            }
            // Calculate the profit if we sold at the current price
            else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }

        return maxProfit;
    }

    // Sample usage
    public static void main(String[] args) {
        BestTimeBuySell solution = new BestTimeBuySell();

        // Test case 1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfit(prices1));  // Output: 5

        // Test case 2
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println(solution.maxProfit(prices2));  // Output: 0
    }
}



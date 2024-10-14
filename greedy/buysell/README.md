## Best Time to Buy and Sell Stock

### Problem Statement

You are given an array `prices` where `prices[i]` is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

### Examples

#### Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

#### Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.

### Approach

We'll use a single pass greedy approach:
1. Keep track of the minimum price seen so far.
2. For each price, calculate the potential profit if we sell at that price.
3. Update the maximum profit if the current potential profit is greater.

### Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class BestTimeBuySell:
    def maxProfit(self, prices):
        min_price = float('inf')  # Start with a very high value for the minimum price
        max_profit = 0  # Start with a zero profit

        # Traverse the list of prices
        for price in prices:
            # Update the minimum price if we find a new lower price
            if price < min_price:
                min_price = price
            # Calculate the profit if we sold at the current price
            else:
                max_profit = max(max_profit, price - min_price)

        return max_profit

if __name__ == "__main__":
solution = BestTimeBuySell()

    # Test case 1
    prices1 = [7, 1, 5, 3, 6, 4]
    print(solution.maxProfit(prices1))  # Output: 5

    # Test case 2
    prices2 = [7, 6, 4, 3, 1]
    print(solution.maxProfit(prices2))  # Output: 0
</code></pre>
</td>
<td>
<pre><code class="java">

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

</code></pre>
</td>
</tr>
</table>

### Explanation

1. We initialize `min_price` to positive infinity (or `Integer.MAX_VALUE` in Java) and `max_profit` to 0.
2. We iterate through the prices:
    - If the current price is less than `min_price`, we update `min_price`.
    - Otherwise, we calculate the potential profit (current price - min_price) and update `max_profit` if this potential profit is greater.
3. After iterating through all prices, we return `max_profit`.

### Detailed Walkthrough

Let's walk through the solution for prices = [7,1,5,3,6,4]:

1. Initialize: `min_price = infinity`, `max_profit = 0`
2. price = 7: Update `min_price` to 7
3. price = 1: Update `min_price` to 1
4. price = 5: Potential profit = 5 - 1 = 4, update `max_profit` to 4
5. price = 3: No update (3 - 1 = 2, which is less than current max_profit)
6. price = 6: Potential profit = 6 - 1 = 5, update `max_profit` to 5
7. price = 4: No update (4 - 1 = 3, which is less than current max_profit)
8. Return `max_profit` = 5


#### Initial State:
- `min_price = infinity`
- `max_profit = 0`

#### Iteration over prices:

1. **Price 7:**
    - `min_price = min(infinity, 7) = 7`
    - No profit yet, as we haven't seen any lower price to buy.

2. **Price 1:**
    - `min_price = min(7, 1) = 1` (Update the minimum price)
    - No profit yet, as selling at 1 yields no gain.

3. **Price 5:**
    - Profit if sold at 5 = 5 - 1 = 4
    - `max_profit = max(0, 4) = 4`

4. **Price 3:**
    - Profit if sold at 3 = 3 - 1 = 2 (less than 4, so no update)

5. **Price 6:**
    - Profit if sold at 6 = 6 - 1 = 5
    - `max_profit = max(4, 5) = 5` (Update the max profit)

6. **Price 4:**
    - Profit if sold at 4 = 4 - 1 = 3 (less than 5, so no update)

#### Final Answer:
Maximum profit is 5.

### Second Test Case

For the input prices = [7,6,4,3,1]:

1. The prices are continuously decreasing.
2. At each step, the minimum price is updated, but no profit can be made.
3. The `max_profit` remains 0 throughout the iteration.

Final Answer: 0 (no profit can be made)

### Key Observations:
1. We only need to make one pass through the array.
2. We're always buying at the lowest price seen so far and calculating the potential profit if we sell at the current price.
3. We update the maximum profit if the current potential profit is greater than the max profit seen so far.
4. This approach ensures we find the maximum profit possible with a single buy and sell transaction.

### Complexity Analysis

- Time Complexity: O(n), where n is the number of prices. We make a single pass through the array.
- Space Complexity: O(1). We only use a constant amount of extra space.

### LeetCode Link

[121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)
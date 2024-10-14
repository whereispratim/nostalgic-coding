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

# Sample usage
if __name__ == "__main__":
    solution = BestTimeBuySell()

    # Test case 1
    prices1 = [7, 1, 5, 3, 6, 4]
    print(solution.maxProfit(prices1))  # Output: 5

    # Test case 2
    prices2 = [7, 6, 4, 3, 1]
    print(solution.maxProfit(prices2))  # Output: 0

def climbStairs(n: int) -> int:
    if n == 1:
        return 1  # Base case: If there's only 1 step

    # Create a DP array to store the number of ways to reach each step
    dp = [0] * (n + 1)  # Initialize the dp array with 0s

    # Base cases
    dp[1] = 1  # 1 way to reach step 1 (just 1 step)
    dp[2] = 2  # 2 ways to reach step 2 (1+1 or 2)

    # Fill the dp array from step 3 to step n
    for i in range(3, n + 1):
        dp[i] = dp[i - 1] + dp[i - 2]  # Recurrence relation

    return dp[n]  # The answer is the number of ways to reach step n
# Test case example
n = 5
print(climbStairs(n))  # Output: 8

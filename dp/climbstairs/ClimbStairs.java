package climbstairs;

public class ClimbStairs {
    public int climbStairs(int n) {
        if (n == 1) return 1;  // Base case: If there's only 1 step

        // Create a DP array to store the number of ways to reach each step
        int[] dp = new int[n + 1];  // n + 1 because we are considering steps from 0 to n

        // Base cases
        dp[1] = 1;  // 1 way to reach step 1 (just 1 step)
        dp[2] = 2;  // 2 ways to reach step 2 (1+1 or 2)

        // Fill the dp array from step 3 to step n
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];  // Recurrence relation
        }

        return dp[n];  // The answer is the number of ways to reach step n
    }

    // Test case example
    public static void main(String[] args) {
        ClimbStairs solution = new ClimbStairs();
        int n = 5;
        System.out.println(solution.climbStairs(n));  // Output: 8
    }
}


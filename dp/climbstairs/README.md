## Climbing Stairs

### Problem Statement

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

### Examples

#### Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

#### Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

### Approach

We'll use a dynamic programming approach:
1. Realize that the number of ways to reach the nth step is the sum of the number of ways to reach the (n-1)th step and the (n-2)th step.
2. This is because we can reach the nth step either by taking a single step from the (n-1)th step or by taking a 2-step from the (n-2)th step.
3. We can use this to build our solution from the bottom up.

This problem is a variation of the Fibonacci sequence. For any step n, the number of distinct ways to reach it is the sum of the ways to reach n-1 and n-2, because at each step you can either take 1 step or 2 steps.

```
ways(n) = ways(n-1) + ways(n-2)
```
### Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class ClimbStairs:
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n

        dp = [0] * (n + 1)
        dp[1] = 1
        dp[2] = 2
        
        for i in range(3, n + 1):
            dp[i] = dp[i-1] + dp[i-2]
        
        return dp[n]

solution = ClimbStairs()
print(solution.climbStairs(2))  # Output: 2
print(solution.climbStairs(3))  # Output: 3
</code></pre>
</td>
<td>
<pre><code class="java">

public class ClimbStairs {
public int climbStairs(int n) {
// Base cases
if (n == 1) {
return 1;
}
if (n == 2) {
return 2;
}

        // Start with step 1 and 2
        int prev2 = 1;  // Ways to reach step 1
        int prev1 = 2;  // Ways to reach step 2

        // Iterate from step 3 to n
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;  // Current step is sum of last two steps
            prev2 = prev1;  // Move prev2 to prev1
            prev1 = current;  // Move prev1 to current
        }

        return prev1;  // The result is stored in prev1 for step n
    }

    // Test case example
    public static void main(String[] args) {
        ClimbStairs solution = new ClimbStairs();
        int n = 5;
        System.out.println(solution.climbStairs(n));  // Output: 8
    }
}

</code></pre>
</td>
</tr>
</table>

### Explanation

1. We start by handling the base cases: if n is 1 or 2, we return n directly.
2. We create a DP array to store the number of ways to reach each step.
3. We initialize the first two steps: there's 1 way to reach the first step and 2 ways to reach the second step.
4. For each subsequent step, the number of ways to reach it is the sum of the ways to reach the two previous steps.
5. We fill the DP array from bottom to top.
6. Finally, we return the value for the nth step, which represents the total number of ways to climb the stairs.

### Detailed Walkthrough

Let's walk through the solution for n = 5:

1. Initialize: dp = [0, 1, 2, 0, 0, 0]
2. For i = 3:
    - dp[3] = dp[2] + dp[1] = 2 + 1 = 3
    - dp = [0, 1, 2, 3, 0, 0]
- Explanation: There are three ways to reach the 3rd step:
- 1 step + 1 step + 1 step
- 1 step + 2 steps
- 2 steps + 1 step
3. For i = 4:
    - dp[4] = dp[3] + dp[2] = 3 + 2 = 5
    - dp = [0, 1, 2, 3, 5, 0]
-  Explanation: There are five ways to reach the 4th step:
- 1 step + 1 step + 1 step + 1 step
- 1 step + 1 step + 2 steps
- 1 step + 2 steps + 1 step
- 2 steps + 1 step + 1 step
- 2 steps + 2 steps
4. For i = 5:
    - dp[5] = dp[4] + dp[3] = 5 + 3 = 8
    - dp = [0, 1, 2, 3, 5, 8]
- Explanation: There are eight ways to reach the 5th step:
- 1 step + 1 step + 1 step + 1 step + 1 step
- 1 step + 1 step + 1 step + 2 steps
- 1 step + 1 step + 2 steps + 1 step
- 1 step + 2 steps + 1 step + 1 step
- 2 steps + 1 step + 1 step + 1 step
- 1 step + 2 steps + 2 steps
- 2 steps + 1 step + 2 steps
- 2 steps + 2 steps + 1 step
5. Return dp[5] = 8

So, there are 8 ways to climb 5 stairs.

### Complexity Analysis

- Time Complexity: O(n), where n is the number of stairs. We iterate from 3 to n once.
- Space Complexity: O(n) to store the DP array.

### Key Observations

1. This problem follows the Fibonacci sequence pattern.
2. We can optimize the space complexity to O(1) by only keeping track of the last two values instead of the entire DP array.
3. For very large n, we need to be careful about integer overflow and might need to use a language's built-in big integer type or implement our own.

### LeetCode Link

[70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
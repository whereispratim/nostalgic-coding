# Time to Infect All Plants

## Problem Description

You are given an m x n grid representing an orchard where:
- Each cell represents a plant.
- The value of each cell can be:
    - 1 (infected plant)
    - 0 (healthy plant)
    - -1 (non-plant area)

An infected plant can spread the disease to its neighboring healthy plants (i.e., horizontally and vertically adjacent) in one day. The task is to determine how many days it will take for the disease to spread to every plant, or return -1 if not all healthy plants can be infected.

## Approach to solve problem

1. **Initialize the grid**: Start with a matrix where infected and healthy plants are represented.
2. **Queue setup for BFS**: Use a queue to store the positions of all initially infected plants.
3. **Count healthy plants**: Keep track of the number of healthy plants that need to be infected.
4. **Spread the infection**: For each infected plant, check its neighbors and spread the infection to adjacent plants.
5. **Count the day**s: Track the number of days it takes for the infection to spread across the entire orchard.
6. **Check completion**: Return the number of days if all plants are infected, or -1 if some plants remain uninfected.

## Solution

<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
from collections import deque

class TimeToInfect:
@staticmethod
def infect_orchard(orchard):
rows, cols = len(orchard), len(orchard[0])
infection_queue = deque()
healthy_plants = 0

        # 1. Initialize the queue with all initially infected plants and count the number of healthy plants.
        for row in range(rows):
            for col in range(cols):
                if orchard[row][col] == 1:
                    infection_queue.append((row, col, 0))  # Add infected plant's position and day (0) to the queue
                elif orchard[row][col] == 0:
                    healthy_plants += 1  # Count healthy plants

        # 2. Start the BFS process to infect healthy plants.
        days_to_infect_all = 0
        directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]  # Possible 4 directions to move (down, up, right, left)

        # BFS spread until the queue is empty or all healthy plants are infected
        while infection_queue and healthy_plants > 0:
            current_plant = infection_queue.popleft()  # Process the current infected plant
            current_row, current_col, current_day = current_plant

            # Check all four neighboring cells
            for direction in directions:
                new_row = current_row + direction[0]
                new_col = current_col + direction[1]

                # If the neighboring cell is healthy, infect it
                if 0 <= new_row < rows and 0 <= new_col < cols and orchard[new_row][new_col] == 0:
                    orchard[new_row][new_col] = 1  # Infect the plant
                    healthy_plants -= 1  # Decrease the number of healthy plants
                    infection_queue.append((new_row, new_col, current_day + 1))  # Add newly infected plant to queue
                    days_to_infect_all = current_day + 1  # Update the number of days

        # 3. If there are still healthy plants left, return -1. Otherwise, return the number of days it took to infect all.
        return days_to_infect_all if healthy_plants == 0 else -1


if __name__ == "__main__":
grid = [
[1, 0, 0],
[0, 0, 0],
[0, 0, 0]
]
result = TimeToInfect.infect_orchard(grid)
print("Time to infect all plants:", result, "days")


</code></pre>
</td>

<td>
<pre><code class="java">
public class TimeToInfect {
    public static int infectOrchard(int[][] orchard) {
        int rows = orchard.length, cols = orchard[0].length;
        Queue<int[]> infectionQueue = new LinkedList<>();
        int healthyPlants = 0;

        //1. Initialize the queue with all initially infected plants and count the number of healthy plants.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (orchard[row][col] == 1) {
                    infectionQueue.offer(new int[]{row, col, 0}); // Add infected plant's position and day (0) to the queue
                } else if (orchard[row][col] == 0) {
                    healthyPlants++; // Count healthy plants
                }
            }
        }

        // 2. Start the BFS process to infect healthy plants.
        int daysToInfectAll = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Possible 4 directions to move (down, up, right, left)

        // BFS spread until the queue is empty or all healthy plants are infected
        while (!infectionQueue.isEmpty() && healthyPlants > 0) {
            int[] currentPlant = infectionQueue.poll(); // Process the current infected plant
            int currentRow = currentPlant[0], currentCol = currentPlant[1], currentDay = currentPlant[2];

            // Check all four neighboring cells
            for (int[] direction : directions) {
                int newRow = currentRow + direction[0], newCol = currentCol + direction[1];

                // If the neighboring cell is healthy, infect it
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && orchard[newRow][newCol] == 0) {
                    orchard[newRow][newCol] = 1; // Infect the plant
                    healthyPlants--; // Decrease the number of healthy plants
                    infectionQueue.offer(new int[]{newRow, newCol, currentDay + 1}); // Add newly infected plant to queue
                    daysToInfectAll = currentDay + 1; // Update the number of days
                }
            }
        }

        // 3. If there are still healthy plants left, return -1. Otherwise, return the number of days it took to infect all.
        return healthyPlants == 0 ? daysToInfectAll : -1;
    }


    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int result = infectOrchard(grid);
        System.out.println("Time to infect all plants: " + result + " days");
    }
}
</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a Breadth-First Search (BFS) approach to simulate the spread of infection.

### How It Works (Step-by-Step):

Consider the grid:
```
[
[1, 0, 0],
[0, 0, 0],
[0, 0, 0]
]
```
## Initialization:
- **Queue:** `[(0, 0, 0)]`
- **Healthy Plants:** `8`
- **Day 0:** Infect `(0, 0)`

## Day-by-Day Process:

### Day 0: Process (0, 0)
- **Current:** `(0, 0, 0)`
- **Infect:** `(0, 1)` and `(1, 0)`
- **Queue:** `[(0, 1, 1), (1, 0, 1)]`
- **Healthy Plants:** `6`
- **Grid:** `[[1, 1, 0], [1, 0, 0], [0, 0, 0]]`

### Day 1: Process (0, 1)
- **Current:** `(0, 1, 1)`
- **Infect:** `(0, 2)`
- **Queue:** `[(1, 0, 1), (0, 2, 2)]`
- **Healthy Plants:** `5`
- **Grid:** `[[1, 1, 1], [1, 0, 0], [0, 0, 0]]`

### Day 1: Process (1, 0)
- **Current:** `(1, 0, 1)`
- **Infect:** `(1, 1)`
- **Queue:** `[(0, 2, 2), (1, 1, 2)]`
- **Healthy Plants:** `4`
- **Grid:** `[[1, 1, 1], [1, 1, 0], [0, 0, 0]]`

### Day 2: Process (0, 2)
- **Current:** `(0, 2, 2)`
- **Infect:** None (already infected)
- **Queue:** `[(1, 1, 2)]`
- **Healthy Plants:** `4`
- **Grid:** `[[1, 1, 1], [1, 1, 0], [0, 0, 0]]`

### Day 2: Process (1, 1)
- **Current:** `(1, 1, 2)`
- **Infect:** `(2, 1)` and `(1, 2)`
- **Queue:** `[(2, 1, 3), (1, 2, 3)]`
- **Healthy Plants:** `2`
- **Grid:** `[[1, 1, 1], [1, 1, 1], [0, 0, 0]]`

### Day 3: Process (2, 1)
- **Current:** `(2, 1, 3)`
- **Infect:** `(2, 2)`
- **Queue:** `[(1, 2, 3), (2, 2, 4)]`
- **Healthy Plants:** `1`
- **Grid:** `[[1, 1, 1], [1, 1, 1], [1, 0, 0]]`

### Day 3: Process (1, 2)
- **Current:** `(1, 2, 3)`
- **Infect:** None (already infected)
- **Queue:** `[(2, 2, 4)]`
- **Healthy Plants:** `1`
- **Grid:** `[[1, 1, 1], [1, 1, 1], [1, 0, 0]]`

### Day 4: Process (2, 2)
- **Current:** `(2, 2, 4)`
- **Infect:** None (already infected)
- **Queue:** `[]`
- **Healthy Plants:** `0`
- **Grid:** `[[1, 1, 1], [1, 1, 1], [1, 1, 1]]`
5. All plants infected, return 2 days

The algorithm uses a queue to keep track of infected plants and their infection day. It processes plants in the order they were infected, spreading the infection to adjacent healthy plants. The process continues until all healthy plants are infected or no more plants can be infected.

## Complexity Analysis

- Time Complexity: O(m * n), where m and n are the dimensions of the grid.
- Space Complexity: O(m * n) in the worst case when all plants are initially infected.

## LeetCode Link

[Time to Infect All Oranges](https://leetcode.com/problems/rotting-oranges/description/)
# Number of Islands

## Problem Description
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
## Approach to solve problem
1. Initialization: We check if the grid is empty and initialize the necessary variables.
2. Direction Vectors: We define direction vectors to explore the four possible adjacent cells (up, down, left, right).
3. BFS Traversal: We use BFS to mark all cells of an island starting from a land cell (1).
4. Counting Islands: For every unvisited land cell, we initiate a BFS traversal, incrementing the island count.
5. Output: Finally, we return the count of islands found in the grid.

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

class NoOfIsland:
def numIslands(self, grid):
if not grid or len(grid) == 0:
return 0

        num_islands = 0
        num_rows = len(grid)
        num_cols = len(grid[0])
        
        # Directions for moving up, down, left, right
        directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]
        
        def bfs(start_row, start_col):
            queue = deque([(start_row, start_col)])
            grid[start_row][start_col] = '0'  # Mark as visited

            while queue:
                current_row, current_col = queue.popleft()
                
                for direction in directions:
                    new_row = current_row + direction[0]
                    new_col = current_col + direction[1]

                    # Check if the new position is valid and is land
                    if 0 <= new_row < num_rows and 0 <= new_col < num_cols and grid[new_row][new_col] == '1':
                        grid[new_row][new_col] = '0'  # Mark as visited
                        queue.append((new_row, new_col))

        # Iterate through each cell in the grid
        for row in range(num_rows):
            for col in range(num_cols):
                if grid[row][col] == '1':  # Found an island
                    num_islands += 1
                    bfs(row, col)  # Start BFS to mark the entire island

        return num_islands


if __name__ == "__main__":
grid = [
["1","1","0","0","0"],
["1","1","0","0","0"],
["0","0","1","0","0"],
["0","0","0","1","1"]
]
solution = NoOfIsland()
result = solution.numIslands(grid)
print("Number of islands:", result)


</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.LinkedList;
import java.util.Queue;

public class NoOfIsland {
public int numIslands(char[][] grid) {
if (grid == null || grid.length == 0) {
return 0;
}

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // If we find a '1', we have found an island
                if (grid[row][col] == '1') {
                    numIslands++;
                    // Perform BFS to mark all parts of this island as visited
                    bfs(grid, row, col);
                }
            }
        }

        return numIslands;
    }

    private void bfs(char[][] grid, int startRow, int startCol) {
        // Directions for moving up, down, left, right
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Create a queue for BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});

        // Mark the starting point as visited
        grid[startRow][startCol] = '0'; // Change '1' to '0' to mark it as visited

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];

            // Check all four directions
            for (int[] direction : directions) {
                int newRow = currentRow + direction[0];
                int newCol = currentCol + direction[1];

                // If the new position is valid and is part of the island
                if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == '1') {
                    queue.offer(new int[]{newRow, newCol});
                    grid[newRow][newCol] = '0'; // Mark as visited
                }
            }
        }
    }

    public static void main(String[] args) {
        NoOfIsland solution = new NoOfIsland();
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        char[][] grid1 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int result = solution.numIslands(grid);
        System.out.println("Number of islands for grid: " + result);
        System.out.println("Number of islands for grid1: " + solution.numIslands(grid1));
    }
}

~~</code></pre>
</td>
</tr>
</table>

## Explanation of Each Iteration

The algorithm uses Breadth-First Search (BFS) to explore each island in the grid. Here's a breakdown of how the code processes each iteration:

### Initialization:

- The algorithm starts by checking if the grid is empty. If it is, it returns 0.
- It initializes `numIslands` to count the islands and defines the directions for moving up, down, left, and right.

### Outer Loop:

- The algorithm iterates through each cell in the grid using two nested loops (one for rows and one for columns).
- If it encounters a cell with a value of '1' (indicating land), it increments the `numIslands` counter, as this marks the discovery of a new island.

### BFS Call:

- The BFS method is invoked for each newly found island cell. This method will explore and mark all connected land cells (part of the same island).
- The starting cell (where '1' was found) is added to a queue, and it is marked as visited by changing its value to '0'.

### BFS Exploration:

- The BFS continues until the queue is empty. For each cell processed:
  - It dequeues the current cell.
  - It checks all four possible directions (up, down, left, right).
  - If a neighboring cell is within bounds and is land ('1'), it marks it as visited ('0') and adds it to the queue.

### Completing the Island Search:

- The BFS will continue to explore and mark all cells connected to the starting cell until no more connected land cells are found.
- Once the BFS is complete, control returns to the outer loop to continue searching the grid for other unvisited land cells.

### Result:

- After all cells in the grid have been processed, the total count of islands is returned.
## Complexity Analysis

- Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the grid, because we may visit each cell once.
- Space Complexity: O(m * n) in the worst case if the entire grid is filled with land ('1'). The queue can grow to the size of the entire grid.

[No of Island](https://leetcode.com/problems/number-of-islands/description/)
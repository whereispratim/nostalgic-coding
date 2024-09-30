# Biggest Island

## Problem Description

You are given a grid representing a map of land and water. Each cell in the grid can either be:

1 (land)

0 (water)

Your task is to find the area of the largest island. An island is a group of land cells (1s) connected vertically or horizontally. You must return the size of the biggest island in the grid.

## Approach to solve problem
We can use Depth-First Search (DFS) or Breadth-First Search (BFS) to solve this problem. The goal is to explore each island, mark it as visited, and calculate its size. Then, we track the largest island we find during this exploration.

Steps:
1. Iterate through the grid:

For each cell, if it contains land (1), perform a DFS/BFS to explore the entire island, marking all the connected land cells as visited.
Track the size of each island and update the maximum size.
2. DFS/BFS to Explore Island:

Starting from a cell that is part of an island (1), recursively explore all its neighboring cells (up, down, left, right) if they are also land.
While exploring, count the number of land cells in the island.
3. Edge Case:

If there are no land cells, the biggest island is of size 0.


## Solution

<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class BiggestIsland:
    def maxAreaOfIsland(self, grid):
        # If the grid is empty or None, return 0
        if not grid or not grid[0]:
            return 0

        max_island_area = 0  # Variable to store the maximum area of an island
        num_rows = len(grid)  # Number of rows in the grid
        num_cols = len(grid[0])  # Number of columns in the grid

        # Helper function to explore the island using DFS and calculate its area
        def explore_island(row, col):
            # Check if the current cell is out of bounds or not a land cell (0 or visited)
            if row < 0 or row >= num_rows or col < 0 or col >= num_cols or grid[row][col] == 0:
                return 0

            # Mark the current land cell as visited by setting it to 0
            grid[row][col] = 0

            # Explore all four directions (up, down, left, right) and sum the area
            area = 1  # The current cell counts as 1 in the area
            area += explore_island(row + 1, col)  # Explore down
            area += explore_island(row - 1, col)  # Explore up
            area += explore_island(row, col + 1)  # Explore right
            area += explore_island(row, col - 1)  # Explore left

            return area

        # Loop through each cell in the grid
        for row in range(num_rows):
            for col in range(num_cols):
                # If we find a land cell (1), we perform DFS to find the size of the island
                if grid[row][col] == 1:
                    current_island_area = explore_island(row, col)
                    # Update the maximum island area if the current one is larger
                    max_island_area = max(max_island_area, current_island_area)

        return max_island_area

if __name__ == "__main__":
grid = [
[1, 1, 0, 0, 0],
[1, 1, 0, 1, 1],
[0, 0, 0, 1, 1],
[0, 1, 1, 1, 0]
]

    solution = BiggestIsland()
    max_area = solution.maxAreaOfIsland(grid)
    print("Max area of island:", max_area)

</code></pre>
</td>
<td>
<pre><code class="java">
public class BiggestIsland {

    public int findBiggestIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols]; // Track visited cells
        int maxIslandSize = 0; // Track the largest island size

        // Iterate through each cell in the grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // If the cell is land and not visited, explore the island
                if (grid[row][col] == 1 && !visited[row][col]) {
                    int currentIslandSize = exploreIsland(grid, visited, row, col);
                    // Update the maximum island size found
                    maxIslandSize = Math.max(maxIslandSize, currentIslandSize);
                }
            }
        }

        return maxIslandSize; // Return the size of the largest island
    }

    // DFS method to explore the island
    private int exploreIsland(int[][] grid, boolean[][] visited, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Base condition: If the cell is out of bounds or water or already visited, stop.
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 0 || visited[row][col]) {
            return 0;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        // Initialize island size as 1 (count the current land cell)
        int islandSize = 1;

        // Explore all four possible directions (up, down, left, right)
        islandSize += exploreIsland(grid, visited, row + 1, col); // down
        islandSize += exploreIsland(grid, visited, row - 1, col); // up
        islandSize += exploreIsland(grid, visited, row, col + 1); // right
        islandSize += exploreIsland(grid, visited, row, col - 1); // left

        return islandSize; // Return the total size of the island
    }

    public static void main(String[] args) {
        BiggestIsland bi = new BiggestIsland();
        int[][] grid = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 1, 1},
            {0, 1, 1, 1, 0}
        };
        int result = bi.findBiggestIsland(grid);
        System.out.println("The size of the biggest island is: " + result);
    }
}

</code></pre>
</td>
</tr>
</table>


### How It Works: Step-by-Step Description of Each Iteration
Initialization:

We create a visited array to keep track of which cells have been explored.
We initialize maxIslandSize to 0, which will store the size of the largest island.
Outer Loop (Iterating Over the Grid):

The program iterates through each cell of the grid.
If the current cell contains land (1) and hasn't been visited, we trigger the exploreIsland() method to calculate the size of the current island.
Exploring an Island (DFS):

Starting from a land cell, the exploreIsland() function is called recursively in four directions: up, down, left, and right.
The function continues exploring as long as it finds unvisited land cells connected to the current cell.
Each time a new land cell is found, it is marked as visited, and the island size is increased by 1.

Consider the grid: 
```
[
[1, 1, 0, 0, 0],
[1, 1, 0, 1, 1],
[0, 0, 0, 1, 1],
[0, 0, 0, 1, 1]
]
```
Iteration 1:
- The loop starts at (0,0) (land cell).
- exploreIsland(0,0) is called:
   - It marks (0,0) as visited and starts exploring.
   - The neighboring cell (1,0) is land and unvisited, so exploreIsland(1,0) is called.
   - The next cell (1,1) is land, so the process continues, marking cells and counting the island size.
- Total island size is 4 (the island is formed by the cells (0,0), (0,1), (1,0), and (1,1)).

Iteration 2:
- The loop reaches (0,1), but since it's already visited, it is skipped.

Iteration 3:
- The loop reaches (1,3) (land cell), starting a new island.
- exploreIsland(1,3) starts exploring the new island:
   - The size of this island is 6 (cells (1,3), (1,4), (2,3), (2,4), (3,3), and (3,4)).

Iteration 4:
- Continue checking the remaining cells. All other land cells are part of the already visited islands.

Final Output:
- The largest island found has a size of 6, so the method returns 6.

## Complexity Analysis

- Time Complexity: O(m * n), where m is the number of rows and n is the number of columns. Each cell is visited once during the DFS.
- Space Complexity: O(m * n) in the worst case, due to the recursion stack if all cells are part of a single island.## LeetCode Link

[Max Area of Island](https://leetcode.com/problems/max-area-of-island/description/)
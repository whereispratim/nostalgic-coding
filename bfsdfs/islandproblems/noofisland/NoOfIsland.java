package islandproblems.noofisland;
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

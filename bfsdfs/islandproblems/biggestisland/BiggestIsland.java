package islandproblems.biggestisland;

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


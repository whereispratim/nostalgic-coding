package spreadingproblems.rottingoranges;

import java.util.*;
public class RottingOranges {
    public static int infectOrange(int[][] oranges) {
        int rows = oranges.length, cols = oranges[0].length;
        Queue<int[]> infectionQueue = new LinkedList<>();
        int healthyOranges = 0;

        // 1. Initialize the queue with all initially infected oranges and count the number of healthy oranges.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (oranges[row][col] == 2) {
                    infectionQueue.offer(new int[]{row, col, 0}); // Add infected orange's position and day (0) to the queue
                } else if (oranges[row][col] == 1) {
                    healthyOranges++; // Count healthy oranges
                }
            }
        }

        for (int[] position : infectionQueue) {
            System.out.println("[" + position[0] + ", " + position[1] +  ", " + position[2] + "]");
        }

        // 2. Start the BFS process to infect healthy plants.
        int daysToInfectAll = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Possible 4 directions to move (down, up, right, left)

        // BFS spread until the queue is empty or all healthy oranges are infected
        while (!infectionQueue.isEmpty() && healthyOranges > 0) {
            int[] currentOrange = infectionQueue.poll(); // Process the current infected orange
            int currentRow = currentOrange[0], currentCol = currentOrange[1], currentDay = currentOrange[2];

            // Check all four neighboring cells
            for (int[] direction : directions) {
                int newRow = currentRow + direction[0], newCol = currentCol + direction[1];

                // If the neighboring cell is healthy, infect it
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && oranges[newRow][newCol] == 1) {
                    oranges[newRow][newCol] = 2; // Infect the orange
                    healthyOranges--; // Decrease the number of healthy oranges
                    infectionQueue.offer(new int[]{newRow, newCol, currentDay + 1}); // Add newly infected plant to queue
                    daysToInfectAll = currentDay + 1; // Update the number of days
                }
            }
        }

        // 3. If there are still healthy orange left, return -1. Otherwise, return the number of days it took to infect all.
        return healthyOranges == 0 ? daysToInfectAll : -1;
    }


    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int result = infectOrange(grid);
        System.out.println("Time to infect all oranges: " + result + " minutes"); // 4 minute
    }
}


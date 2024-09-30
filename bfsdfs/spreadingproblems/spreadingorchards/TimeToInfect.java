package spreadingproblems.spreadingorchards;

import java.util.*;

public class TimeToInfect {
    public static int infectOrchard(int[][] orchard) {
        int rows = orchard.length, cols = orchard[0].length;
        Queue<int[]> infectionQueue = new LinkedList<>();
        int healthyPlants = 0;

        // 1. Initialize the queue with all initially infected plants and count the number of healthy plants.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (orchard[row][col] == 1) {
                    infectionQueue.offer(new int[]{row, col, 0}); // Add infected plant's position and day (0) to the queue
                } else if (orchard[row][col] == 0) {
                    healthyPlants++; // Count healthy plants
                }
            }
        }

        for (int[] position : infectionQueue) {
            System.out.println("[" + position[0] + ", " + position[1] +  ", " + position[2] + "]");
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
        System.out.println("Time to infect all plants: " + result + " days"); // 4 days
    }
}

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

# Example grid to test the code (your provided grid)
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

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


# Example usage
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

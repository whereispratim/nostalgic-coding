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

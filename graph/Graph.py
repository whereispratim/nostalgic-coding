from collections import deque

class Graph:
    def __init__(self, number_of_vertices):
        self.number_of_vertices = number_of_vertices  # Total number of vertices in the graph
        self.adjacency_list = [[] for _ in range(number_of_vertices)]  # Adjacency list to store graph edges

    # Method to add a directed edge from 'source_vertex' to 'destination_vertex'
    def add_edge(self, source_vertex, destination_vertex):
        self.adjacency_list[source_vertex].append(destination_vertex)

    # Depth First Search (DFS) starting from a given vertex
    def depth_first_search(self, start_vertex):
        visited = [False] * self.number_of_vertices  # Array to track visited vertices
        print(f"DFS starting from vertex {start_vertex}:")
        self._dfs_util(start_vertex, visited)  # Helper method for DFS
        print()  # New line after traversal

    # Recursive helper method for DFS traversal
    def _dfs_util(self, current_vertex, visited):
        # Mark the current vertex as visited
        visited[current_vertex] = True
        print(current_vertex, end=" ")

        # Recur for all adjacent vertices
        for adjacent_vertex in self.adjacency_list[current_vertex]:
            if not visited[adjacent_vertex]:
                self._dfs_util(adjacent_vertex, visited)

    # Breadth First Search (BFS) starting from a given vertex
    def breadth_first_search(self, start_vertex):
        visited = [False] * self.number_of_vertices  # Array to track visited vertices
        queue = deque()  # Queue to manage BFS

        # Mark the start vertex as visited and enqueue it
        visited[start_vertex] = True
        queue.append(start_vertex)

        print(f"BFS starting from vertex {start_vertex}:")

        while queue:
            current_vertex = queue.popleft()  # Dequeue the next vertex
            print(current_vertex, end=" ")

            # Enqueue all unvisited adjacent vertices
            for adjacent_vertex in self.adjacency_list[current_vertex]:
                if not visited[adjacent_vertex]:
                    visited[adjacent_vertex] = True
                    queue.append(adjacent_vertex)

        print()  # New line after traversal

# Create the graph and test DFS and BFS
if __name__ == "__main__":
    graph = Graph(6)  # Create a graph with 6 vertices (0 to 5)

    # Adding edges to the graph
    graph.add_edge(0, 1)
    graph.add_edge(0, 3)
    graph.add_edge(1, 2)
    graph.add_edge(1, 4)
    graph.add_edge(3, 5)

    # Perform DFS and BFS starting from vertex 0
    graph.depth_first_search(0)
    graph.breadth_first_search(0)

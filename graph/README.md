# Graph Traversal

Graph traversal is a fundamental operation in graph theory and algorithms. It involves visiting every vertex in a graph in a systematic way. The two primary methods of graph traversal are Depth-First Search (DFS) and Breadth-First Search (BFS).

## Table of Contents
1. [Depth-First Search (DFS)](#depth-first-search-dfs)
2. [Breadth-First Search (BFS)](#breadth-first-search-bfs)
3. [Implementation](#implementation)
4. [Applications](#applications)
5. [Time and Space Complexity](#time-and-space-complexity)

## Depth-First Search (DFS)

DFS explores as far as possible along each branch before backtracking. It can be implemented recursively or using a stack.

### Key Characteristics:
- Goes deep into the graph first before exploring neighbors
- Uses a stack (implicit in recursive implementation)
- Useful for topological sorting, detecting cycles, and path finding

## Breadth-First Search (BFS)

BFS explores all the neighbor nodes at the present depth prior to moving on to the nodes at the next depth level.

### Key Characteristics:
- Explores neighbors before going deeper
- Uses a queue
- Useful for finding shortest path on unweighted graphs and level-order traversals

## Implementation

Here's a basic template for graph traversal in Java:

```java
import java.util.*;

class Graph {
    private int numberOfVertices;  // Total number of vertices in the graph
    private List<List<Integer>> adjacencyList;  // Adjacency list to store graph edges

    // Constructor to initialize the graph with 'vertices' number of vertices
    public Graph(int vertices) {
        this.numberOfVertices = vertices;
        adjacencyList = new ArrayList<>(vertices);

        // Initialize each vertex's adjacency list
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Method to add a directed edge from 'sourceVertex' to 'destinationVertex'
    public void addEdge(int sourceVertex, int destinationVertex) {
        adjacencyList.get(sourceVertex).add(destinationVertex);
    }

    // Depth First Search (DFS) starting from a given vertex
    public void depthFirstSearch(int startVertex) {
        boolean[] visited = new boolean[numberOfVertices];  // Array to track visited vertices
        System.out.println("DFS starting from vertex " + startVertex + ":");
        depthFirstSearchUtil(startVertex, visited);  // Helper method for DFS
        System.out.println();
    }

    // Recursive helper method for DFS traversal
    private void depthFirstSearchUtil(int currentVertex, boolean[] visited) {
        // Mark the current vertex as visited
        visited[currentVertex] = true;
        System.out.print(currentVertex + " ");

        // Recur for all adjacent vertices
        for (int adjacentVertex : adjacencyList.get(currentVertex)) {
            if (!visited[adjacentVertex]) {
                depthFirstSearchUtil(adjacentVertex, visited);
            }
        }
    }

    // Breadth First Search (BFS) starting from a given vertex
    public void breadthFirstSearch(int startVertex) {
        boolean[] visited = new boolean[numberOfVertices];  // Array to track visited vertices
        Queue<Integer> queue = new LinkedList<>();  // Queue to manage BFS

        // Mark the start vertex as visited and enqueue it
        visited[startVertex] = true;
        queue.offer(startVertex);

        System.out.println("BFS starting from vertex " + startVertex + ":");

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();  // Dequeue the next vertex
            System.out.print(currentVertex + " ");

            // Enqueue all unvisited adjacent vertices
            for (int adjacentVertex : adjacencyList.get(currentVertex)) {
                if (!visited[adjacentVertex]) {
                    visited[adjacentVertex] = true;
                    queue.offer(adjacentVertex);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);  // Create a graph with 6 vertices (0 to 5)

        //     0 --> 1 --> 2
        //     |     |
        //     v     v
        //     3     4
        //     |
        //     v
        //     5
        // Adding edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 5);

        // Perform DFS and BFS starting from vertex 0
        graph.depthFirstSearch(0);
        graph.breadthFirstSearch(0);
    }
}

```
Here's a basic template for graph traversal in Python:
```python
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

```
### Explanation of Code

#### Graph Construction:
- The graph is created with 6 vertices, and directed edges are added between them using the `addEdge()` method.
- For example, `graph.addEdge(0, 1)` means there is a directed edge from vertex 0 to vertex 1.

#### DFS:
- The `depthFirstSearch()` method initiates a DFS from a starting vertex, using a boolean array `visited` to keep track of which vertices have been visited.
- The recursive helper function `depthFirstSearchUtil()` explores each vertex, printing it when visited, and recursively visits all adjacent unvisited vertices.

#### BFS:
- The `breadthFirstSearch()` method uses an iterative approach with a queue (`Queue<Integer>`).
- It enqueues the starting vertex and explores each level of the graph by visiting all adjacent vertices of the current node.
- The process continues until there are no more vertices to visit in the queue.

#### Example Graph Visualization:
```
0 --> 1 --> 2
|     |
v     v
3     4
|
v
5
```
In this graph:
- Vertex 0 has neighbors 1 and 3.
- Vertex 1 has neighbors 2 and 4.
- Vertex 3 has a neighbor 5.

### Execution:
For the given graph, we will perform both DFS and BFS starting from vertex 0.

#### DFS Output:
```
DFS starting from vertex 0:
0 1 2 4 3 5
```

DFS Traversal Explanation:
1. Start at vertex 0, move to vertex 1.
2. From 1, move to vertex 2 (left subtree is explored fully).
3. After finishing 2, backtrack to 1 and move to vertex 4.
4. Then backtrack to 0, move to vertex 3, and finally visit vertex 5.

#### BFS Output:
```
BFS starting from vertex 0:
0 1 3 2 4 5
```

BFS Traversal Explanation:
1. Start at vertex 0 and visit all of its neighbors first, which are 1 and 3.
2. Then move to the next level and visit the neighbors of 1, which are 2 and 4.
3. Finally, move to the neighbors of 3, which is vertex 5.

### Key Differences Between DFS and BFS:
- DFS explores as deep as possible along each branch before backtracking.
- BFS explores all neighbors at the present depth level before moving to the next depth level.
- DFS uses recursion (or an explicit stack), while BFS uses a queue.
## Applications

Graph traversal algorithms have numerous practical applications in computer science and real-world problem-solving. Some key applications include:

1. **Finding connected components**: Identifying groups of nodes that are connected to each other but disconnected from other groups.

2. **Topological sorting**: Ordering the nodes in a directed acyclic graph (DAG) such that for every directed edge (u, v), node u comes before node v in the ordering.

3. **Detecting cycles in a graph**: Determining whether a graph contains any cycles, which is crucial in many algorithms and real-world scenarios.

4. **Pathfinding algorithms**: Finding the shortest path between two nodes in a graph, used in navigation systems, network routing, and game AI.

5. **Solving puzzles and mazes**: Representing puzzles as graphs and using traversal algorithms to find solutions.

6. **Web crawling**: Exploring and indexing web pages by following links, which is fundamental to search engines.

## Time and Space Complexity

Understanding the time and space complexity of graph traversal algorithms is crucial for efficient implementation and scalability.

For a graph with V vertices and E edges:

### Time Complexity
- Both DFS and BFS: O(V + E)
  - In the worst case, we might need to visit all vertices and edges.

### Space Complexity
- DFS: O(V)
  - This accounts for the recursion call stack in the worst case (for a skewed graph).
- BFS: O(V)
  - This is for the queue used to store nodes at each level.

**Note**: The space complexity can be O(V + E) if we store the graph as an adjacency list. This is because the adjacency list itself takes O(V + E) space to represent all vertices and edges.

These complexities make graph traversal algorithms efficient for most practical applications, even with large graphs.
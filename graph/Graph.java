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

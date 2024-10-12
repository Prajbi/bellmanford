import java.util.Scanner;

public class bellmanford {
    // Function to run the Bellman-Ford algorithm
    public static void bellmanFord(int[][] graph, int v, int e, int src) {
        // Initialize distance array with large values (infinity)
        int[] dist = new int[v];
        for (int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE; // Set all distances to infinity
        }
        dist[src] = 0; // Distance to the source itself is always 0

        // Relax all edges (v-1) times
        for (int i = 1; i < v; i++) {
            for (int j = 0; j < e; j++) {
                int u = graph[j][0]; // Start vertex of the edge
                int vtx = graph[j][1]; // End vertex of the edge
                int weight = graph[j][2]; // Weight of the edge

                // Check if the current distance can be improved
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[vtx]) {
                    dist[vtx] = dist[u] + weight; // Update the distance if a shorter path is found
                }
            }
        }

        // Check for negative weight cycles by trying to relax one more time
        for (int j = 0; j < e; j++) {
            int u = graph[j][0];
            int vtx = graph[j][1];
            int weight = graph[j][2];

            // If we can still relax an edge, there is a negative cycle
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[vtx]) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // If no negative cycles are found, print the shortest distances
        printSolution(dist, v);
    }

    // Helper function to print the final distances from the source
    public static void printSolution(int[] dist, int v) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < v; i++) {
            System.out.println(i + "\t\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: number of vertices
        System.out.print("Enter number of vertices: ");
        int v = scanner.nextInt();

        // Input: number of edges
        System.out.print("Enter number of edges: ");
        int e = scanner.nextInt();

        // Create a graph as a 2D array to store edges in the form (u, v, w)
        int[][] graph = new int[e][3];
        System.out.println("Enter the edges (u, v, w) where u and v are vertices and w is the weight:");

        // Input the edges (u, v, weight)
        for (int i = 0; i < e; i++) {
            graph[i][0] = scanner.nextInt(); // Starting vertex of the edge
            graph[i][1] = scanner.nextInt(); // Ending vertex of the edge
            graph[i][2] = scanner.nextInt(); // Weight of the edge
        }

        // Input: source vertex from which distances will be calculated
        System.out.print("Enter source vertex: ");
        int src = scanner.nextInt();

        // Call the Bellman-Ford algorithm
        bellmanFord(graph, v, e, src);

        scanner.close();
    }
}

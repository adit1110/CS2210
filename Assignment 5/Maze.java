/* Name: Adit Bhimani
 * Student Number: 251289237
 * This is the Maze.java class
 * Represents a maze stored as an undirected graph.
 * Nodes represent rooms and edges represent corridors/doors.
 * Includes maze solving algorithm using DFS with coin costs.
 * Handles reading maze layout from input file and finding path.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Maze {
    private Graph graph; // graph representation of the maze
    private GraphNode entrance; // entrance node of the maze
    private GraphNode exit; // exit node of the maze
    private int coins; // number of coins available
    private List<GraphNode> path; // path from entrance to exit
    private int length; // length of the maze
    private int width; // width of the maze

    public Maze(String inputFile) throws MazeException {
        try { // try reading input file
            BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
            readInput(inputReader); // read and process input file
            inputReader.close();
        } catch (IOException | GraphException e) {
            throw new MazeException("Error reading input file: " + e.getMessage()); // throw exception if error reading file
        }
    }

    public Graph getGraph() throws MazeException { // get graph representation of maze
        if (graph == null) { // check if graph is initialized
            throw new MazeException("Graph has not been initialized."); // throw exception if graph is not initialized
        }
        return graph; // return graph representation of maze
    }

    public Iterator<GraphNode> solve() { // solve maze using DFS
        path = new ArrayList<>();
        try {
            return DFS(coins, entrance); // attempt to solve maze using DFS
        } catch (GraphException e) { // catch GraphException
            System.err.println("Error during DFS: "+ e.getMessage()); // print error message
        }
        return null;
    }

    // Helper method to perform DFS traversal of the maze
    private Iterator<GraphNode> DFS(int k, GraphNode go) throws GraphException { // DFS implementation
        if (go == null || go.isMarked()) { // check if node is null or already visited
            return null;
        }

        go.mark(true); // mark node as visited
        path.add(go); // add node to path
        System.out.println("Visiting node: " + go.getName() + ", Coins left: " + k); // print current node and coins left

        if (go.equals(exit)) { // check if current node is the exit
            return path.iterator(); // return path if exit is reached
        }

        Iterator<GraphEdge> edges = graph.incidentEdges(go); // get edges connected to current node
        while (edges.hasNext()) { // iterate over edges connected to current node
            GraphEdge edge = edges.next(); // get next edge
            GraphNode neighbor = edge.firstEndpoint().equals(go) ? edge.secondEndpoint() : edge.firstEndpoint(); // get neighbor node
            int cost = edge.getType(); // get cost of edge

            if (cost <= k) { // check if cost is less than or equal to coins left
                Iterator<GraphNode> result = DFS(k - cost, neighbor); // recursively visit neighbor node
                if (result != null) { // check if path is found
                    return result; // return path if found
                }
            }
        }

        path.remove(path.size() - 1);
        go.mark(false);
        System.out.println("Backtracking from node: " + go.getName());
        return null;
    }

    // Helper method to read input file and create graph representation of maze
    private void readInput(BufferedReader inputReader) throws IOException, GraphException {
        // Skip scale factor
        inputReader.readLine();
        
        // Read dimensions and coins
        width = Integer.parseInt(inputReader.readLine().trim());
        length = Integer.parseInt(inputReader.readLine().trim());
        coins = Integer.parseInt(inputReader.readLine().trim());

        // Initialize graph with the correct number of nodes
        graph = new Graph(length * width);
        
        // Read maze layout and identify entrance/exit
        char[][] maze = new char[length][width];
        for (int i = 0; i < length; i++) {
            String line = inputReader.readLine();
            for (int j = 0; j < width; j++) {
                maze[i][j] = line.charAt(j);
                if (maze[i][j] == 's') {
                    entrance = graph.getNode(i * width + j);
                } else if (maze[i][j] == 'x') {
                    exit = graph.getNode(i * width + j);
                }
            }
        }

        // Create edges between adjacent rooms
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (j < width - 1) {  // Add horizontal connections
                    addEdge(i, j, i, j + 1, maze[i][j + 1]);
                }
                if (i < length - 1) {  // Add vertical connections
                    addEdge(i, j, i + 1, j, maze[i + 1][j]);
                }
            }
        }
    }

    // Helper method to add an edge between two nodes
    private void addEdge(int i1, int j1, int i2, int j2, char type) throws GraphException { // add edge between two nodes
        int node1 = i1 * width + j1; // get node ID for first node
        int node2 = i2 * width + j2; // get node ID for second node
        String label = (type == 'c') ? "corridor" : "door"; // set label based on type
        int cost = (type >= '0' && type <= '9') ? type - '0' : 0; // set cost based on type
        graph.insertEdge(graph.getNode(node1), graph.getNode(node2), cost, label);  // insert edge between two nodes
    }
}
/* Name: Adit Bhimani
 * Student Number: 251289237
 * This is the Graph.java class
 * Represents an undirected graph using adjacency lists.
 * Stores nodes and their connections as edges.
 * Provides methods for graph manipulation and querying.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Graph implements GraphADT {

    private HashMap<Integer, GraphNode> nodes; // stores nodes by their names
    private HashMap<GraphNode, List<GraphEdge>> adjacencyList; // stores edges for each node
    
    public Graph(int n) { // create graph with n nodes
        nodes = new HashMap<>(); // initialize nodes and adjacency list
        adjacencyList = new HashMap<>(); // initialize adjacency list

        for (int i = 0; i < n; i++) { // create nodes and adjacency lists
            GraphNode node = new GraphNode(i);
            nodes.put(i, node);
            adjacencyList.put(node, new ArrayList<>()); // initialize adjacency list for each node
        }
    }
    
    public void insertEdge(GraphNode u, GraphNode v, int type, String label) throws GraphException {
        if (u == null || v == null) { // check if nodes are null
            throw new GraphException("One or both nodes are null."); // throw exception if either node is null
        }
        
        if(!nodes.containsKey(u.getName()) || !nodes.containsKey(v.getName())) {
            throw new GraphException("One or both nodes do not exist in the graph"); // Throws GraphException if nodes don't exist or are invalid
        }
        GraphEdge edge = new GraphEdge(u, v, type, label); // create new edge between u and v
        adjacencyList.get(u).add(edge); // add edge to adjacency lists of both nodes
        adjacencyList.get(v).add(edge); // add edge to adjacency lists of both nodes
    }


    public GraphNode getNode(int name) throws GraphException { // get node by name
        GraphNode node = nodes.get(name);
        if(node == null) { // check if node exists
            throw new GraphException("Node " + name + " does not exist in the graph."); // throw exception if node doesn't exist
        }
        return node;
    }

    /* Returns an iterator over all edges connected to node u
     * Throws GraphException if node doesn't exist in graph
     */
    public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException { // get edges connected to node u
        if (!nodes.containsValue(u)) { // check if node exists
            throw new GraphException("Node does not exist in the graph"); // throw exception if node doesn't exist
        }
        return adjacencyList.getOrDefault(u, new ArrayList<>()).iterator(); // return iterator over edges connected to u
    }

    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
        if(!nodes.containsValue(u) || !nodes.containsValue(v)) { // check if nodes exist in the graph
            throw new GraphException("One or both nodes do not exist in the graph."); // throw exception if either node doesn't exist
        }
        for (GraphEdge edge : adjacencyList.get(u)) { // check each edge connected to u
            if ((edge.firstEndpoint().equals(u) && edge.secondEndpoint().equals(v)) ||
                (edge.firstEndpoint().equals(v) && edge.secondEndpoint().equals(u))) {
                return edge; // return edge if it connects u and v
            }
        }
        return null; // return null if no edge exists between u and v
    }

    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException { // check if nodes are adjacent (throws graph exception if either node doesn't exist)
        return getEdge(u, v) != null; // return true if edge exists between u and v
    }
}
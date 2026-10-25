/* Name: Adit Bhimani
 * Student Number: 251289237
 * This is the GraphNode.java class
 * Represents a node (room) in the graph.
 * Each node has a unique name (ID) and can be marked during traversal.
 */

public class GraphNode {

	private int name; // unique identifier for the node
	private boolean marked; // mark status used during graph traversal
	
	public GraphNode(int name) {
		this.name = name; // creates a new node with the specified name
		this.marked = false; // initializes the mark status to false
	}

	
	public void mark(boolean mark) {
		this.marked = mark; // marks the node with the specified value
	}
	
	public boolean isMarked() {
		return this.marked; // returns the value with which the node has been marked
	}
	
	public int getName() {
		return this.name; // returns the unique identifier for the node
	}
	
}

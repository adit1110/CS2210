/* Name: Adit Bhimani
 * Student Number: 251289237
 * This is the GraphEdge.java class
 * Represents an edge in the graph, connecting two nodes.
 * Includes type (cost in coins) and label (e.g., "corridor", "door").
 */


public class GraphEdge {

	private GraphNode origin;
	private GraphNode destination;
	private int type;
	private String label;
	
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		this.origin = u; // origin is the first endpoint
		this.destination = v; // destination is the second endpoint
		this.type = type; // type is the type of edge
		this.label = label; // label is the label of the edge
	}
	
	public GraphNode firstEndpoint() {
		return this.origin; // returns the first endpoint
	}
	
	public GraphNode secondEndpoint() {
		return this.destination; // returns the second endpoint
	}
	
	public int getType() {
		return this.type; // returns the type of edge
	}
	
	public void setype(int type) {
		this.type = type; // sets the type of edge
	}
	
	public String getLabel() {
		return this.label; // returns the label of the edge
	}
	
	public void setLabel(String label) {
		this.label = label; // sets the label of the edge
	}
	
}

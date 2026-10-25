// Name: Adit Bhimani
// Student Number: 251289237
// This is the BSTNode.java file

// instance variables to store references to the record, left child, right child, and parent of the node
public class BSTNode { 
    private Record record;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private BSTNode parent;
    
    // constructor to initialize the node with a record
    public BSTNode(Record item) {
        // initalize te node's record and set the child and parent references to null
        this.record = item;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }


    public Record getRecord() { // method to return the record store in this node
        return this.record;
    }

    public void setRecord(Record d) { // // Method to set the record for this node.
        this.record = d; // set the provided record to this node
        // reset the child and parent references when a new record is set
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    public BSTNode getLeftChild() { // Method to return the left child of this node.
        return this.leftChild;
    }

    public BSTNode getRightChild() { // Method to return the right child of this node.
        return this.rightChild;
    }

    public BSTNode getParent() { // Method to return the parent of this node.
        return this.parent;
    }

    public void setLeftChild(BSTNode u) { // Method to set the left child of this node and update its parent.
        this.leftChild = u;
        if (u != null) {
            u.setParent(this); // set the parent of the left child to this node
        }
    }

    public void setRightChild(BSTNode u) { // method to set the right child of this node and update its parent
        this.rightChild = u;
        if(u != null) {
            u.setParent(this); // set the parent of the right child to this node
        }
    }

    public void setParent(BSTNode u) { // method to set the parent of this node
        this.parent = u;
    }

    public boolean isLeaf() { // Method to check if this node is a leaf (has no children).
        return (this.leftChild == null && this.rightChild == null);
    }
}

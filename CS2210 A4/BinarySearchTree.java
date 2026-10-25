// Name: Adit Bhimani
// Student Number: 251289237
// This is the BinarySearchTree.java file

public class BinarySearchTree {
    private BSTNode root; // the root of the binary search tree

    public BinarySearchTree() { // constructor to initalize an empty tree
        this.root = null;
    }

    public BSTNode getRoot() { // method to get the root of the tree
        return this.root;
    }

    public BSTNode get(BSTNode r, Key k) { // method to find and return the node with the specified key in the tree
        if (r == null) { // if the node is null, the key is not found
            return null;
        }

        int compare = k.compareTo(r.getRecord().getKey()); // compare the key with the key of the current node
        if (compare == 0) { // if the keys are the same, return the current node
            return r;
        } else if (compare < 0) { // if the key is less than the current node's key, search the left subtree
            return get(r.getLeftChild(), k);
        } else { // if the key is greater than the current node's key, search the right subtree
            return get(r.getRightChild(), k);
        }
    }

    public void insert(BSTNode r, Record d) throws DictionaryException { // method to insert a record into the tree
    // Handle the case where the tree is empty, and a root node needs to be created
    if (r == null) {
        root = new BSTNode(d);
        return;
    }
    
    // If this node is a leaf (no record), place the record here
    if (r.getRecord() == null) {
        r.setRecord(d);
        return;
    }

    // Compare the key of the new record with the key of the current node
    Key currentKey = r.getRecord().getKey(); 
    int compare = d.getKey().compareTo(currentKey);

    if (compare < 0) {  // New record should go to the left
        if (r.getLeftChild() == null) { // If there is no left child, insert the new record here
            BSTNode newNode = new BSTNode(d);
            r.setLeftChild(newNode);
            newNode.setParent(r);
        } else {
            insert(r.getLeftChild(), d); // Recursively insert into the left subtree
        }
    } else if (compare > 0) {  // New record should go to the right
        if (r.getRightChild() == null) { // If there the right child is null, create a new node and set it as the right child
            BSTNode newNode = new BSTNode(d);
            r.setRightChild(newNode);
            newNode.setParent(r);
        } else { // Recursively insert into the right subtree
            insert(r.getRightChild(), d);
        }
    } else { // Key already exists in the dictionary
        throw new DictionaryException("Key already exists in the dictionary.");
    }
}

    public void remove(BSTNode r, Key k) throws DictionaryException { // method to remove a node with the given key from the tree
    // Find the node to remove
    BSTNode nodeToRemove = get(r, k);
    if (nodeToRemove == null) { // if the node is not found, throw an exception
        throw new DictionaryException("Key not found in the dictionary.");
    }

    // Case 1: Node is a leaf
    if (nodeToRemove.getLeftChild() == null && nodeToRemove.getRightChild() == null) {
        if (nodeToRemove == root) { // If the node is the root, set the root to null
            root = null;
        } else { // Otherwise, remove the node by updating the parent's reference
            BSTNode parent = nodeToRemove.getParent();
            if (parent.getLeftChild() == nodeToRemove) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        }
        return;
    }

    // Case 2: Node has one child
    if (nodeToRemove.getLeftChild() == null || nodeToRemove.getRightChild() == null) {
        BSTNode child = (nodeToRemove.getLeftChild() != null) ? 
                        nodeToRemove.getLeftChild() : nodeToRemove.getRightChild();
        
        if (nodeToRemove == root) { // If the node is the root, set the root to the child
            root = child;
            child.setParent(null);
        } else { // Otherwise, link the parent to the child
            BSTNode parent = nodeToRemove.getParent();
            if (parent.getLeftChild() == nodeToRemove) {
                parent.setLeftChild(child);
            } else {
                parent.setRightChild(child);
            }
            child.setParent(parent);
        }
        return;
    }

    // Case 3: Node has two children
    // Find the successor (smallest node in right subtree)
    BSTNode successor = smallest(nodeToRemove.getRightChild());
    Record successorRecord = successor.getRecord();
    
    // Remove the successor (it has at most one child)
    remove(nodeToRemove, successor.getRecord().getKey());
    
    // Replace nodeToRemove's data with successor's data
    nodeToRemove.setRecord(successorRecord);
}
    
    public BSTNode successor(BSTNode r, Key k) { // method to find and return the successor of the node with the given key
        BSTNode node = get(r, k);

        if(node == null) { // return null if the node with the given key is not found
            return null;
        }

        if(node.getRightChild() != null) { // if the node has a right child, return the smallest node in the right subtree (the successor)
            return smallest(node.getRightChild());
        }

        BSTNode parent = node.getParent(); // traverse up the tree to find the successor
        while(parent != null && node == parent.getRightChild()) {
            node = parent;
            parent = node.getParent();
        }
        return parent;
    }

    public BSTNode predecessor(BSTNode r, Key k) { // method to find the predecessor of a given key in the tree
        BSTNode node = get(r, k);

        if(node == null) { // return null if the node with the given key is not found
            return null;
        }

        if(node.getLeftChild() != null) { // if the node has a left child, return the largest node in the left subtree (the predecessor)
            return largest(node.getLeftChild());
        }

        BSTNode parent = node.getParent(); // traverse up the tree to find the predecessor
        while(parent != null && node == parent.getLeftChild()) {
            node = parent;
            parent = node.getParent();
        }
        return parent;
    }

    public BSTNode smallest(BSTNode r) { // method to find and return the smallest node in a subtree
        BSTNode current = r;

        while (current != null && current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return current;
    }

    public BSTNode largest(BSTNode r) { // method to find and return the largest node in a subtree
        BSTNode current = r;

        while (current != null && current.getRightChild() != null) { // traverse to the rightmost node in the subtree
            current = current.getRightChild();
        }
        return current;
    }
   
}
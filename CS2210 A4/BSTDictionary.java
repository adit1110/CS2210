// Name: Adit Bhimani
// Student Number: 251289237
// This is the BSTDictionary.java file

public class BSTDictionary implements BSTDictionaryADT {
    // Instance variable to hold the binary search tree.
    private BinarySearchTree bst;

    // Constructor to initialize the dictionary with an empty binary search tree.
    public BSTDictionary() {
        this.bst = new BinarySearchTree();
    }

    // Method to retrieve a record by its key.
    public Record get(Key k) {
        // Use the get() method of the BinarySearchTree to find the node with the given key.
        BSTNode node = bst.get(bst.getRoot(), k);
        // If the node is found, return its record; otherwise, return null.
        return (node != null) ? node.getRecord() : null;
    }

    // Method to insert a new record into the dictionary.
    public void put(Record d) throws DictionaryException {
        // If the tree is empty (root is null), initialize it with the first record.
        if (bst.getRoot() == null) {
            bst.insert(bst.getRoot(), d); // Initialize the root if it's null.
        } else {
            // Otherwise, insert the record into the tree starting from the root.
            bst.insert(bst.getRoot(), d);
        }
    }

    // Method to remove a record by its key from the dictionary.
    public void remove(Key k) throws DictionaryException {
        // Call the remove() method of the BinarySearchTree to remove the node with the given key.
        bst.remove(bst.getRoot(), k);
    }

    // Method to find the successor of a given key.
    public Record successor(Key k) {
        // Use the successor() method of the BinarySearchTree to find the successor node.
        BSTNode node = bst.successor(bst.getRoot(), k);
        // If the successor is found, return its record; otherwise, return null.
        return (node != null) ? node.getRecord() : null;
    }

    // Method to find the predecessor of a given key.
    public Record predecessor(Key k) {
        // Use the predecessor() method of the BinarySearchTree to find the predecessor node.
        BSTNode node = bst.predecessor(bst.getRoot(), k);
        // If the predecessor is found, return its record; otherwise, return null.
        return (node != null) ? node.getRecord() : null;
    }

    // Method to find the record with the smallest key in the dictionary.
    public Record smallest() {
        // Use the smallest() method of the BinarySearchTree to find the smallest node.
        BSTNode node = bst.smallest(bst.getRoot());
        // If the smallest node is found, return its record; otherwise, return null.
        return (node != null) ? node.getRecord() : null;
    }

    // Method to find the record with the largest key in the dictionary.
    public Record largest() {
        // Use the largest() method of the BinarySearchTree to find the largest node.
        BSTNode node = bst.largest(bst.getRoot());
        // If the largest node is found, return its record; otherwise, return null.
        return (node != null) ? node.getRecord() : null;
    }
}

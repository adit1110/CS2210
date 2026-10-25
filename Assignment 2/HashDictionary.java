/* Name: Adit Bhimani
   Student Number: 251289237
   Date: October 27th, 2024
   Description: This class implements a hash table to store and retrieve game board configurations efficiently using
                a polynomial hash function. 
                It uses separate chaining to handle collisions and provides methods to add,
                remove, and retrieve configurations along with their scores. 
                It also keeps track of the number of records stored in the hash table.
    */


import java.util.LinkedList;

// Class implementing a hash table to store Data objects using a polynomial hash function
public class HashDictionary implements DictionaryADT {
    private int size; // size of the hash table
    private LinkedList<Data>[] table; // array of linked lists to store Data objects

    // Constructor to initialize the hash table with a specific size
    public HashDictionary(int size) {
        this.size = size;
        table = (LinkedList<Data>[]) new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Private method to compute a hash value for a given string using a polynomial hash function
    private int hashFunction(String key) {
        int hashVal = 0;
        int prime = 10;
        for (int i = 0; i < key.length(); i++) {
            hashVal = (prime * hashVal + key.charAt(i)) % size;
        }
        return Math.abs(hashVal);
    }

    // Method to add a Data record to the hash table
    public int put(Data record) throws DictionaryException {
        int index = hashFunction(record.getConfiguration());
        LinkedList<Data> bucket = table[index];

        // Check if the configuration already exists in the hash table
        for (Data data : bucket) {
            if (data.getConfiguration().equals(record.getConfiguration())) {
                throw new DictionaryException(); // if it does, throw an exception
            }
        }

        // Add the enw record and return the appropriate value based on collision
        bucket.add(record);
        return bucket.size() > 1 ? 1 : 0;
    }

    // Method to remove a configuration from the hash table
    public void remove(String config) throws DictionaryException {
        int index = hashFunction(config);
        LinkedList<Data> bucket = table[index];

        // Search for the configuration in the linked list and remove it if it is found
        for (Data data : bucket) {
            if (data.getConfiguration().equals(config)) {
                bucket.remove(data); // Remove the matching configuration if it is found
                return;
            }
        }

        throw new DictionaryException(); // Throw an exception if the configuration is not found
    }

    // Method to retrieve the score associated with a configuration
    public int get(String config) {
        int index = hashFunction(config); // Get the bucket index
        LinkedList<Data> bucket = table[index]; // Retrieve the bucket 

        // Search for the configuration in the linked list and return its score if it is found
        for (Data data : bucket) {
            if (data.getConfiguration().equals(config)) {
                return data.getScore(); // Return the score if it is found
            }
        }

        return -1; // Return -1 if the configuration is not found
    }

    // Method to count the number of records stored in the hash table
    public int numRecords() {
        int count = 0;
        // Count the number of records in each bucket
        for (LinkedList<Data> bucket : table) {
            count += bucket.size();
        }
        return count; // Return the total number of records
    }
    
}

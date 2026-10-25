// Name: Adit Bhimani
// Student Number: 251289237
// This is the Record.java file

public class Record { // Instance variables to store the key and data of the record
    private Key theKey;
    private String data;


public Record (Key theKey, String theData) { // Constructor to initialize the record with a key and data
    this.theKey = theKey; // Set the key of the record
    this.data = data; // Set the data of the record

    }

    public Key getKey() { // Getter method to return the key of the record
        return this.theKey; // Return the key of the record
    }

    public String getDataItem() { // Getter method to return the data of the record
        return this.data; // Return the data of the record
    }

}

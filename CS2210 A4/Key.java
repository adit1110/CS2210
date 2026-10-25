// Name: Adit Bhimani
// Student Number: 251289237
// This is the Key.java file

public class Key { // Instance variables to store the label and type of key
    private String label;
    private int type;


    public Key(String label, int type) { // Constructor to initialize the key with a label and type.
        this.label = label; // Set the label and type of the key
        this.type = type; // Set the type of the key
    }

    public String getLabel() { // Getter method to return the label of the key
        return this.label; // Return the label of the key
    }

    public int getType() { // Getter method to return the type of the key
        return this.type; // Return the type of the key
    }

    public int compareTo(Key k) { // Method to compare two keys based on label and type
        int labelCompare = this.label.compareTo(k.getLabel());
        if (labelCompare != 0) { // If the labels are different, return the comparison result
            return labelCompare;
        } else { // If the labels are the same, compare the types
            return this.type - k.getType();
        }
    }
}
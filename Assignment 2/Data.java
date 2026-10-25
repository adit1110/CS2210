/* Name: Adit Bhimani
   Student Number: 251289237
   Date: October 27th, 2024
   Description: This class is used to store a specific configuration of the game board along with its associated score.
                It provides methods to access the configuration and its score, and it is designed to be used in the
                hash table for quick lookups and storage.
    */

// This class represents a configuration and it's associated score
public class Data {
    private String configuration; // string representation of the board's configuration
    private int score; // the score associated with the configuration

    // constructor to initialize the configuration and score
    public Data(String config, int score) {
        this.configuration = config;
        this.score = score;
    }

    // getter method to retrive the configuration
    public String getConfiguration () {
        return this.configuration;
    }

    // getter method to retrieve the score
    public int getScore () {
        return this.score;
    }
}

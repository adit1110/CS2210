// Name: Adit Bhimani
// Student Number: 251289237
// This is the Interface.java file

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Interface {
    public static void main(String[] args) {
        // Check if the user provided the correct number of command-line arguments.
        if (args.length != 1) {
            System.out.println("Usage: java Interface inputFile");
            return; // Exit the program if the usage is incorrect.
        }

        // Get the input file name from the command-line arguments.
        String inputFile = args[0];
        BSTDictionary dictionary = new BSTDictionary(); // Initialize an empty dictionary.
        StringReader keyboard = new StringReader(); // Create a StringReader for user input.

        // Try to read the input file and populate the dictionary.
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String label;
            // Read each line from the file and process it.
            while ((label = br.readLine()) != null) {
                String dataString = br.readLine(); // Read the associated data line.
                label = label.toLowerCase().trim(); // Normalize the label (to lowercase).

                if (dataString != null) {
                    int type;
                    String data;
                    char character = dataString.charAt(0); // Get the first character of the data line.

                    // Determine the type of the data based on the first character or file extension.
                    switch (character) {
                        case '-':
                            type = 3; // Type 3 for sound files.
                            data = dataString.substring(1).trim();
                            break;
                        case '+':
                            type = 4; // Type 4 for play files.
                            data = dataString.substring(1).trim();
                            break;
                        case '*':
                            type = 5; // Type 5 for say files.
                            data = dataString.substring(1).trim();
                            break;
                        case '/':
                            type = 2; // Type 2 for translations.
                            data = dataString.substring(1).trim();
                            break;
                        default:
                            // Determine type based on file extension if applicable.
                            if (dataString.endsWith(".gif")) {
                                type = 7; // Type 7 for GIF images.
                                data = dataString;
                            } else if (dataString.endsWith(".jpg")) {
                                type = 6; // Type 6 for JPG images.
                                data = dataString;
                            } else if (dataString.endsWith(".html")) {
                                type = 8; // Type 8 for HTML files.
                                data = dataString;
                            } else {
                                type = 1; // Default type for general text data.
                                data = dataString;
                            }
                    }

                    // Create a Key and a Record object for the dictionary.
                    Key key = new Key(label, type);
                    Record record = new Record(key, data);

                    // Attempt to insert the record into the dictionary.
                    try {
                        dictionary.put(record);
                    } catch (DictionaryException e) {
                        // Print an error if a duplicate key is detected.
                        System.out.println("Error inserting record: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            // Print an error message if there is an issue reading the input file.
            System.out.println("Error reading the file: " + e.getMessage());
        }

        // Main loop to process user commands.
        boolean running = true;
        while (running) {
            String command = keyboard.read("Enter next command: ").trim().toLowerCase(); // Read user input.
            if (command.equals("exit")) {
                running = false; // Exit the loop if the command is "exit".
                break;
            }

            StringTokenizer st = new StringTokenizer(command); // Tokenize the input command.
            if (!st.hasMoreTokens()) {
                continue; // Skip if there are no tokens (empty input).
            }

            String cmd = st.nextToken(); // Get the first token as the command.
            try {
                // Process the command based on its type.
                switch (cmd) {
                    case "define":
                        if (st.hasMoreTokens()) {
                            String label = st.nextToken();
                            Record rec = dictionary.get(new Key(label, 1)); // Search for a record with type 1.
                            if (rec != null) {
                                System.out.println(rec.getDataItem());
                            } else {
                                System.out.println("The word " + label + " is not in the ordered dictionary.");
                            }
                        } else {
                            System.out.println("Invalid command.");
                        }
                        break;
                    case "translate":
                        if (st.hasMoreTokens()) {
                            String label = st.nextToken();
                            Record rec = dictionary.get(new Key(label, 2)); // Search for a record with type 2.
                            if (rec != null) {
                                System.out.println(rec.getDataItem());
                            } else {
                                System.out.println("There is no definition for the word " + label);
                            }
                        } else {
                            System.out.println("Invalid command.");
                        }
                        break;
                    case "sound":
                    case "play":
                    case "say":
                        // Process multimedia commands related to audio.
                        audioCommand(cmd, st, dictionary);
                        break;
                    case "show":
                    case "animate":
                        // Process multimedia commands related to images.
                        imageCommand(cmd, st, dictionary);
                        break;
                    case "browse":
                        // Process multimedia commands related to browsing HTML files.
                        browseCommand(cmd, st, dictionary);
                        break;
                    case "exit":
                        running = false; // Exit the loop if the command is "exit".
                        break;
                    default:
                        System.out.println("Invalid command.");
                        break;
                }
            } catch (MultimediaException e) {
                // Print an error if there is an issue with multimedia processing.
                System.out.println("Error with multimedia: " + e.getMessage());
            }
        }
    }

    private static void audioCommand(String cmd, StringTokenizer st, BSTDictionary dictionary) throws MultimediaException {
    if (st.hasMoreTokens()) { // Check if there are more tokens in the command.
        String label = st.nextToken();
        int type; 

        // Use a traditional switch block.
        switch (cmd) {
            case "sound": // Determine the type based on the command.
                type = 3;
                break;
            case "play":
                type = 4;
                break;
            case "say":
                type = 5;
                break;
            default:
                type = -1; // Invalid type (shouldn't happen).
                break;
        }

        // Check if the type is valid before proceeding.
        if (type == -1) { // if the type is invalid, print an error message and return.
            System.out.println("Invalid command type.");
            return;
        }

        // Get the record from the dictionary and play it if it exists.
        Record rec = dictionary.get(new Key(label, type));
        if (rec != null) { // Check if the record exists.
            try {
                SoundPlayer player = new SoundPlayer();
                player.play(rec.getDataItem());
            } catch (Exception e) { // Catch any exceptions that occur during playback.
                throw new MultimediaException("Error playing sound: " + e.getMessage());
            }
        } else { // Print an error message if the record does not exist.
            System.out.println("There is no " + cmd + " file for " + label);
        }
    } else { // Print an error message if there are no more tokens in the command.
        System.out.println("Invalid command.");
    }
}


    // Method to handle image-related commands.
    private static void imageCommand(String cmd, StringTokenizer st, BSTDictionary dictionary) throws MultimediaException {
        if (st.hasMoreTokens()) {
            String label = st.nextToken();
            int type = (cmd.equals("show")) ? 6 : 7; // Determine the type based on the command.

            Record rec = dictionary.get(new Key(label, type)); // Search for a record with the corresponding type.
            if (rec != null) {
                try {
                    PictureViewer viewer = new PictureViewer();
                    viewer.show(rec.getDataItem()); // Display the image data.
                } catch (Exception e) {
                    throw new MultimediaException("Error showing image: " + e.getMessage());
                }
            } else {
                System.out.println("There is no " + cmd + " image file for " + label);
            }
        } else {
            System.out.println("Invalid command.");
        }
    }

    // Method to handle browsing commands for HTML files.
    private static void browseCommand(String cmd, StringTokenizer st, BSTDictionary dictionary) throws MultimediaException {
        if (st.hasMoreTokens()) {
            String label = st.nextToken();
            Record rec = dictionary.get(new Key(label, 8)); // Search for a record with type 8 (HTML).
            if (rec != null) {
                try {
                    ShowHTML browser = new ShowHTML();
                    browser.show(rec.getDataItem()); // Open the HTML data in the browser.
                } catch (Exception e) {
                    throw new MultimediaException("Error displaying HTML: " + e.getMessage());
                }
            } else {
                System.out.println("There is no webpage called " + label);
            }
        } else {
            System.out.println("Invalid command.");
        }
    }
}

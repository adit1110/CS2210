/* Name: Adit Bhimani
   Student Number: 251289237
   Date: October 27th, 2024
   Description: This class is used to store and manage the configuration of the game board, including its size,
                the number of consecutive symbols required to win, and the maximum levels allowed for recursion.
                It provides methods to check for wins, evaluate the board state, and interact with a hash table
                for storing and retrieving board configurations.
    */

// Class to manage the configuration of the game and it's related operations
public class Configurations {
    private char[][] board; // 2D array to store the game board
    private int boardSize; // size of the game board
    private int lengthToWin; // number of consecutive symbols required to win
    private int maxLevels; // maximum levels allowed for recursion

    // Constructor to initialize the configuration of the game board
    public Configurations(int boardSize, int lengthToWin, int maxLevels) {
        this.boardSize = boardSize;
        this.lengthToWin = lengthToWin;
        this.maxLevels = maxLevels;
        this.board = new char[boardSize][boardSize];

        // Initialize all game board cells with empty spaces ('')
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    // Method to create a new hash table with a specific size
    public HashDictionary createDictionary() {
        return new HashDictionary(7001); // prime number for the hashtable size
    }

    // Method to check if the current configuration is already in the hash table
    public int repeatedConfiguration(HashDictionary hashTable) {
        String boardConfig = boardToString();
        return hashTable.get(boardConfig);
    }

    // Method to add the current configuration and its score to the hash table
    public void addConfiguration(HashDictionary hashDictionary, int score) {
        String boardConfig = boardToString(); // Convert the board state to a string
        Data data = new Data(boardConfig, score); // Create a new Data object with config and score
        try {
            hashDictionary.put(data); // Add the configuration to the hash table
        } catch (DictionaryException e) {
            System.out.println("Configuration already exists.");
        }
    }

    // Method to save a play on the board at a given position
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol; // Update the board with the player's move
    }

    // Method to check if a specific cell is empty
    public boolean squareIsEmpty(int row, int col) {
        return board[row][col] == ' '; // Return true if the cell is empty
    }

    // Method to check if a symbol ('X' or 'O') has won
    public boolean wins(char symbol) {
        // Check if there's a winning row, column, or diagonal for the symbol
        return checkRows(symbol) || checkCols(symbol) || checkDiagonals(symbol);
    }

    // Method to check if the game has ended in a draw
    public boolean isDraw() {
        // iterate through the board to check for any empty cell
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return false; // if an empty cell is found, it is not a draw
                }
            }
        }
        // If the board is full, check if there is no winner
        return !wins('X') && !wins('O');
    }

    // Method to evaluate the board state
    public int evalBoard() {
        if (wins('O')) {
            return 3; // Computer wins
        } else if (wins('X')) {
            return 0; // Human wins
        } else if (isDraw()) {
            return 2; // Game is a draw
        } else {
            return 1; // Game is undeclared
        }
    }

    // Method to convert the board state into a string for hashing
    private String boardToString() {
        StringBuilder sb = new StringBuilder();
        // Iterate through the board and append each cell to the string
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString(); // Return the string representation of the board
    }

    // Method to check for horizontal wins
    private boolean checkRows(char symbol) {
        for (int i = 0; i < boardSize; i++) { // iterate through each row
            int consecutive = 0; // count of consecutive symbols
            for (int j = 0; j < boardSize; j++) { // Loop through each column
                if (board[i][j] == symbol) {
                    consecutive++; // increment the count if the symbol matches
                    if (consecutive >= lengthToWin) { // If each enough consecutive symbols are found
                        return true; // Then it is a horizontal win
                    }
                } else {
                    consecutive = 0; // Reset the count if the symbol does not match
                }
            }
        }
        return false; // Return false if no horizontal win is found
    }

    // Method to check for vertical wins
    private boolean checkCols(char symbol) { // Check for vertical wins
        for (int j = 0; j < boardSize; j++) { // Iterate through each column
            int consecutive = 0;
            for (int i = 0; i < boardSize; i++) { // Loop through each row
                if (board[i][j] == symbol) { // Check if the symbol matches
                    consecutive++;
                    if (consecutive >= lengthToWin) { // If enough consecutive symbols are found
                        return true; // then it is a vertical win
                    }
                } else {
                    consecutive = 0; // Reset the count if the symbol does not match
                }
            }
        }
        return false; // Return false if no vertical win is found
    }


    // Method to check for diagonal wins
    private boolean checkDiagonals(char symbol) {
        // Check main diagonal (top-left to bottom-right)
        for (int i = 0; i <= boardSize - lengthToWin; i++) {
            for (int j = 0; j <= boardSize - lengthToWin; j++) {
                if (checkDiagonalFrom(i, j, symbol, 1, 1)) { // Check the diagonal from the current cell
                    return true; // Return true if enough consecutive symbols are found
                }
            }
        }

        // Check anti diagonals (top-right to bottom-left)
        for (int i = 0; i <= boardSize - lengthToWin; i++) {
            for (int j = lengthToWin - 1; j < boardSize; j++) {
                if (checkDiagonalFrom(i, j, symbol, 1, -1)) { // Check the diagonal from the current cell
                    return true; // Return true if enough consecutive symbols are found
                }
            }
        }

        return false; // No diagonal win found
    }

    // Helper method to check diagonals from a specific start point
    private boolean checkDiagonalFrom(int startRow, int startCol, char symbol, int rowInc, int colInc) {
        int consecutive = 0; // Count of consecutive symbols
        int row = startRow;
        int col = startCol;
        while (row >= 0 && row < boardSize && col >= 0 && col < boardSize) { // Check if the cell is within the board
            if (board[row][col] == symbol) {
                consecutive++; // Increment the count if the symbol matches
                if (consecutive >= lengthToWin) { // Check if enough consecutive symbols are found
                    return true; // Return true if enough consecutive symbols are found
                }
            } else {
                consecutive = 0; // Reset the count if the symbol does not match
            }
            row += rowInc; // Move to the next cell in the diagonal
            col += colInc; // Move to the next cell in the diagonal
        }
        return false;
    }
}

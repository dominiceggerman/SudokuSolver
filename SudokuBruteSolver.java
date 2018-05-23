/****************************************
 *            Sudoku Solver             *
 *         "Brute Force" Method         *
 *     Written by Dominic Eggerman      *
 ****************************************/

// Excecution:  javac SudokuBruteSolver.java
//              java SudokuBruteSolver
// Arguements:  None
// The board to solve is changed in the solver file

public class SudokuBruteSolver {
    
    /******************
     * Board to solve *
     ******************/
    public static int[][] board = {
        {8, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 3, 6, 0, 0, 0, 0, 0},
        {0, 7, 0, 0, 9, 0, 2, 0, 0},
        {0, 5, 0, 0, 0, 7, 0, 0, 0},
        {0, 0, 0, 0, 4, 5, 7, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 3, 0},
        {0, 0, 1, 0, 0, 0, 0, 6, 8},
        {0, 0, 8, 5, 0, 0, 0, 1, 0},
        {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };
    
    
    /***************
     * Basic checks *
     ****************/
    // Check if each row is valid
    private boolean rowValid(int[][] board, int row) {
        // False boolean array
        boolean[] unique = new boolean[9];
        // Search method
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != 0) {
                // If a value, check if the value in unique array is true, 
                // else make that index true
                if (unique[board[row][i] - 1]) { return false; }
                else { unique[board[row][i] - 1] = true; }
            }
        }
        // Pass check
        return true;
    }
    
    // Check if each column is valid
    private boolean colValid(int[][] board, int col) {
        // False boolean array
        boolean[] unique = new boolean[9];
        // Search method
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != 0) {
                // If a value, check if the value in unique array is true, 
                // else make that index true
                if (unique[board[i][col] - 1]) { return false; }
                else { unique[board[i][col] - 1] = true; }
            }
        }
        // Pass check
        return true;
    }
    
    // Check if section (3x3) is valid
    private boolean sectionValid(int[][] board, int row, int col) {
        boolean[] constraint = new boolean[9];
        // Make start and end points for the subsection of the sudoku
        int rowStart  =  (row / 3) * 3;
        int rowEnd    =  rowStart + 3;
        int colStart  =  (col / 3) * 3;
        int colEnd    =  colStart + 3;
        // Loop through section
        for (int r = rowStart; r < rowEnd; r++) {
            for (int c = colStart; c < colEnd; c++) {
                // Check using checkConstraint
                if (!checkConstraint(board, r, c, constraint)) { return false; }
            }
        }
        // If test passes
        return true;
    }
    
    // Check constraint helper function
    private boolean checkConstraint(int[][] board, int row, int col, 
                                    boolean[] constraint) {
        // If value in square
        if (board[row][col] != 0) {
            // If array value is false, change to true, else return false
            if (!constraint[board[row][col] - 1]) {
                constraint[board[row][col] - 1] = true;
            } else { return false; }
        }
        // If array passes, return true
        return true;
    }
    
     /*****************
     * Use all checks *
     ******************/
    private boolean checkAll(int[][] board, int row, int col) {
        return (rowValid(board, row) && 
                colValid(board, col) && 
                sectionValid(board, row, col));
    }
    
    /*********
     * Solve *
     *********/
    private boolean solve(int[][] board) {
        // Loop through rows and columns of board, if square has no value (0)...
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    // Guess a value until it is valid
                    for (int val = 1; val <= 9; val++) {
                        board[row][col] = val;
                        // If pass checks, return true (solved board)
                        if (checkAll(board, row, col) && solve(board)) {
                            return true;
                        }
                        // Reset square with no value
                        board[row][col] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    /***************
     * Print Board *
     ***************/
    private void printBoard() {
        // Loop through board
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Print board square
                System.out.print(board[row][col] + " ");
            }
            // Newline for each row
            System.out.println();
        }
    }
    
    /***************************************
     *               Main                  *
     ***************************************/
    public static void main(String args[]) {
        
        // Solves the board and prints it to the interactions pane
        SudokuBruteSolver sudoku = new SudokuBruteSolver();
        sudoku.solve(board);
        sudoku.printBoard();
    }
}
import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = '-';
    private static final char PLAYER1 = 'X';
    private static final char PLAYER2 = 'O';

    public static void main(String[] args) {
        char[][] board = new char[ROWS][COLUMNS];
        initializeBoard(board);
        printBoard(board);
        boolean isPlayer1Turn = true;
        boolean gameWon = false;

        while (!gameWon && !isBoardFull(board)) {
            int column;
            if (isPlayer1Turn) {
                column = getPlayerMove(PLAYER1);
                if (dropDisc(board, column, PLAYER1)) {
                    isPlayer1Turn = false;
                }
            } else {
                column = getPlayerMove(PLAYER2);
                if (dropDisc(board, column, PLAYER2)) {
                    isPlayer1Turn = true;
                }
            }
            printBoard(board);
            gameWon = checkForWin(board);
        }

        if (gameWon) {
            if (isPlayer1Turn) {
                System.out.println("Player 2 (O) wins!");
            } else {
                System.out.println("Player 1 (X) wins!");
            }
        } else {
            System.out.println("It's a draw!");
        }
    }

    // Initialize the board with empty spaces
    private static void initializeBoard(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Print the game board
    private static void printBoard(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }

    // Get the player's move
    private static int getPlayerMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int column;
        do {
            System.out.println("Player " + player + ", choose a column (0-6): ");
            column = scanner.nextInt();
        } while (column < 0 || column >= COLUMNS);
        return column;
    }

    // Drop the disc into the selected column
    private static boolean dropDisc(char[][] board, int column, char player) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY) {
                board[i][column] = player;
                return true;
            }
        }
        System.out.println("Column is full! Try another one.");
        return false;
    }

    // Check if the board is full (a draw condition)
    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check for a winning condition
    private static boolean checkForWin(char[][] board) {
        // Check horizontal, vertical, and diagonal
        return checkHorizontalWin(board) || checkVerticalWin(board) || checkDiagonalWin(board);
    }

    // Check for a horizontal win
    private static boolean checkHorizontalWin(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] != EMPTY && board[i][j] == board[i][j + 1] &&
                    board[i][j] == board[i][j + 2] && board[i][j] == board[i][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check for a vertical win
    private static boolean checkVerticalWin(char[][] board) {
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] != EMPTY && board[i][j] == board[i + 1][j] &&
                    board[i][j] == board[i + 2][j] && board[i][j] == board[i + 3][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check for a diagonal win (both directions)
    private static boolean checkDiagonalWin(char[][] board) {
        // Check for upward diagonal
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] != EMPTY && board[i][j] == board[i - 1][j + 1] &&
                    board[i][j] == board[i - 2][j + 2] && board[i][j] == board[i - 3][j + 3]) {
                    return true;
                }
            }
        }

        // Check for downward diagonal
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] != EMPTY && board[i][j] == board[i + 1][j + 1] &&
                    board[i][j] == board[i + 2][j + 2] && board[i][j] == board[i + 3][j + 3]) {
                    return true;
                }
            }
        }

        return false;
    }
}

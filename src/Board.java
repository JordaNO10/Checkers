public class Board {
    private String red;
    private String white;
    private String[][] board;

    public Board(int red, int white) {
        this.red = "Red";
        this.white = "White";
        board = new String[8][8];
        setBoard();
    }

    public String getRed() {
        return red;
    }

    public void moveRed(String red) {
        this.red = red;
    }

    public String getWhite() {
        return white;
    }

    public void MoveWhite(String white) {
        this.white = white;
    }

    private void setBoard() {
        // Fix The Board Presentation
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    board[i][j] = "Dark";
                } else {
                    board[i][j] = "Light";
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    board[i][j] = "Black";
                }
            }
        }

        for (int i = 5; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    board[i][j] = "Red";
                }
            }

        }

    }

    public void showTheBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "  ");

            }
            System.out.println("\n");
        }
    }

    public boolean isOccupied(String[][] board) {
        boolean isTaken = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == "Red" || board[i][j] == "Black") {
                    isTaken = true;
                }
            }
        }
        return isTaken;
    }

    public boolean isPieceOnDiagonal(int row, int col, String[][] board) {
        // Check for a piece on the main diagonal
        if (row == col && (board[row][col].equals("Red") || board[row][col].equals("Black"))) {
            return true;
        }

        // Check for a piece on the secondary diagonal
        int n = board.length;
        if (row + col == n - 1 && (board[row][col].equals("Red") || board[row][col].equals("Black"))) {
            return true;
        }
        return false;
    }

    public void eat(int row, int col) {
        if (isOccupied(board)) {
            if (isPieceOnDiagonal(row, col, board)) {
                // Informative message when a piece is on a diagonal
                System.out.println("Cannot eat this piece because it's on a diagonal.");
            } else {
                // Check if there's an opponent's piece diagonally adjacent to the current position
                // Adjust the logic according to your checkers rules
                // If there's an opponent's piece diagonally adjacent and the next square diagonally beyond is empty, proceed with eating logic
                // Otherwise, print a message indicating that the piece cannot be eaten
            }
        } else {
            // Informative message when the board is empty
            System.out.println("Cannot eat any piece because the board is empty.");
        }
    }
}

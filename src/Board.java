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
                if ((i + j) % 2 != 0) {
                    if (i < 3) {
                        board[i][j] = "Red";
                    } else if (i > 4) {
                        board[i][j] = "White";
                    } else {
                        board[i][j] = "Empty";
                    }
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
                if ("Red".equals(board[i][j]) || "White".equals(board[i][j])) {
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
        if (!isOccupied(board)) {
            throw new CheckersException("Cannot eat any piece because the board is empty.");
        }

        if (isPieceOnDiagonal(row, col, board)) {
            throw new CheckersException("Cannot eat this piece because it's on a diagonal.");
        }
    }

    public String getPieceAt(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return null; // Return null for out-of-bounds indices
        }
        String piece = board[row][col];
        if (piece == null) {
            return "Empty"; // Return "Empty" for squares without pieces
        }
        return piece;
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        String piece = board[fromRow][fromCol];
        if (piece == null) {
            throw new IllegalArgumentException("No piece at the specified location.");
        }

        // Check if the destination square is occupied by the same player's piece
        String destinationPiece = board[toRow][toCol];
        if (destinationPiece != null && destinationPiece.equals(piece)) {
            throw new IllegalArgumentException("Destination square is occupied by your own piece.");
        }

        // Check if the destination square is occupied by an opponent's piece
        if (destinationPiece != null && !destinationPiece.equals(piece)) {
            // Handle the capture by removing the opponent's piece from the board
            board[toRow][toCol] = null;
            System.out.println("Captured " + destinationPiece + " at (" + toRow + ", " + toCol + ").");
        }

        // Check if the move is diagonal
        int dx = Math.abs(toCol - fromCol);
        int dy = Math.abs(toRow - fromRow);
        if (dx != dy) {
            throw new IllegalArgumentException("Invalid move: not diagonal.");
        }

        // Move the piece to the destination
        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = null;

        // You can add additional checks and actions based on your game rules here
    }

}
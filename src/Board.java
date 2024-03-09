
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
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				int newRow = row + i;
				int newCol = col + j;
				if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[newRow].length) {
					if (board[newRow][newCol].equals("Red") || board[newRow][newCol].equals("Black")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void eat(int row, int col) {
		if (isOccupied(board) && !isPieceOnDiagonal(row, col, board)) {
			// Give informative massage if piece is on Diagonal
		}
	}

}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class CheckersGame extends Application {
    private int selectedRow = -1;
    private int selectedCol = -1;
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 50;
    private static final double PIECE_SIZE_FACTOR = 0.8; // Adjust factor for circle size
    private static final double PIECE_TRANSLATE_FACTOR = (1 - PIECE_SIZE_FACTOR) / 2; // Adjust factor for circle position

    private Board board; // Create an instance of the Board class
    private boolean isRedTurn = true; // Indicates if it's red player's turn

    @Override
    public void start(Stage primaryStage) {
        board = new Board(8, 8); // Initialize the board with 8 red and 8 white pieces

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 400, 400);

        // Create board squares and pieces
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
                square.setFill((row + col) % 2 == 0 ? Color.FORESTGREEN : Color.DIMGREY);
                final int currentRow = row;
                final int currentCol = col;
                square.setOnMouseClicked(e -> handleSquareClick(currentRow, currentCol));
                root.add(square, col, row);

                // Add pieces to the board based on the Board object
                if ((row + col) % 2 != 0) {
                    String pieceColor = board.getPieceAt(row, col);
                    if (pieceColor != null) {
                        Color pieceFillColor;
                        if (pieceColor.equals("Red")) {
                            pieceFillColor = Color.RED; // Red pieces on lines 0, 1, and 2
                        } else if (pieceColor.equals("White")) {
                            pieceFillColor = Color.WHITE; // White pieces on lines 5, 6, and 7
                        } else {
                            continue;
                        }
                        Circle piece = new Circle(SQUARE_SIZE / 2 * PIECE_SIZE_FACTOR);
                        piece.setFill(pieceFillColor);
                        piece.setTranslateX(SQUARE_SIZE * PIECE_TRANSLATE_FACTOR);
                        piece.setTranslateY(SQUARE_SIZE * PIECE_TRANSLATE_FACTOR);
                        root.add(piece, col, row);
                    }
                }
            }
        }

        primaryStage.setTitle("Checkers Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSquareClick(int row, int col) {
        String pieceColor = board.getPieceAt(row, col);
        if (pieceColor != null && !"Empty".equals(pieceColor)) {
            if ((isRedTurn && "Red".equals(pieceColor)) || (!isRedTurn && "White".equals(pieceColor))) {
                selectedRow = row;
                selectedCol = col;
                System.out.println("Selected piece at (" + row + ", " + col + ")");
            } else {
                System.out.println("It's not your turn to move this piece.");
            }
        } else {
            System.out.println("No piece at (" + row + ", " + col + ") or it's an empty square.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

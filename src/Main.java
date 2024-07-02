import java.util.Scanner;

public class Main {
    private Board board;
    private Tetromino currentPiece;
    private boolean isRunning;

    public Main() {
        board = new Board(20, 10); // Typical Tetris board size: 20 rows x 10 columns
        isRunning = true;
        spawnNewPiece();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            board.printBoard(currentPiece);
            System.out.println("Enter command (a: left, d: right, s: down, w: rotate, q: quit):");
            char command = scanner.next().charAt(0);

            switch (command) {
                case 'a':
                    moveLeft();
                    break;
                case 'd':
                    moveRight();
                    break;
                case 's':
                    moveDown();
                    break;
                case 'w':
                    rotatePiece();
                    break;
                case 'q':
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
            if (!moveDown()) {
                board.placePiece(currentPiece);
                board.clearFullLines();
                spawnNewPiece();
            }
        }
        scanner.close();
        System.out.println("Game Over");
    }

    private void spawnNewPiece() {
        currentPiece = Tetromino.getRandomPiece();
        currentPiece.setPosition(new Position(0, board.getWidth() / 2 - 1));
        if (!board.canPlacePiece(currentPiece)) {
            isRunning = false; // Game over condition
        }
    }

    private void moveLeft() {
        currentPiece.moveLeft();
        if (!board.canPlacePiece(currentPiece)) {
            currentPiece.moveRight();
        }
    }

    private void moveRight() {
        currentPiece.moveRight();
        if (!board.canPlacePiece(currentPiece)) {
            currentPiece.moveLeft();
        }
    }

    private boolean moveDown() {
        currentPiece.moveDown();
        if (!board.canPlacePiece(currentPiece)) {
            currentPiece.moveUp();
            return false;
        }
        return true;
    }

    private void rotatePiece() {
        currentPiece.rotate();
        if (!board.canPlacePiece(currentPiece)) {
            currentPiece.undoRotate();
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.start();
    }
}

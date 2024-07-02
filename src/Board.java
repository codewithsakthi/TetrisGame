public class Board {
    private int rows;
    private int cols;
    private char[][] board;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '.';
            }
        }
    }

    public int getWidth() {
        return cols;
    }

    public boolean canPlacePiece(Tetromino piece) {
        for (Position pos : piece.getBlocks()) {
            int x = pos.getX();
            int y = pos.getY();
            if (x < 0 || x >= rows || y < 0 || y >= cols || board[x][y] != '.') {
                return false;
            }
        }
        return true;
    }

    public void placePiece(Tetromino piece) {
        for (Position pos : piece.getBlocks()) {
            board[pos.getX()][pos.getY()] = '#';
        }
    }

    public void clearFullLines() {
        for (int i = 0; i < rows; i++) {
            boolean isFullLine = true;
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '.') {
                    isFullLine = false;
                    break;
                }
            }
            if (isFullLine) {
                clearLine(i);
            }
        }
    }

    private void clearLine(int line) {
        for (int i = line; i > 0; i--) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = board[i - 1][j];
            }
        }
        for (int j = 0; j < cols; j++) {
            board[0][j] = '.';
        }
    }

    public void printBoard(Tetromino currentPiece) {
        char[][] tempBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tempBoard[i][j] = board[i][j];
            }
        }
        for (Position pos : currentPiece.getBlocks()) {
            tempBoard[pos.getX()][pos.getY()] = '#';
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(tempBoard[i][j]);
            }
            System.out.println();
        }
    }
}

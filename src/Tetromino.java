import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tetromino {
    private static final char[][][] SHAPES = {
            {{'#', '#', '#', '#'}}, // I
            {{'#', '#'}, {'#', '#'}}, // O
            {{'#', '#', '#'}, {' ', '#', ' '}}, // T
            {{'#', '#', ' '}, {' ', '#', '#'}}, // S
            {{' ', '#', '#'}, {'#', '#', ' '}}, // Z
            {{'#', '#', '#'}, {'#', ' ', ' '}}, // J
            {{'#', '#', '#'}, {' ', ' ', '#'}}  // L
    };

    private char[][] shape;
    private Position position;

    public Tetromino(char[][] shape) {
        this.shape = shape;
    }

    public static Tetromino getRandomPiece() {
        Random rand = new Random();
        int idx = rand.nextInt(SHAPES.length);
        return new Tetromino(SHAPES[idx]);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void moveLeft() {
        position.setY(position.getY() - 1);
    }

    public void moveRight() {
        position.setY(position.getY() + 1);
    }

    public void moveDown() {
        position.setX(position.getX() + 1);
    }

    public void moveUp() {
        position.setX(position.getX() - 1);
    }

    public void rotate() {
        int originalRows = shape.length;
        int originalCols = shape[0].length;
        char[][] rotated = new char[originalCols][originalRows];

        for (int i = 0; i < originalRows; i++) {
            for (int j = 0; j < originalCols; j++) {
                rotated[j][originalRows - 1 - i] = shape[i][j];
            }
        }

        shape = rotated;
    }

    public void undoRotate() {
        int originalRows = shape.length;
        int originalCols = shape[0].length;
        char[][] rotated = new char[originalCols][originalRows];

        for (int i = 0; i < originalRows; i++) {
            for (int j = 0; j < originalCols; j++) {
                rotated[originalCols - 1 - j][i] = shape[i][j];
            }
        }

        shape = rotated;
    }


    public List<Position> getBlocks() {
        List<Position> blocks = new ArrayList<>();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == '#') {
                    blocks.add(new Position(position.getX() + i, position.getY() + j));
                }
            }
        }
        return blocks;
    }
}

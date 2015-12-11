import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

public class GameBoard {
    private int rows;
    private int columns;
    private BitSet values;

    public GameBoard(int rows, int columns) throws IOException {
        this.rows = rows;
        this.columns = columns;
        this.values = new BitSet(rows * columns);
    }

    public GameBoard(GameBoard other) {
        this.rows = other.rows;
        this.columns = other.columns;
        this.values = (BitSet)other.values.clone();
    }

    public boolean getValue(int x, int y) {
        return values.get(columns * x + y);
    }

    public void setValue(int x, int y, boolean isAlive) {
        values.set(columns * x + y, isAlive);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean[] getNeighbourValues(int i, int j) {
        if (!areValid(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        ArrayList<Boolean> temp = new ArrayList<Boolean>();
        for (int row = i - 1; row <= i + 1; row++) {
            for (int column = j - 1; column <= j + 1; column++) {
                if (areValid(row, column) && !(row == i && column == j)) {
                    temp.add(getValue(row, column));
                }
            }
        }

        boolean[] result = new boolean[temp.size()];
        for (i = 0; i < result.length; i++) {
            result[i] = temp.get(i);
        }

        return result;
    }

    private boolean areValid(int i, int j) {
        return i >= 0 && j >= 0 && i < rows && j < columns;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (getValue(i, j)) {
                    builder.append("*");
                } else {
                    builder.append(".");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}

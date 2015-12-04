import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameBoard {
    private int rows;
    private int columns;
    private boolean values[][];

    public GameBoard(String fileName) throws IOException {
        loadStartState(fileName);
    }

    public GameBoard(GameBoard other) {
        this.rows = other.rows;
        this.columns = other.columns;
        this.values = new boolean[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                this.values[i][j] = other.values[i][j];
            }
        }
    }

    private void loadStartState(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            String[] parts = line.split(" ");
            rows = Integer.parseInt(parts[0]);
            columns = Integer.parseInt(parts[1]);
            values = new boolean[rows][columns];

            for (int i = 0; i < rows; i++) {
                line = bufferedReader.readLine();
                for (int j = 0; j < columns; j++) {
                    boolean isAlive = line.charAt(j) != '.';
                    setValue(i, j, isAlive);
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    public boolean getValue(int x, int y) {
        return values[x][y];
    }

    public void setValue(int x, int y, boolean isAlive) {
        values[x][y] = isAlive;
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

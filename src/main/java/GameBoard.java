import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameBoard {
    int rows;
    int columns;
    boolean values[][];

    public GameBoard(String fileName) throws IOException {
        loadStartState(fileName);
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
                    values[i][j] = (line.charAt(j) != '.');
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
}

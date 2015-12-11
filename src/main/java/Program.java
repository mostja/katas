
import java.io.*;
import java.util.BitSet;

public class Program {
    public static void main(String[] args) throws IOException {
        String fileName = "src\\main\\resources\\file.txt";
        GameBoard state = loadStartState(fileName);
        state = Game.getNextGeneration(state);
        System.out.print(state.toString());
    }
    private static GameBoard loadStartState(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        GameBoard gameBoard = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            String[] parts = line.split(" ");
            int rows = Integer.parseInt(parts[0]);
            int columns = Integer.parseInt(parts[1]);
            gameBoard = new GameBoard(rows, columns);

            for (int i = 0; i < rows; i++) {
                line = bufferedReader.readLine();
                for (int j = 0; j < columns; j++) {
                    boolean isAlive = line.charAt(j) != '.';
                    gameBoard.setValue(i, j, isAlive);
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return gameBoard;
    }
}

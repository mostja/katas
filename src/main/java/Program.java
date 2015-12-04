
import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException {
        String fileName = "src\\main\\resources\\file.txt";
        GameBoard state = new GameBoard(fileName);
        state = Game.getNextGeneration(state);
        System.out.print(state.toString());

    }
}

public class Game {
    public static GameBoard getNextGeneration(GameBoard previousState) {
        GameBoard nextState = new GameBoard(previousState);
        for (int i = 0; i < previousState.getRows(); ++i) {
            for (int j = 0; j < previousState.getColumns(); ++j) {
                boolean[] neighbourValues = previousState.getNeighbourValues(i, j);
                int numberOfAlive = getNumberOfAliveNeighbours(neighbourValues);
                if (numberOfAlive < 2 || numberOfAlive > 3) {
                    nextState.setValue(i, j, false);
                }
                else if (numberOfAlive == 3) {
                    nextState.setValue(i, j, true);
                }
            }
        }
        return nextState;
    }

    private static int getNumberOfAliveNeighbours(boolean[] neighbourValues) {
        int count = 0;
        for (int i = 0; i < neighbourValues.length; i++) {
            if (neighbourValues[i]) {
                count++;
            }
        }
        return count;
    }
}

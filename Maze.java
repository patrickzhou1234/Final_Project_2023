import java.util.ArrayList;

public class Maze {
    private char[][] maze;
    private int startRow, startCol;
    private ArrayList<String> routes = new ArrayList<String>();

    public Maze() {
        maze = new char[0][0];
        startRow = 0;
        startCol = 0;
    }

    public Maze(char[][] maze, int startRow, int startCol) {
        this.maze = maze;
        this.startRow = startRow;
        this.startCol = startCol;
    }

    public void calculateRoute() {
        calculateRoute("", startRow, startCol, new ArrayList<String>());
    }

    private void calculateRoute(String currentRoute, int currentRow, int currentCol, ArrayList<String> usedCoords) {
        usedCoords.add(currentRow + " " + currentCol);
        ArrayList<String> newUsedCoords;
        if (currentRow == 0 || currentCol == 0 || currentRow == maze.length - 1 || currentCol == maze[0].length - 1) {
            routes.add(currentRoute);
            return;
        }
        if (maze[currentRow][currentCol - 1] == ' ') {
            if (!coordUsed((currentRow) + " " + (currentCol - 1), usedCoords)) {
                newUsedCoords = new ArrayList<String>(usedCoords);
                calculateRoute(currentRoute + 'W', currentRow, currentCol - 1, newUsedCoords);
            }
        }
        if (maze[currentRow - 1][currentCol] == ' ') {
            if (!coordUsed((currentRow - 1) + " " + (currentCol), usedCoords)) {
                newUsedCoords = new ArrayList<String>(usedCoords);
                calculateRoute(currentRoute + 'N', currentRow - 1, currentCol, newUsedCoords);
            }
        }
        if (maze[currentRow][currentCol + 1] == ' ') {
            if (!coordUsed((currentRow) + " " + (currentCol + 1), usedCoords)) {
                newUsedCoords = new ArrayList<String>(usedCoords);
                calculateRoute(currentRoute + 'E', currentRow, currentCol + 1, newUsedCoords);
            }
        }
        if (maze[currentRow + 1][currentCol] == ' ') {
            if (!coordUsed((currentRow + 1) + " " + (currentCol), usedCoords)) {
                newUsedCoords = new ArrayList<String>(usedCoords);
                calculateRoute(currentRoute + 'S', currentRow + 1, currentCol, newUsedCoords);
            }
        }
        return;
    }

    public boolean coordUsed(String target, ArrayList<String> usedCoords) {
        for (String coords : usedCoords) {
            if (coords.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return routes.toString();
    }

    public ArrayList<String> getRoutes() {
        return routes;
    }

    public String getShortestRoute() {
        int i, min = Integer.MAX_VALUE;
        String minRoute = "";
        for (i = 0; i < routes.size(); i++) {
            if (routes.get(i).length() < min) {
                min = routes.get(i).length();
                minRoute = routes.get(i);
            }
        }
        return minRoute;
    }
}
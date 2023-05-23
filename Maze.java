import java.util.ArrayList;

public class Maze {
    private char[][] maze;
    private int startRow, startCol;
    private ArrayList<String> routes = new ArrayList<String>();

    public Maze() {
        maze=new char[0][0];
        startRow=0;
        startCol=0;
    }

    public Maze(char[][] maze, int startRow, int startCol) {
        this.maze = maze;
        this.startRow = startRow;
        this.startCol = startCol;
    }

    public void getRoute() {
        getRoute("", startRow, startCol);
    }

    public void getRoute(String currentRoute, int currentRow, int currentCol) {
        maze[currentRow][currentCol] = 'X';
        if (currentRow == 0 || currentCol == 0 || currentRow == maze.length - 1 || currentCol == maze[0].length - 1) {
            routes.add(currentRoute);
            return;
        }
        if (maze[currentRow][currentCol - 1] == ' ') {
            getRoute(currentRoute+'W', currentRow, currentCol - 1);
        }
        if (maze[currentRow - 1][currentCol] == ' ') {
            getRoute(currentRoute+'N', currentRow - 1, currentCol);
        }
        if (maze[currentRow][currentCol + 1] == ' ') {
            getRoute(currentRoute+'E', currentRow, currentCol + 1);
        }
        if (maze[currentRow + 1][currentCol] == ' ') {
            getRoute(currentRoute+'S', currentRow + 1, currentCol);
        }
    }

    public String toString() {
        return routes.toString();
    }
}

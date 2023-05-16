import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Initializing Scanner Object
        Scanner in = new Scanner(System.in);

        // Getting the file name
        System.out.println("What is the file name for the maze?");
        String filename = in.nextLine();
        File f = new File(filename);
        Scanner fileScanner = new Scanner(f);

        // Declaring vars
        int i, j, rows, cols;
        String line;
        char[][] maze;

        // Initializing 2D array vars
        rows = fileScanner.nextInt();
        cols = fileScanner.nextInt();
        maze = new char[rows][cols];
        fileScanner.nextLine();

        for (i = 0; i < rows; i++) {
            line = fileScanner.nextLine();
            for (j = 0; j < cols; j++) {
                maze[i][j] = line.charAt(j);
            }
        }

        // Printing the maze
        System.out.print("The maze route is: " + getRoute(maze, "", rows, cols));
    }

    public static String getRoute(char maze[][], String currentRoute, int currentRow, int currentCol) {
        if (currentRow == 0 || currentCol == 0 || currentRow == maze.length - 1 || currentCol == maze[0].length - 1) {
            return currentRoute;
        }
        for (int i = 0; i < 4; i++) {
            if (i == 0 && maze[currentRow][currentCol - 1] == ' ') {
                currentRoute += "W";
                return getRoute(maze, currentRoute, currentRow, currentCol - 1);
            }
            if (i == 1 && maze[currentRow - 1][currentCol] == ' ') {
                currentRoute += "N";
                return getRoute(maze, currentRoute, currentRow - 1, currentCol);
            }
            if (i == 2 && maze[currentRow][currentCol + 1] == ' ') {
                currentRoute += "E";
                return getRoute(maze, currentRoute, currentRow, currentCol + 1);
            } else if (maze[currentRow + 1][currentCol] == ' ') {
                currentRoute += "S";
                return getRoute(maze, currentRoute, currentRow + 1, currentCol);
            }
        }
        return "";
    }
}
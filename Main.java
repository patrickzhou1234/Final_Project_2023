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
        System.out.print("The maze route is: ");
        String route = "";
        while (route.equals("")) {
            route = getRoute(maze, "", 4, 0);
        }
        System.out.print(route);
        System.out.print("Reached");
    }

    public static String getRoute(char maze[][], String currentRoute, int currentRow, int currentCol) {
        if ((currentRow == 0 || currentCol == 0 || currentRow == maze.length - 1 || currentCol == maze[0].length - 1)
                && currentRoute.length() != 0) {
            return currentRoute;
        }
        char previousMove;
        if (currentRoute.length() != 0) {
            previousMove = currentRoute.charAt(currentRoute.length() - 1);
        } else {
            previousMove = ' ';
        }
        int i = 0 + (int) (Math.random() * ((3 - 0) + 1));
        if (i == 0 && currentCol != 0 && maze[currentRow][currentCol - 1] == ' ' && previousMove != 'E') {
            currentRoute += "W";
            return getRoute(maze, currentRoute, currentRow, currentCol - 1);
        }
        if (i == 1 && currentRow != 0 && maze[currentRow - 1][currentCol] == ' ' && previousMove != 'S') {
            currentRoute += "N";
            return getRoute(maze, currentRoute, currentRow - 1, currentCol);
        }
        if (i == 2 && currentCol != maze.length - 1 && maze[currentRow][currentCol + 1] == ' '
                && previousMove != 'W') {
            currentRoute += "E";
            return getRoute(maze, currentRoute, currentRow, currentCol + 1);
        }
        if (i == 3 && currentRow != maze[0].length - 1 && maze[currentRow + 1][currentCol] == ' '
                && previousMove != 'N') {
            currentRoute += "S";
            return getRoute(maze, currentRoute, currentRow + 1, currentCol);
        }
        return "";
    }
}
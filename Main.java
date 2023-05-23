import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static ArrayList<String> routes = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException {
        // Initializing Scanner Object
        Scanner in = new Scanner(System.in);

        // Getting the file name
        System.out.print("What is the file name for the maze? ");
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
        System.out.print("Input the starting row: ");
        int startRow = in.nextInt();
        System.out.print("Input the starting column: ");
        int startCol = in.nextInt();
        // Printing the maze
        System.out.print("The maze route is: ");
        getRoute(maze, "", startRow, startCol, startRow, startCol);
        System.out.print(routes);
    }

    public static void getRoute(char maze[][], String currentRoute, int currentRow, int currentCol, int origRow,
            int origCol) {
        maze[currentRow][currentCol] = 'X';
        if (currentRow == 0 || currentCol == 0 || currentRow == maze.length - 1 || currentCol == maze[0].length - 1) {
            routes.add(currentRoute);
            return;
        }
        if (maze[currentRow][currentCol - 1] == ' ') {
            getRoute(maze, currentRoute+'W', currentRow, currentCol - 1, origRow, origCol);
        }
        if (maze[currentRow - 1][currentCol] == ' ') {
            getRoute(maze, currentRoute+'N', currentRow - 1, currentCol, origRow, origCol);
        }
        if (maze[currentRow][currentCol + 1] == ' ') {
            getRoute(maze, currentRoute+'E', currentRow, currentCol + 1, origRow, origCol);
        }
        if (maze[currentRow + 1][currentCol] == ' ') {
            getRoute(maze, currentRoute+'S', currentRow + 1, currentCol, origRow, origCol);
        }
    }
}
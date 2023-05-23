import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
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
        Maze mz = new Maze(maze, startRow, startCol);
        // Printing the maze
        System.out.print("The maze route is: ");
        mz.getRoute();
        System.out.print(mz);
        MazeGraphics g = new MazeGraphics(maze);
        g.drawMaze();
    }
}
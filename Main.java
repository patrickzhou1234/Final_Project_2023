import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> routes, shortestRoutes;

        // Initializing 2D array vars
        rows = fileScanner.nextInt();
        cols = fileScanner.nextInt();
        maze = new char[rows][cols];

        fileScanner.nextLine();

        // Adding all characters from the maze file into a 2D array
        for (i = 0; i < rows; i++) {
            line = fileScanner.nextLine();
            for (j = 0; j < cols; j++) {
                maze[i][j] = line.charAt(j);
            }
        }

        // Inputting starting row and column
        System.out.print("Input the starting row: ");
        int startRow = in.nextInt();
        System.out.print("Input the starting column: ");
        int startCol = in.nextInt();

        // Initializing Maze object
        Maze mz = new Maze(maze, startRow, startCol);

        // Calculating the routes
        mz.calculateRoutes();
        routes = mz.getRoutes();
        shortestRoutes = mz.getShortestRoute();

        // Outputting all the maze routes
        System.out.print("The possible maze routes are: \n");
        if(routes.size() < 4){
            for(i = 0; i < routes.size(); i++){
                System.out.printf("%40s", routes.get(i) + " ");
            }
        }
        for(i = 0; i < routes.size()/4; i++){
            for(j = 0; j < 4; j++){
                System.out.printf("%40s", routes.get(i*4 + j) + " ");
            }
            System.out.println();
        }
        
        // Outputting the shortest maze routes
        System.out.print("\n\nThe shortest maze routes are: \n");
        if(shortestRoutes.size() < 4){
            for(i = 0; i < shortestRoutes.size(); i++){
                System.out.printf("%40s", shortestRoutes.get(i) + " ");
            }
        }
        for(i = 0; i < shortestRoutes.size()/4; i++){
            for(j = 0; j < 4; j++){
                System.out.printf("%40s", shortestRoutes.get(i*4 + j) + " ");
            }
            System.out.println();
        }

        // Initializing MazeGraphics objects
        MazeGraphics allRoutesGraphics = new MazeGraphics(maze, routes, startRow, startCol, "All Routes", 0);
        MazeGraphics shortestRoutesGraphics = new MazeGraphics(maze, shortestRoutes, startRow, startCol, "Shortest Routes", (maze[0].length+1)*50);

        // Drawaing the maze using graphics
        allRoutesGraphics.drawMaze();
        shortestRoutesGraphics.drawMaze();

        // Closing Scanner objects
        in.close();
        fileScanner.close();
    }
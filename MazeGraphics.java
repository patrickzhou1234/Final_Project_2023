import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.util.ArrayList;

public class MazeGraphics extends JPanel {
    // instance variables
    private char maze[][];
    private ArrayList<String> routes;
    private int startRow, startCol, xStart;
    private String message;

    /**
     * MazeGraphics Constructor which initializes all the instance variables
     * @param maze Array containing the maze
     * @param routes ArrayList containing all the routes
     * @param startRow Row of the starting point
     * @param startCol Column of the starting point
     * @param message Message to display on the title of the window
     * @param xStart The first x coordinate to be filled in (first y coordinate is 0)
     */
    public MazeGraphics(char[][] maze, ArrayList<String> routes, int startRow, int startCol, String message, int xStart) {
        this.maze = maze;
        this.routes = routes;
        this.startRow = startRow;
        this.startCol = startCol;
        this.message = message;
        this.xStart = xStart;
    }

    /**
     * Sets up the frame to be filled in later
     * 
     * @return none
     */
    public void drawMaze() {
        // Initializing vars
        JFrame frame = new JFrame("Maze GUI - "+message);
        frame.setSize((maze[0].length + 1) * 50, (maze.length + 1) * 50); // Sets the size of the frame to fit the size of the array
        JPanel gPanel = new JPanel();
        gPanel.add(this);
        ScrollPane scroller = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED); // Scroller so we can scroll through the mazes
        scroller.add(gPanel);

        // Sets the size of the frame based on the dimensions of the maze
        setPreferredSize(new Dimension((maze[0].length) * 50, (maze.length + 1) * 50*routes.size()));

        // Sets up the frame
        frame.add(scroller);
        frame.setLocation(xStart, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Paints all the components of the maze 2D array onto the frame
     * 
     * @param g The graphics involved with the paintComponent() method of the JPanel class
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Initializing vars
        int i, j, m;
        int tmpStartRow, tmpStartCol;
        String route;

        // Looping through all routes
        for (m=0;m<routes.size();m++) {
            route = routes.get(m);

            // Removing all of the Xs from the maze array
            for (i = 0; i < maze.length; i++) {
                for (j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == 'X') {
                        maze[i][j] = ' ';
                    }
                }
            }

            tmpStartRow = startRow;
            tmpStartCol = startCol;

            // Goes through the route and adds Xs to each of the points on the maze that have been visited for this route
            for (i = 0; i < route.length(); i++) {
                if (route.charAt(i) == 'N') {
                    startRow--;
                } else if (route.charAt(i) == 'S') {
                    startRow++;
                } else if (route.charAt(i) == 'E') {
                    startCol++;
                } else if (route.charAt(i) == 'W') {
                    startCol--;
                }
                maze[startRow][startCol] = 'X';
            }

            startRow = tmpStartRow;
            startCol = tmpStartCol;

            // Changing the color of the paint component according to the position on the maze
            for (i = 0; i < maze.length; i++) {
                for (j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == '|') {
                        g.setColor(Color.BLACK); // A wall
                    } else if (maze[i][j] == 'X') {
                        g.setColor(Color.GREEN); // A place that is visited during the maze
                    } else if (i==startRow && j==startCol) {
                        g.setColor(Color.RED); // The starting point
                    } else {
                        g.setColor(Color.WHITE); // Not visited and not a wall
                    }

                    // For filling in the color for this component:
                    // Each component is 50 pixels long/wide
                    // For each route, the y coordinate is increased by the length of the maze.
                    // Since each coordinate is 50 pixels, the x coordinate will be j * 50 while
                    // the y coordinate will be m*(maze.length+1)*50+i * 50.
                    g.fillRect(j * 50, m*(maze.length+1)*50+i * 50, 50, 50); 
                }
            }
        }
    }
}

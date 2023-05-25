import java.util.ArrayList;

public class Maze {

    // Instance Variables
    private char[][] maze;
    private int startRow, startCol;
    private ArrayList<String> routes;

    /**
     * Default Constructor of the Maze class which initializes all the instance
     * variables to a default value
     * 
     * @return none
     */
    public Maze() {
        maze = new char[0][0];
        startRow = 0;
        startCol = 0;
        routes = new ArrayList<String>();
    }

    /**
     * Constructor of the Maze class which initializes the instance variables based
     * on the parameters
     * 
     * @param maze     the maze contained in an ArrayList
     * @param startRow The row number of the starting point of the maze
     * @param startCol The column number of the starting point of the maze
     * @return none
     */
    public Maze(char[][] maze, int startRow, int startCol) {
        this.maze = maze;
        this.startRow = startRow;
        this.startCol = startCol;
        routes = new ArrayList<String>();
    }

    /**
     * Calls the overriding calculateRoutes() method with the default values
     * 
     * @return none
     */
    public void calculateRoutes() {
        calculateRoutes("", startRow, startCol, new ArrayList<String>());
    }

    /**
     * Finds all possible routes to get out of the maze from the starting point,
     * which
     * is the value of currentRow and currentCol in the first call of the method.
     * Each route is a string which can contain "N", "S", "E", or "W" which would be
     * the different
     * directions you from the starting point. All of these routes are stored in the
     * routes ArrayList.
     * 
     * @param currentRoute The current route from the starting point, which is what
     *                     you have moved so far
     * @param currentRow   The row of your current position
     * @param currentCol   The column of your current position
     * @param usedCoords   ArrayList consisting of coords which you have already
     *                     visited. (Each coord is row + " " + column)
     * 
     * @return none
     */
    private void calculateRoutes(String currentRoute, int currentRow, int currentCol, ArrayList<String> usedCoords) {
        usedCoords.add(currentRow + " " + currentCol); // Add your current coords to the usedCoords ArrayList

        ArrayList<String> newUsedCoords;

        // Checks if you are at the edge of the maze (which means you have reached an
        // exit)
        if (currentRow == 0 || currentCol == 0 || currentRow == maze.length - 1 || currentCol == maze[0].length - 1) {
            routes.add(currentRoute);
            return;
        }

        // Checks for each point around your current coordinates to see if it is
        // available, and calls the method again with those coords if that point is
        // available
        if (maze[currentRow][currentCol - 1] == ' ') {
            if (!coordUsed((currentRow) + " " + (currentCol - 1), usedCoords)) {
                newUsedCoords = new ArrayList<String>(usedCoords); // Copies usedCoords ArrayList in a new ArrayList to
                                                                   // make sure the usedCoords is only updated for this
                                                                   // call to currentRoute()
                calculateRoutes(currentRoute + 'W', currentRow, currentCol - 1, newUsedCoords);
            }
        }
        if (maze[currentRow - 1][currentCol] == ' ') {
            if (!coordUsed((currentRow - 1) + " " + (currentCol), usedCoords)) {
                newUsedCoords = new ArrayList<String>(usedCoords);
                calculateRoutes(currentRoute + 'N', currentRow - 1, currentCol, newUsedCoords);
            }
        }
        if (maze[currentRow][currentCol + 1] == ' ') {
            if (!coordUsed((currentRow) + " " + (currentCol + 1), usedCoords)) {
                newUsedCoords = new ArrayList<String>(usedCoords);
                calculateRoutes(currentRoute + 'E', currentRow, currentCol + 1, newUsedCoords);
            }
        }
        if (maze[currentRow + 1][currentCol] == ' ') {
            if (!coordUsed((currentRow + 1) + " " + (currentCol), usedCoords)) {
                newUsedCoords = new ArrayList<String>(usedCoords);
                calculateRoutes(currentRoute + 'S', currentRow + 1, currentCol, newUsedCoords);
            }
        }
        return;
    }

    /**
     * Checks if target is in the usedCoords ArrayList
     * 
     * @param target
     * @param usedCoords
     * @return whether target is in usedCoords
     */
    public boolean coordUsed(String target, ArrayList<String> usedCoords) {
        for (String coords : usedCoords) { // Linear search, since it is not sorted in ascending order, so we can't do
                                           // binary search
            if (coords.equals(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Overrides toString() method of Object Class to display all the possible
     * routes
     * 
     * @return none
     */
    public String toString() {
        return routes.toString(); // Cannot just do "routes" as it gives a compilation error
    }

    /**
     * Returns the ArrayList containing all the routes
     * 
     * 
     * @return ArrayList containing all the routes
     */
    public ArrayList<String> getRoutes() {
        return routes;
    }

    /**
     * Returns the shortest route out of all the routes in the routes ArrayList
     * 
     * @return ArrayList containing all the shortest routes
     */
    public ArrayList<String> getShortestRoute() {
        int i;
        int min = routes.get(0).length();
        ArrayList<String> minRoutes = new ArrayList<String>();

        // Loops through each route in the routes ArrayList and checks if its length is
        // minimum than the current minimum amount
        if (routes.size() >= 1) {
            for (i = 1; i < routes.size(); i++) {
                if (routes.get(i).length() < min) {
                    min = routes.get(i).length(); // Replaces min with the new minimum amount
                }
            }
        }

        // Adds all routes minRoutes that have a length equal to the minimum length
        for (i = 0; i < routes.size(); i++) {
            if (routes.get(i).length() == min) {
                minRoutes.add(routes.get(i));
            }
        }
        return minRoutes;
    }
}
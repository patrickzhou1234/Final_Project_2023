import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.util.ArrayList;

public class MazeGraphics extends JPanel {
    private char maze[][];
    private ArrayList<String> routes;
    private int startRow, startCol, xStart;
    private String message;

    public MazeGraphics(char[][] maze, ArrayList<String> routes, int startRow, int startCol, String message, int xStart) {
        this.maze = maze;
        this.routes = routes;
        this.startRow = startRow;
        this.startCol = startCol;
        this.message = message;
        this.xStart = xStart;
    }

    public void drawMaze() {
        JFrame frame = new JFrame("Maze GUI - "+message);
        frame.setSize((maze[0].length + 1) * 50, (maze.length + 1) * 50);
        JPanel gPanel = new JPanel();
        gPanel.add(this);
        setPreferredSize(new Dimension((maze[0].length) * 50, (maze.length + 1) * 50*routes.size()));
        ScrollPane scroller = new ScrollPane();
        scroller = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scroller.add(gPanel);
        frame.add(scroller);
        frame.setLocation(xStart, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i, j, m;
        String route;
        for (m=0;m<routes.size();m++) {
            route = routes.get(m);
            for (i = 0; i < maze.length; i++) {
                for (j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == 'X') {
                        maze[i][j] = ' ';
                    }
                }
            }
            int tmpStartRow = startRow, tmpStartCol = startCol;
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
            for (i = 0; i < maze.length; i++) {
                for (j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == '|') {
                        g.setColor(Color.BLACK);
                    } else if (maze[i][j] == 'X') {
                        g.setColor(Color.GREEN);
                    } else if (i==startRow && j==startCol) {
                        g.setColor(Color.RED);
                    } else {
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(j * 50, m*(maze.length+1)*50+i * 50, 50, 50);
                }
            }
        }
    }
}

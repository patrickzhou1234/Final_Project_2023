import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

public class MazeGraphics extends JPanel {
    private char maze[][];
    private String route;
    private int startRow, startCol, windowCt;

    public MazeGraphics(char[][] maze, String route, int startRow, int startCol, int windowCt) {
        this.maze = maze;
        this.route = route;
        this.startRow = startRow;
        this.startCol = startCol;
        this.windowCt = windowCt;
    }

    public void drawMaze() {
        JFrame frame = new JFrame("Maze GUI #" + (windowCt + 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((maze[0].length + 1) * 50, (maze.length + 1) * 50);
        JPanel gPanel = new JPanel();
        gPanel.add(this);
        setPreferredSize(new Dimension((maze[0].length) * 50, (maze.length) * 50));
        frame.add(gPanel);
        frame.setLocation(maze[0].length * 50 * windowCt, 0);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i, j;
        for (i = 0; i < maze.length; i++) {
            for (j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'X') {
                    maze[i][j] = ' ';
                }
            }
        }
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
        for (i = 0; i < maze.length; i++) {
            for (j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == '|') {
                    g.setColor(Color.BLACK);
                } else if (maze[i][j] == 'X') {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * 50, i * 50, 50, 50);
            }
        }
    }
}

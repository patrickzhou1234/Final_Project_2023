import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class MazeGraphics extends JPanel {
    private char maze[][];
    public MazeGraphics(char[][] maze) {
        this.maze=maze;
    }

    public void drawMaze() {
        JFrame frame = new JFrame("Maze GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        JPanel gPanel = new JPanel();
        gPanel.add(this);
        frame.add(gPanel);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i, j;
        for (i=0;i<maze.length;i++) {
            for (j=0;j<maze[0].length;j++) {
                if (maze[i][j]=='|') {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i*500, j*500, 500, 500);
            }
        }
    }
}

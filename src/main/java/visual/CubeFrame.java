package visual;

import main.Cube;
import main.ElementColor;

import javax.swing.*;
import java.awt.*;

public class CubeFrame extends JFrame {
    private Cube cube;

    public CubeFrame(Cube cube) {
        super("Unfolded cube 2X2");
        this.cube = cube;
        setResizable(false);
        repaint();
        setVisible(true);
        setSize(new Dimension(301 + getInsets().left + getInsets().right,
                401 + getInsets().top + getInsets().bottom));
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawCube(g);
    }

    private void drawCube(Graphics g) {
        drawSide(cube.getUp(), 100, 0, g);
        drawSide(cube.getLeft(), 0, 100, g);
        drawSide(cube.getFront(), 100, 100, g);
        drawSide(cube.getRight(), 200, 100, g);
        drawSide(cube.getDown(), 100, 200, g);
        drawSide(cube.getBack(), 100, 300, g);
    }

    private void drawSide(ElementColor[] side, int x, int y, Graphics g) {
        x += getInsets().left;
        y += getInsets().top;
        for (int i = 0; i < 4; i++) {
            switch (side[i]) {
                case WHITE:
                    g.setColor(Color.white);
                    break;
                case GREEN:
                    g.setColor(Color.green);
                    break;
                case RED:
                    g.setColor(Color.red);
                    break;
                case BLUE:
                    g.setColor(Color.blue);
                    break;
                case ORANGE:
                    g.setColor(Color.orange);
                    break;
                case YELLOW:
                    g.setColor(Color.yellow);
                    break;
            }
            g.fillRect(x + (i % 2) * 50, y + (i / 2) * 50, 50, 50);
            g.setColor(Color.black);
            g.drawRect(x + (i % 2) * 50, y + (i / 2) * 50, 50, 50);
        }
    }
}

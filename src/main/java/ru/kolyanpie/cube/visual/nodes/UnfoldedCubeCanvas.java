package ru.kolyanpie.cube.visual.nodes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.kolyanpie.cube.core.Cube;
import ru.kolyanpie.cube.core.ElementColor;

public class UnfoldedCubeCanvas extends Canvas {

    public void drawCube(Cube cube) {
        drawCube(cube, getGraphicsContext2D());
    }

    private void drawCube(Cube cube, GraphicsContext context) {
        drawSide(cube.getUp(), 100, 4, context);
        drawSide(cube.getLeft(), 4, 100, context);
        drawSide(cube.getFront(), 100, 100, context);
        drawSide(cube.getRight(), 196, 100, context);
        drawSide(cube.getDown(), 100, 196, context);
        drawSide(cube.getBack(), 100, 292, context);
    }

    private void drawSide(ElementColor[] side, int x, int y, GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillRect(x, y, 98, 98);
        for (int i = 0; i < 4; i++) {
            switch (side[i]) {
                case WHITE:
                    context.setFill(Color.WHITE);
                    break;
                case GREEN:
                    context.setFill(Color.GREEN);
                    break;
                case RED:
                    context.setFill(Color.RED);
                    break;
                case BLUE:
                    context.setFill(Color.BLUE);
                    break;
                case ORANGE:
                    context.setFill(Color.ORANGE);
                    break;
                case YELLOW:
                    context.setFill(Color.YELLOW);
                    break;
            }
            context.fillRect(x + ((1 - i / 2) * (1 - i % 2) + (i / 2) * (i % 2)) * 48 + 2, y + (i / 2) * 48 + 2, 46, 46);
        }
    }
}

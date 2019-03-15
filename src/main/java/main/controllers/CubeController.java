package main.controllers;

import main.Cube;
import main.ElementColor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CubeController {

    public boolean isCubeCollected(Cube cube) {
        Set<ElementColor> top = new HashSet<>(Arrays.asList(cube.getUp()));
        Set<ElementColor> right = new HashSet<>(Arrays.asList(cube.getRight()));
        Set<ElementColor> bot = new HashSet<>(Arrays.asList(cube.getDown()));
        Set<ElementColor> left = new HashSet<>(Arrays.asList(cube.getLeft()));

        int sumSize = top.size() + right.size() + bot.size() + left.size();
        return (sumSize == 4);
    }

    public void controlMove(Cube cube, String ch) {
        boolean isClockWise = ch.length() == 1;

        switch (ch.charAt(0)) {
            case 'F':
                moveCubeFront(cube, isClockWise);
                break;
            case 'B':
                moveCubeBack(cube, isClockWise);
                break;
            case 'L':
                moveCubeLeft(cube, isClockWise);
                break;
            case 'R':
                moveCubeRight(cube, isClockWise);
                break;
            case 'U':
                moveCubeUp(cube, isClockWise);
                break;
            case 'D':
                moveCubeDown(cube, isClockWise);
                break;
        }
    }

    // --------------------------------Методы для смены цветов в кубе-------------------------------------

    /**
     * Меняем местами элементы одной стороны по часовой стрелке
     * (только цвета в плоскости)
     */
    private void moveFlatness(ElementColor[] flat) {
        ElementColor temp = flat[0];
        flat[0] = flat[3];
        flat[3] = flat[2];
        flat[2] = flat[1];
        flat[1] = temp;
    }

    private void moveCounterFlatness(ElementColor[] flat) {
        ElementColor temp = flat[3];
        flat[3] = flat[0];
        flat[0] = flat[1];
        flat[1] = flat[2];
        flat[2] = temp;
    }

    /**
     * Меняем местами цвета граней вокруг одной плоскости
     * (только для боковых плоскостей)
     */
    private void rotateSidePart(Cube cube,
                               ElementColor[] right,
                               ElementColor[] left
    ) {
        ElementColor[] up = cube.getUp();
        ElementColor[] down = cube.getDown();
        ElementColor temp_left = up[3];
        ElementColor temp_right = up[2];
        // swapping:
        up[2] = left[1];
        up[3] = left[2];
        left[1] = down[0];
        left[2] = down[1];

        down[0] = right[3];
        down[1] = right[0];
        right[0] = temp_left;
        right[3] = temp_right;
    }

    // -------------------------------Повороты куба по часовой стрелке------------------------------------

    private void moveCubeFront(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeFront(cube);
            return;
        }
        moveFlatness(cube.getFront());
        ElementColor[] right = cube.getRight();
        ElementColor[] left = cube.getLeft();

        rotateSidePart(cube, right, left);
    }

    private void moveCubeBack(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeBack(cube);
            return;
        }
        moveFlatness(cube.getBack());
        ElementColor[] right = cube.getLeft();
        ElementColor[] left = cube.getRight();

        rotateSidePart(cube, right, left);
    }

    private void moveCubeLeft(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeLeft(cube);
            return;
        }
        moveFlatness(cube.getLeft());
        ElementColor[] right = cube.getFront();
        ElementColor[] left = cube.getBack();

        rotateSidePart(cube, right, left);
    }

    private void moveCubeRight(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeRight(cube);
            return;
        }
        moveFlatness(cube.getRight());
        ElementColor[] right = cube.getBack();
        ElementColor[] left = cube.getFront();

        rotateSidePart(cube, right, left);
    }

    /**
     * ВЫЗЫВАЙТЕ САНИТАРОВ!
     */
    private void moveCubeUp(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeUp(cube);
            return;
        }
        moveFlatness(cube.getUp());
        ElementColor[] up = cube.getBack();
        ElementColor[] right = cube.getRight();
        ElementColor[] down = cube.getFront();
        ElementColor[] left = cube.getLeft();

        ElementColor temp_left = up[1];
        ElementColor temp_right = up[0];
        up[0] = right[1];
        up[1] = right[2];
        left[1] = down[0];
        left[2] = down[1];

        down[0] = right[0];
        down[1] = right[1];
        right[0] = temp_right;
        right[1] = temp_left;
    }

    private void moveCubeDown(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeDown(cube);
            return;
        }
        moveFlatness(cube.getFront());
        ElementColor[] up = cube.getFront();
        ElementColor[] right = cube.getRight();
        ElementColor[] down = cube.getBack();
        ElementColor[] left = cube.getLeft();

        ElementColor temp_left = up[3];
        ElementColor temp_right = up[2];
        up[2] = left[2];
        up[3] = left[3];
        left[2] = down[2];
        left[3] = down[3];

        down[2] = right[2];
        down[3] = right[3];
        right[2] = temp_right;
        right[3] = temp_left;
    }

    // --------------------------------Против часовой стрелки----------------------------------------------

    private void moveCubeFront(Cube cube) {
        //moveCounterFlatness(cube.getFront());
        for (int i = 0; i < 3; i++) {
            moveCubeFront(cube, true);
        }
    }

    private void moveCubeBack(Cube cube) {
        //moveCounterFlatness(cube.getBack());
        for (int i = 0; i < 3; i++) {
            moveCubeBack(cube, true);
        }
    }

    private void moveCubeLeft(Cube cube) {
        //moveCounterFlatness(cube.getLeft());
        for (int i = 0; i < 3; i++) {
            moveCubeLeft(cube, true);
        }
    }

    private void moveCubeRight(Cube cube) {
        //moveCounterFlatness(cube.getRight());
        for (int i = 0; i < 3; i++) {
            moveCubeRight(cube, true);
        }
    }

    private void moveCubeUp(Cube cube) {
        //moveCounterFlatness(cube.getUp());
        for (int i = 0; i < 3; i++) {
            moveCubeUp(cube, true);
        }
    }

    private void moveCubeDown(Cube cube) {
        //moveCounterFlatness(cube.getDown());
        for (int i = 0; i < 3; i++) {
            moveCubeDown(cube, true);
        }
    }
}

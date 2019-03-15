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
     * (для плоскостей кроме левой и правой)
     */

    private void rotateUFBD(Cube cube,
                            ElementColor[] up,
                            ElementColor[] down,
                            int lt,
                            int lb,
                            int rt,
                            int rb
    ) {
        ElementColor[] left = cube.getLeft();
        ElementColor[] right = cube.getRight();
        ElementColor temp_left = up[2];
        ElementColor temp_right = up[3];
        // swapping:
        up[2] = left[lb];
        up[3] = left[lt];
        left[lt] = down[0];
        left[lb] = down[1];

        down[0] = right[rb];
        down[1] = right[rt];
        right[rb] = temp_right;
        right[rt] = temp_left;
    }

    // -------------------------------Повороты граней куба по часовой стрелке------------------------------------

    private void moveCubeFront(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeFront(cube);
            return;
        }
        moveFlatness(cube.getFront());
        ElementColor[] up = cube.getUp();
        ElementColor[] down = cube.getDown();

        rotateUFBD(cube, up, down, 1, 3, 0, 2);
    }

    private void moveCubeBack(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeBack(cube);
            return;
        }
        moveFlatness(cube.getBack());
        ElementColor[] up = cube.getDown();
        ElementColor[] down = cube.getUp();

        rotateUFBD(cube, up, down, 2, 0, 3, 1);
    }

    private void moveCubeLeft(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeLeft(cube);
            return;
        }
        moveFlatness(cube.getRight());
        ElementColor[] up = cube.getUp();
        ElementColor[] front = cube.getFront();
        ElementColor[] down = cube.getDown();
        ElementColor[] back = cube.getBack();
        ElementColor temp_left = up[0];
        ElementColor temp_right = up[2];
        up[0] = back[0];
        up[2] = back[2];
        back[0] = down[0];
        back[2] = down[2];
        down[0] = front[0];
        down[2] = front[2];
        front[0] = temp_left;
        front[2] = temp_right;
    }

    private void moveCubeRight(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeRight(cube);
            return;
        }
        moveFlatness(cube.getRight());
        ElementColor[] up = cube.getUp();
        ElementColor[] front = cube.getFront();
        ElementColor[] down = cube.getDown();
        ElementColor[] back = cube.getBack();
        ElementColor temp_left = up[3];
        ElementColor temp_right = up[1];
        up[3] = front[3];
        up[1] = front[1];
        front[3] = down[3];
        front[1] = down[1];
        down[3] = back[3];
        down[1] = back[1];
        back[3] = temp_left;
        back[1] = temp_right;
    }

    private void moveCubeUp(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeUp(cube);
            return;
        }
        moveFlatness(cube.getUp());
        ElementColor[] up = cube.getBack();
        ElementColor[] down = cube.getFront();

        rotateUFBD(cube, up, down, 0, 1, 1, 0);
    }

    private void moveCubeDown(Cube cube, boolean isClockWise) {
        if (!isClockWise) {
            moveCubeDown(cube);
            return;
        }
        moveFlatness(cube.getDown());
        ElementColor[] up = cube.getFront();
        ElementColor[] down = cube.getBack();

        rotateUFBD(cube, up, down, 3, 2, 2, 3);
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

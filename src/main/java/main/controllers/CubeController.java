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

    public void rotate(Cube cube, String ch) {
        boolean isClockWise = true;
        boolean two = false;
        if (ch.length() > 1) {
            switch (ch.charAt(1)) {
                case '\'':
                    isClockWise = false;
                    break;
                case '2':
                    two = true;
                    break;
            }
        }
        switch (ch.charAt(0)) {
            case 'F':
                rotate(cube, Cube.FRONT, isClockWise, two);
                break;
            case 'B':
                rotate(cube, Cube.BACK, isClockWise, two);
                break;
            case 'L':
                rotate(cube, Cube.LEFT, isClockWise, two);
                break;
            case 'R':
                rotate(cube, Cube.RIGHT, isClockWise, two);
                break;
            case 'U':
                rotate(cube, Cube.UP, isClockWise, two);
                break;
            case 'D':
                rotate(cube, Cube.DOWN, isClockWise, two);
                break;
        }
    }

    // --------------------------------Методы для смены цветов в кубе-------------------------------------

    private void rotate(Cube cube, byte side, boolean isClockWise, boolean two) {
        if (two) {
            rotateClockWise(cube, side, isClockWise);
            rotateClockWise(cube, side, isClockWise);
            return;
        }
        rotateClockWise(cube, side, isClockWise);
    }

    private void rotateClockWise(Cube cube, byte side, boolean isClockWise) {
        moveFlatness(cube.getSide(side), isClockWise);
        switch (side) {
            case Cube.RIGHT:
                if (isClockWise) {
                    rotateUpRL(cube, side);
                } else {
                    rotateDownRL(cube, side);
                }
                break;
            case Cube.LEFT:
                if (isClockWise) {
                    rotateDownRL(cube, side);
                } else {
                    rotateUpRL(cube, side);
                }
                break;
            default:
                if (isClockWise) {
                    rotateUFBD(cube, side);
                } else {
                    rotateUFBD(cube, side);
                    rotateUFBD(cube, side);
                    rotateUFBD(cube, side);
                }
                break;
        }
    }

    /**
     * Меняем местами элементы одной стороны по часовой стрелке
     * (только цвета в плоскости)
     */
    private void moveFlatness(ElementColor[] flat, boolean isClockWise) {
        if (isClockWise) {
            ElementColor temp = flat[0];
            System.arraycopy(flat, 1, flat, 0, 3);
            flat[3] = temp;
        } else {
            ElementColor temp = flat[3];
            System.arraycopy(flat, 0, flat, 1, 3);
            flat[0] = temp;
        }
    }

    /**
     * Меняем местами цвета граней вокруг одной плоскости
     */

    private void rotateUpRL(Cube cube, byte side) {
        byte dual = (byte) (side % 2);
        ElementColor temp_top = cube.getSide(Cube.UP)[dual];
        ElementColor temp_bot = cube.getSide(Cube.UP)[3 - dual];
        for (byte i = 0; i < 3; i++) {
            cube.getSide(i)[dual] = cube.getSide((byte) (i + 1))[dual];
            cube.getSide(i)[3 - dual] = cube.getSide((byte) (i + 1))[3 - dual];
        }
        cube.getSide(Cube.BACK)[dual] = temp_top;
        cube.getSide(Cube.BACK)[3 - dual] = temp_bot;
    }

    private void rotateDownRL(Cube cube, byte side) {
        byte dual = (byte) (side % 2);
        ElementColor temp_top = cube.getSide(Cube.BACK)[dual];
        ElementColor temp_bot = cube.getSide(Cube.BACK)[3 - dual];
        for (byte i = 3; i > 0; i--) {
            cube.getSide(i)[dual] = cube.getSide((byte) (i - 1))[dual];
            cube.getSide(i)[3 - dual] = cube.getSide((byte) (i - 1))[3 - dual];
        }
        cube.getSide(Cube.UP)[dual] = temp_top;
        cube.getSide(Cube.UP)[3 - dual] = temp_bot;
    }

    private void rotateUFBD(Cube cube, byte side) {
        ElementColor[] up = cube.getSide((byte) ((side + 3) % 4));
        ElementColor[] down = cube.getSide((byte) ((side + 1) % 4));
        ElementColor[] left = cube.getLeft();
        ElementColor[] right = cube.getRight();
        ElementColor temp_left = up[2];
        ElementColor temp_right = up[3];
        // swapping:
        up[2] = left[(4 - side) % 4];
        up[3] = left[(5 - side) % 4];
        left[(4 - side) % 4] = down[0];
        left[(5 - side) % 4] = down[1];
        down[0] = right[side % 4];
        down[1] = right[(1 + side) % 4];
        right[side % 4] = temp_left;
        right[(1 + side) % 4] = temp_right;
    }

    //TODO: create method rotateCounterUFBD(Cube cube, byte side)
}

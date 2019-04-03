package main;

import static main.ElementColor.*;

public class Cube {
    public static final byte UP = 0;
    public static final byte FRONT = 1;
    public static final byte DOWN = 2;
    public static final byte BACK = 3;
    public static final byte RIGHT = 4;
    public static final byte LEFT = 5;

    private final ElementColor[][] elements;

    /**
     * Цвета в плоскости обозначены в следющем порядке:
     * 1, 0,
     * 2, 3
     */
    public Cube() {
        this(new ElementColor[]{GREEN, GREEN, GREEN, GREEN},
                new ElementColor[]{WHITE, WHITE, WHITE, WHITE},
                new ElementColor[]{RED, RED, RED, RED},
                new ElementColor[]{YELLOW, YELLOW, YELLOW, YELLOW},
                new ElementColor[]{ORANGE, ORANGE, ORANGE, ORANGE},
                new ElementColor[]{BLUE, BLUE, BLUE, BLUE});
    }

    public Cube(ElementColor[] front,
                ElementColor[] up,
                ElementColor[] right,
                ElementColor[] down,
                ElementColor[] left,
                ElementColor[] back
    ) {
        elements = new ElementColor[][]{
                new ElementColor[4],
                new ElementColor[4],
                new ElementColor[4],
                new ElementColor[4],
                new ElementColor[4],
                new ElementColor[4],};
        elements[FRONT] = front;
        elements[UP] = up;
        elements[RIGHT] = right;
        elements[DOWN] = down;
        elements[LEFT] = left;
        elements[BACK] = back;
    }

    public ElementColor[] getFront() {
        return elements[FRONT];
    }

    public ElementColor[] getUp() {
        return elements[UP];
    }

    public ElementColor[] getRight() {
        return elements[RIGHT];
    }

    public ElementColor[] getDown() {
        return elements[DOWN];
    }

    public ElementColor[] getLeft() {
        return elements[LEFT];
    }

    public ElementColor[] getBack() {
        return elements[BACK];
    }

    public ElementColor[] getSide(byte side) {
        return elements[side];
    }
}

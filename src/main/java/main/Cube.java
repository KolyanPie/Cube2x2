package main;

import static main.ElementColor.*;

public class Cube {
    private ElementColor[] front;
    private ElementColor[] up;
    private ElementColor[] right;
    private ElementColor[] down;
    private ElementColor[] left;
    private ElementColor[] back;

    /**
     * Цвета в плоскости обозначены в следющем порядке:
     * 0, 1,
     * 2, 3
     */
    public Cube() {
        front = new ElementColor[]  { WHITE, WHITE, WHITE, WHITE };
        up = new ElementColor[]    { RED, RED, RED, RED };
        right = new ElementColor[]  { GREEN, GREEN, GREEN, GREEN };
        down = new ElementColor[]    { ORANGE, ORANGE, ORANGE, ORANGE };
        left = new ElementColor[]   { BLUE, BLUE, BLUE, BLUE };
        back = new ElementColor[]   { YELLOW, YELLOW, YELLOW, YELLOW };
    }

    public Cube(ElementColor[] front,
                ElementColor[] up,
                ElementColor[] right,
                ElementColor[] down,
                ElementColor[] left,
                ElementColor[] back
    ) {
        this.front = front;
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
        this.back = back;
    }

    public ElementColor[] getFront() {
        return front;
    }

    public ElementColor[] getUp() {
        return up;
    }

    public ElementColor[] getRight() {
        return right;
    }

    public ElementColor[] getDown() {
        return down;
    }

    public ElementColor[] getLeft() {
        return left;
    }

    public ElementColor[] getBack() {
        return back;
    }
}

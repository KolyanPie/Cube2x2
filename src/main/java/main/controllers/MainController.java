package main.controllers;

import main.Cube;
import visual.CubeFrame;

import java.util.Scanner;

public class MainController {
    private static final CubeController CONTROLLER = new CubeController();
    private Cube cube;
    private Scanner scanner;
    private CubeFrame frame;

    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.control();
    }

    private void control() {
        scanner = new Scanner(System.in);
        cube = new Cube();
        String str;

        while (!(str = scanner.nextLine()).equals("exit")) {
            str = str.toUpperCase();
            if (str.length() <= 2) {
                CONTROLLER.controlMove(cube, str);
            } else if (str.equals("PRINT")) {
                if (frame != null) {
                    frame.dispose();
                    frame = null;
                }
                frame = new CubeFrame(cube);
            } else {
                System.out.println("String length is incorrect!");
            }
        }
    }
}

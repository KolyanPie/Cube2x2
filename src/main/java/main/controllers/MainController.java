package main.controllers;

import main.Cube;

import java.util.Scanner;

public class MainController {
    private static final CubeController CONTROLLER = new CubeController();
    private Cube cube;
    private Scanner scanner;

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
            } else {
                System.out.println("String length is incorrect!");
            }
        }
    }
}

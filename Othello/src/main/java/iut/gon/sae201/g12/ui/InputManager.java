package iut.gon.sae201.g12.ui;

import java.util.Scanner;

public class InputManager {
    public static int awaitNumberInput() {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        while (input == -1) {
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
        return input;
    }

    public static char awaitCharInput() {
        Scanner scanner = new Scanner(System.in);
        char input = (char) -1;
        while (input == (char) -1) {
            try {
                input = scanner.nextLine().charAt(0);
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
        return input;
    }
}

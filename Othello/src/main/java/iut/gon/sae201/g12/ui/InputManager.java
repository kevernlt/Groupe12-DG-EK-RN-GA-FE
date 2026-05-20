package iut.gon.sae201.g12.ui;

import java.util.Scanner;

/**
 * Gestionnaire des entrées utilisateur en terminal.
 *
 * <p>Fournit des méthodes pour lire différents types d'entrées de l'utilisateur.
 */
public class InputManager {
    /**
     * Lit une entrée numérique de l'utilisateur.
     *
     * <p>Boucle jusqu'à recevoir un entier valide.
     *
     * @return le nombre entré par l'utilisateur
     */
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

    /**
     * Lit une entrée caractère de l'utilisateur.
     *
     * <p>Boucle jusqu'à recevoir un caractère valide (le premier caractère de la ligne).
     *
     * @return le caractère entré par l'utilisateur
     */
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

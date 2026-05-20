package iut.gon.sae201.g12.ui;

/**
 * Utilitaires pour l'interface utilisateur en terminal.
 *
 * <p>Fournit des méthodes pour manipuler l'affichage terminal (nettoyage, etc.).
 */
public class UIUtils {
    /** Commande ANSI pour effacer le terminal */
    public static final String CLEAR_TERMINAL_CMD = "\033[H\033[2J";

    /**
     * Efface le terminal.
     */
    public static void clearTerminal() {
        System.out.print(CLEAR_TERMINAL_CMD);
        System.out.flush();
    }
}

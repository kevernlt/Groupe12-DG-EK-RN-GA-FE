package iut.gon.sae201.g12.ui;

public class UIUtils {
    public static final String CLEAR_TERMINAL_CMD = "\033[H\033[2J";

    public static void clearTerminal() {
        System.out.print(CLEAR_TERMINAL_CMD);
        System.out.flush();
    }
}

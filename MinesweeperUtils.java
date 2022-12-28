import java.util.Scanner;
public class MinesweeperUtils {
    public static Scanner sc = new Scanner(System.in);

    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BLACK  = "\u001B[30m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE  = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_GREENLIGHT = "\u001B[92m";
    public static final String ANSI_VIOLET = "\u001B[94m";
    public static final String ANSI_BLUESOFT = "\u001B[0;49;34m";
    public static final String ANSI_GREENSOFT = "\u001B[38;5;70m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String UNICODE_ANGLE_LEFT_TOP = "\u250C";
    public static final String UNICODE_ANGLE_RIGHT_TOP = "\u2510";
    public static final String UNICODE_ANGLE_LEFT_BOTTOM = "\u2514";
    public static final String UNICODE_ANGLE_RIGTH_BOTTOM = "\u2518";

    public static final String UNICODE_LINE_ORIZZONTAL = "\u2500";
    public static final String UNICODE_LINE_VERTICAL = "\u2502";

    public static final String UNICODE_ARROW_RIGHT = "←";

    /**
     * gli si passa i un range di valori che l'utente può inserire
     * 
     * @param range [min, max], se -1 è illimitato
     * @return
     */
    public static int checkUserDigit(int[] range) { // -2 indica che c'è un infinito
        int result = -1, min = range[0], max = range[1];
        boolean isOkey = false;
        do {
            try {
                result = Integer.parseInt(sc.nextLine());
                if ((result >= min || min == -1) && (result < max || max == -1))
                    isOkey = true;
            } catch (Exception e) {
            }

            if (!isOkey)
                System.out.println(MinesweeperUtils.ANSI_RED + "Intero non valido!!" + MinesweeperUtils.ANSI_RESET);
        } while (!isOkey);
        return result;
    }

    /**
     * gli si passa un range di Stringhe ammesse
     * 
     * @param possibStrings
     * @return
     */
    public static String checkUserDigit(String[] possibStrings) {
        String result;
        boolean isOkey = false;
        do {
            result = sc.nextLine();
            for (int i = 0; i < possibStrings.length; i++)
                if (result.equals(possibStrings[i]))
                    isOkey = true;

            if (!isOkey)
                System.out.println(MinesweeperUtils.ANSI_RED + "Stringa non valida!!" + MinesweeperUtils.ANSI_RESET);
        } while (!isOkey);
        return result;
    }

    /**
     * pulisce la console
     */
    public static void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

import java.util.Scanner;
public class MinesweeperDisplay {
    static Scanner sc = new Scanner(System.in);

    /**
     * chiede all'utente e restituisce rispettivamente:
     * <ul>
     * <li>-1 se l'utente vuole uscire</li>
     * <li>0 se l'utente vuole iniziare una nuova partita</li>
     * <li>1-n id della partita che vuole giocare</li>
     * </ul>
     */
    public static int askGame(MinesweeperGames games) {
        System.out.print("Creare un nuovo game (c), continuare uno esistente (n), o chiudere (x): ");
        String result = MinesweeperUtils.checkUserDigit(new String[] { "c", "n", "x"});

        if (result.equals("n"))
            return chooseGame(games); // 
        else if (result.equals("c"))
            return 0;
        return -1;
    }

    /**
     *  tramite MinesweeperDisplay.printGame stampa le diverse griglie salvate e chiede all'utente di scegliere una di queste
     */
    public static int chooseGame(MinesweeperGames games) {

        for (int i = 0; i < games.getListGame().size(); i++) {
            printGame(games.getListGame().get(i), "listGame");
        }

        System.out.print("Inserisci l'id del game che vuoi giocare: ");
        return MinesweeperUtils.checkUserDigit(new int[] { 1, -1 });

    }

    /**
     * Il metodo principale per stampare un la grid di un game
     * 
     * @param game
     * @param type indica se deve fare una stampa accurata o una stampa per la lista dei games
     */
    public static void printGame(MinesweeperGame game, String type) {
        System.out.print(MinesweeperUtils.ANSI_WHITE_BACKGROUND + MinesweeperUtils.ANSI_BLACK);

        if (type.equals("game")) {
            System.out.print("Mine rimanenti: " + game.getNumBombRemain());
        }
        if (type.equals("listGame")) {
            System.out.print("Id Game: " + game.getId());
        }

        System.out.print(MinesweeperUtils.ANSI_RESET);

        if (type.equals("listGame") && game.isDone()) {
            System.out.print(" - Game completato");
        }

        newLine();
        System.out.print(MinesweeperUtils.ANSI_WHITE);

        // first line
        System.out.print(MinesweeperUtils.UNICODE_ANGLE_LEFT_TOP);
        for (int i = 0; i < game.getSize() * 2 - 1; i++)
            System.out.print(MinesweeperUtils.UNICODE_LINE_ORIZZONTAL);
        System.out.print(MinesweeperUtils.UNICODE_ANGLE_RIGHT_TOP);
        newLine();

        for (int i = 0; i < game.getSize(); i++) {
            System.out.print(MinesweeperUtils.UNICODE_LINE_VERTICAL);
            for (int j = 0; j < game.getSize(); j++) {
                // current selected
                if (i == game.getPosX() && j == game.getPosY() && type.equals("game")) 
                    System.out.print(MinesweeperUtils.ANSI_WHITE_BACKGROUND);

                System.out.print(game.getDisplay().getCell(i, j).toString());
                System.out.print(MinesweeperUtils.ANSI_RESET);

                if (j != game.getSize() - 1)
                    System.out.print(" ");
            }
            System.out.print(MinesweeperUtils.UNICODE_LINE_VERTICAL);
            newLine();
        }

        // bottom
        System.out.print(MinesweeperUtils.UNICODE_ANGLE_LEFT_BOTTOM);
        for (int i = 0; i < game.getSize() * 2 - 1; i++)
            System.out.print(MinesweeperUtils.UNICODE_LINE_ORIZZONTAL);
        System.out.print(MinesweeperUtils.UNICODE_ANGLE_RIGTH_BOTTOM);
        newLine();

        System.out.print(MinesweeperUtils.ANSI_RESET);

        if (type.equals("game")) {
            gameCommand();
        }
    }

    /**
     * Esegue le domande necessarie per poter generare un game
     */
    public static int[] generateGame() {
        int res[] = new int[2];
        System.out.print("Dimensione griglia: ");
        res[0] = MinesweeperUtils.checkUserDigit(new int[] { 0, -1 });

        System.out.print("Numero bombe: ");
        res[1] = MinesweeperUtils.checkUserDigit(new int[] { 0, res[0] * res[0]});

        return res;
    }

    /**
     * stampa i comandi che il giocatore può eseguire
     */
    private static void gameCommand() {
        newLine();
        System.out.print(MinesweeperUtils.ANSI_BLACK + MinesweeperUtils.ANSI_WHITE_BACKGROUND +
                "Navigazione:"
                + MinesweeperUtils.ANSI_RESET + " w a s d  ");

        System.out.print(MinesweeperUtils.ANSI_BLACK + MinesweeperUtils.ANSI_WHITE_BACKGROUND +
                "Rileva:"
                + MinesweeperUtils.ANSI_RESET + " r  ");

        System.out.print(MinesweeperUtils.ANSI_BLACK + MinesweeperUtils.ANSI_WHITE_BACKGROUND +
                "Toogle Flag:"
                + MinesweeperUtils.ANSI_RESET + " f  ");

        System.out.print(MinesweeperUtils.ANSI_BLACK + MinesweeperUtils.ANSI_WHITE_BACKGROUND +
                "Exit:"
                + MinesweeperUtils.ANSI_RESET + " x ");

        newLine();
        System.out.print("> ");
    }

    /**
     * va a capo
     */
    private static void newLine() {
        System.out.println();
    }
}

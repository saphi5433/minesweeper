public class Minesweeper {
    public static void main(String[] args) {
        Minesweeper.play();
    }

    /**
     * Gestisce i tutto il gioco
     */
    public static void play() {

        // carica tutte le partite salvate in data.json
        MinesweeperGames games = MinesweeperStore.load();

        String result = "";
        do {
            // chiede all'untete quale gioco vuole caricare
            int gameToPlay = MinesweeperDisplay.askGame(games);

            // esce dal game
            if (gameToPlay == -1) {
                result = "x";
            } else {
                // crea una nuova partita e la imposta come partita corrente
                if (gameToPlay == 0) {
                    gameToPlay = games.generateNewID();
                    int info[] = MinesweeperDisplay.generateGame();
                    games.add(new MinesweeperGame(gameToPlay, info[0], info[1]));
                }

                games.setIDActiveGame(gameToPlay);

                

                // prende il gioco attivo e inizia a giocarlo
                games.getGameById(games.getIDActiveGame()).playGame();;
            }

            // salva i dati di tutte le partite
            MinesweeperStore.save(games);
        } while (!result.equals("x"));
    }
}

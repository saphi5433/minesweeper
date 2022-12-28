import cell.*;
import grid.*;

public class MinesweeperGame {
    private int id;
    private int size;
    private int numBomb;
    private int numBombRemain;
    private boolean isDone = false;
    private boolean isWin = false;
    /**
     * contiene le informazioni sulla posizione delle bombe e delle celle adiacenti
     */
    private GridGame game;

    /**
     * e la griglia che viene fatta vedere all'utente
     */
    private GridDisplay display;
    private int pos[] = new int[] { 0, 0 };

    /**
     * Costruttore di un game,
     * Viene utilizzato quando bisogna generare un nuovo game
     * 
     * @param id
     * @param size
     * @param numBomb
     */
    MinesweeperGame(int id, int size, int numBomb) {
        this.id = id;
        this.size = size;
        this.numBomb = numBomb;
        this.numBombRemain = numBomb;

        display = new GridDisplay(size);
    }

    /**
     * Costruttore di un game,
     * Viene utilizzato quando bisogna caricare un game salvato
     * 
     * @param id
     * @param size
     * @param numBomb
     * @param numBombRemain
     * @param isDone
     * @param isWin
     * @param game
     * @param display
     */
    MinesweeperGame(int id, int size, int numBomb, int numBombRemain, boolean isDone, boolean isWin, GridGame game,
            GridDisplay display) {
        this.id = id;
        this.size = size;
        this.numBomb = numBomb;
        this.numBombRemain = numBombRemain;
        this.isDone = isDone;
        this.isWin = isWin;

        this.game = game;
        this.display = display;
    }


    /**
     * Inizia l'istanza correnti di game e fa un po' da controller tra tutte le azioni che può fare l'utente
     */
    public void playGame() {
        String result = "";

        do {
            MinesweeperUtils.clearTerminal();

            // stampa il game e chiede all'utente cosa vuole fare
            MinesweeperDisplay.printGame(this, "game");
            result = MinesweeperUtils.checkUserDigit(new String[] { "w", "a", "s", "d", "r", "f", "x" });

            if (result.equals("w")) {
                pos[0]--;
            } else if (result.equals("a")) {
                pos[1]--;
            } else if (result.equals("s")) {
                pos[0]++;
            } else if (result.equals("d")) {
                pos[1]++;
            } else if (result.equals("r")) {
                // quanto una cella viene rilevata viene chiamata la funzione di rilevamento, successivamente viene fatto un controllo se il gioco è finito o meno
                revealCell(pos[0], pos[1], true);
                if (isDone) {
                    revealBomb();
                    if (isWin)
                        System.out.println(MinesweeperUtils.ANSI_CYAN + "Congratulazione hai vinto!!!!"
                                + MinesweeperUtils.ANSI_RESET);
                    else
                        System.out.println(MinesweeperUtils.ANSI_RED + "Hai perso :(" + MinesweeperUtils.ANSI_RESET);

                }
            } else if (result.equals("f")) {
                // fa il toogle di una cella marcata o no
                if (display.getCell(pos[0], pos[1]).getType().equals("Hidden")) {
                    ((CellHidden) display.getCell(pos[0], pos[1])).toogleMarked();
                    if (((CellHidden) display.getCell(pos[0], pos[1])).isMarked())
                        numBombRemain -= 1;
                    else
                        numBombRemain += 1;
                }

            }
        } while (!result.equals("x"));
    }

    /**
     * Metodo ricorsivo per rilevare un'area di celle vuote,
     * uscita: se il cursore esce dai bordi o se rileva una cella vicina a una bomba 
     * se la cella è vuota chiama la procedura per tutte le celle adiacenti facendo così scoprire tutte le aree vuote
     * 
     * @param x
     * @param y
     * @param firstCall serve per capire se incontra una bomba a perso o si deve solo fermare
     */
    private void revealCell(int x, int y, boolean firstCall) { 
        // controlla se il cursore è uscito dalla grid
        if (x < 0 || x >= size || y < 0 || y >= size)
            return;

        // se il game non è ancora inizializzato (prima mossa)
        // allora inizializza un game
        if (game == null)
            game = new GridGame(size, numBomb, x, y);

        // se il giocatore rileva una bomba 
        if (game.getCell(x, y).getType().equals("Bomb") && firstCall) {
            display.setCell(x, y, new CellBomb());
            isWin = false;
            isDone = true;

        // quando il giocatore vince, cioè scopre tutte le bombe
        } else if (getNumCellHidden() == numBomb) {
            isWin = true;
            isDone = true;

        // quanto il cursore è sopra una cella vuota
        } else if (game.getCell(x, y).getType().equals("Clear")) {
            // imposta visualmente la cella vuota
            display.setCell(x, y, new CellClear());
            // se la cella non è ancora stata controllata esegue per tutte le celle adiacenti la stessa chimata e imposta la cella a controllata
            if(!((CellClear) game.getCell(x, y)).isChecked()){
                ((CellClear) game.getCell(x, y)).setChecked(true);
                revealCell(x, y + 1, false);
                revealCell(x - 1, y + 1, false);
                revealCell(x + 1, y + 1, false);
                revealCell(x - 1, y, false);
                revealCell(x + 1, y, false);
                revealCell(x - 1, y - 1, false);
                revealCell(x + 1, y - 11, false);
                revealCell(x, y - 1, false);
            }

        // quanto il cursore è sopra una cella adiacente
        } else if (game.getCell(x, y).getType().equals("CloseBomb")) {
            display.setCell(x, y, game.getCell(x, y));
        }
    }

    /**
     * Serve ad ottenere il numero di celle ancora sconosciute
     * @return
     */
    private int getNumCellHidden() {
        int num = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (display.getCell(i, j).getType().equals("Hidden"))
                    num++;
        return num;
    }

    /**
     * Rileva la posizione di tutte le bombe, quando il giocatore vince o perde
     */
    private void revealBomb(){
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (game.getCell(i, j).getType().equals("Bomb"))
                    display.setCell(i, j, new CellBomb());
    }

    public int getPosX() {
        return pos[0];
    }

    public int getPosY() {
        return pos[1];
    }

    public int getNumBombRemain() {
        return numBombRemain;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public GridDisplay getDisplay() {
        return display;
    }

    public int getNumBomb() {
        return numBomb;
    }

    public int[] getPos() {
        return pos;
    }

    public GridGame getGame() {
        return game;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean isWin() {
        return isWin;
    }
}

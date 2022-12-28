package cell;

public class CellCloseBomb implements Cell {
    /**
     * indica il numero di bombe adiacenti alla posizione della cella
     */
    private int numBombClose;


    public CellCloseBomb(int numBombClose) {
        this.numBombClose = numBombClose;
    }

    public int getNumBombClose() {
        return numBombClose;
    }

    @Override
    public String toString() {
        return "\u001B[33m" + String.valueOf(numBombClose) + "\u001B[0m";
    }

    @Override
    public String getType() {
        return "CloseBomb";
    }
}

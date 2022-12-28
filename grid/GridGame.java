package grid;
import cell.*;
public class GridGame extends Grid {

    /**
     * Costruttore per la generazione di una griglia
     * 
     * @param size
     * @param numBomb
     * @param x posizione x della prima cella selezionata, serve a non far predere l'utente al primo click
     * @param y posizione y della prima cella selezionata, serve a non far predere l'utente al primo click
     */
    public GridGame(int size, int numBomb, int x, int y) {
        super(size);

        populateGrid();
        placeBomb(x, y, numBomb);
    }


    /**
     * Costruttore per l'assegnazione di una griglia
     * 
     * @param grid
     */
    public GridGame(Cell[][] grid){
        super(grid);
    }

    
    /**
     * Inizializza la griglia con le celle vuote
     */
    private void populateGrid() {
        for(int i = 0; i < super.getSize(); i++)
            for(int j = 0; j < super.getSize(); j++)
                super.setCell(i, j, new CellClear());
    }

    /**
     * Inserisce le bombe nelle posizioni casuali
     * 
     * @param numBomb
     */
    private void placeBomb(int x, int y, int numBomb) {
        int remain = numBomb;

        while(remain > 0){
            int i = (int) (Math.random() * super.getSize());
            int j = (int) (Math.random() * super.getSize());

            if(!super.getCell(i, j).getType().equals("Bomb") && !(i == x && j == y)){
                super.setCell(i, j, new CellBomb());
                remain--;
            }
        }

        setNumCLoseBomb();
    }

    /**
     * Per ogni cella conto le bombe adiacenti
     */
    private void setNumCLoseBomb(){
        for(int i = 0; i < super.getSize(); i++)
            for(int j = 0; j < super.getSize(); j++){
                if(super.getCell(i, j).getType().equals("Clear")){
                    int numClose = getNumCloseBomb(i, j);
                    if(numClose != 0)
                        super.grid[i][j] = new CellCloseBomb(numClose);
                }
            }
    }

    /**
     * Data una cella restituisce il numero di bombe adiacenti
     * 
     * @param x
     * @param y
     * @return numero di bombe adiacenti
     */
    private int getNumCloseBomb(int x, int y){
        int numBombCLose = 0;
        if(y > 0){
            if(super.getCell(x, y - 1).getType().equals("Bomb"))
                numBombCLose++;

            if(x > 0)
                if(super.getCell(x - 1, y - 1).getType().equals("Bomb"))
                    numBombCLose++;
            
            if(x < super.getSize() - 1)
                if(super.getCell(x + 1, y - 1).getType().equals("Bomb"))
                    numBombCLose++;
        }

        if(x > 0)
            if(super.getCell(x - 1, y).getType().equals("Bomb"))
                numBombCLose++;

        if(x < super.getSize() - 1)
            if(super.getCell(x + 1, y).getType().equals("Bomb"))
                numBombCLose++;

        if(y < super.getSize() - 1){
            if(super.getCell(x, y + 1).getType().equals("Bomb"))
                numBombCLose++;

            if(x > 0)
                if(super.getCell(x - 1, y + 1).getType().equals("Bomb"))
                    numBombCLose++;
            
            if(x < super.getSize() - 1)
                if(super.getCell(x + 1, y + 1).getType().equals("Bomb"))
                    numBombCLose++;
        }
            

        return numBombCLose;
    }

    @Override
    public Cell[][] getGrid() {
        return super.getGrid();
    }
}

package grid;
import cell.*;

public abstract class Grid {
    protected Cell[][] grid;

    /**
     * Costruttore per la generazione di una griglia
     * 
     * @param size
     */
    protected Grid(int size) {
        grid = new Cell[size][size];
    }

    /**
     * Costruttore per l'assegnazione di una griglia
     * 
     * @param grid
     */
    protected Grid(Cell[][] grid) {
        this.grid = grid;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public void setCell(int x, int y, Cell value){
        grid[x][y] = value;
    }

    public int getSize() {
        return grid.length;
    }
}

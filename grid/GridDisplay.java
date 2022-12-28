package grid;
import cell.*;
public class GridDisplay extends Grid {
    /**
     * Costruttore per la generazione di una griglia
     * 
     * @param size
     */
    public GridDisplay(int size) {
        super(size);
        populateGrid();
    }

    /**
     * Costruttore per l'assegnazione di una griglia
     * 
     * @param grid
     */
    public GridDisplay(Cell[][] grid) {
        super(grid);
    }

    /**
     * Inizializza la griglia con le celle nascoste
     */
    private void populateGrid(){
        for(int i = 0; i < super.getSize(); i++)
            for(int j  = 0; j < super.getSize(); j++)
                super.setCell(i, j, new CellHidden());
    }
    
}

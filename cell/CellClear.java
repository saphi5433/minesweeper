package cell;

import java.io.ObjectInputStream.GetField;

public class CellClear implements Cell {

    /**
     * Viene utilizzata in MinesweeperGame.revealCell()
     * per poter sapere se la cella è già stata controllata
      */
    private boolean isChecked = false;

    public CellClear(){}
    
    @Override
    public String toString() {
        return " ";
    }

    @Override
    public String getType() {
        return "Clear";
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }
}

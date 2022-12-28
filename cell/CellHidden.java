package cell;
public class CellHidden implements Cell {
    /**
     * indica se la cella è stata contrassegnata come bomba
     */
    private boolean marked = false;

    public CellHidden(){}

    public CellHidden(boolean marked){
        this.marked = marked;
    }

    @Override
    public String toString() {
        if (marked)
            return "\u001B[34m" + "F" + "\u001B[0m";
        
        return "?";
    }

    @Override
    public String getType() {
        return "Hidden";
    }
    

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isMarked() {
        return marked;
    }

    public void toogleMarked() {
        marked = !marked;
    }
}

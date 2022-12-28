package cell;
public class CellBomb implements Cell {

    public CellBomb(){}

    @Override
    public String toString(){
        return "\u001B[31m" + "B" + "\u001B[0m";
    }

    @Override
    public String getType() {
        return "Bomb";
    } 
}

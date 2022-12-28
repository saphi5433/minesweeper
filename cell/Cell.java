package cell;
public interface Cell {
    /**
     * @return la rappresentazione grafica della cella
     */
    public String toString();   

    /**
     * @return tipo della cella, per poter identificare a quale classe specifica appartiene
     */
    public String getType();
}
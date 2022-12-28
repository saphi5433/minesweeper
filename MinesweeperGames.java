import java.util.ArrayList;
public class MinesweeperGames {
    /**
     * il game che viene fatto vedere all'utente (quello che sta giocando)
     */
    private int IDActiveGame = -1; 
    private ArrayList<MinesweeperGame> listGame;

    public MinesweeperGames(ArrayList<MinesweeperGame> listGame) {
        this.listGame = listGame;
    }

    public ArrayList<MinesweeperGame> getListGame() {
        return listGame;
    }

    /**
     * permette di selezionare un game tramite id
     * @param id
     * @return
     */
    public MinesweeperGame getGameById(int id) {
        for(int i = 0; i < listGame.size(); i++){
            if(listGame.get(i).getId() == id){
                return listGame.get(i);
            }
        }

        return null;
    }

    public void add(MinesweeperGame game){
        listGame.add(game);
    }

    public void setGame(int id, MinesweeperGame game) {
        listGame.set(id, game);
    }

    /**
     * restituisce il primo id disponibile per generare un nuovo game
     * 
     * @return
     */
    public int generateNewID(){
        int max;
        if(listGame.size() != 0)
            max = listGame.get(0).getId();
        else 
            return 1;

        for(int i = 0; i < listGame.size(); i++) {

            if(listGame.get(i).getId() > max)
                max = listGame.get(i).getId();


        }

        return max + 1;
    }

    public void setIDActiveGame(int activeGame) {
        this.IDActiveGame = activeGame;
    }

    public int getIDActiveGame() {
        return IDActiveGame;
    }

    
}

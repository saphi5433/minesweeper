import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import cell.*;
import java.util.ArrayList;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.Reader;
import grid.*;

public class MinesweeperStore {
    /**
     * Passata la lista di game, la salva in un file data.json
     * 
     * @param games
     */
    public static void save(MinesweeperGames games){
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();

        for(MinesweeperGame game : games.getListGame()){
            JSONObject gameObj = new JSONObject();


            gameObj.put("id", game.getId());
            gameObj.put("size", game.getSize());
            gameObj.put("numBomb", game.getNumBomb());
            gameObj.put("numBombRemain", game.getNumBombRemain());
            gameObj.put("isDone", game.isDone());
            gameObj.put("isWin", game.isWin());

            if(game.getGame() != null)
                gameObj.put("game", getJsonCell(game.getGame().getGrid()));
            gameObj.put("display", getJsonCell(game.getDisplay().getGrid()));

            

            array.add(gameObj);
        }

        obj.put("games", array);

        try (FileWriter file = new FileWriter("data.json")) {
            file.write(array.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serve a trasformare una Grid in Array json, viene utilizzato in MinesweeperStore.save
     * 
     * @param grid
     * @return
     */
    private static JSONArray getJsonCell(Cell[][] grid){
        JSONArray rowJson = new JSONArray();

        for(int i = 0; i < grid.length; i++){
            JSONArray colJson = new JSONArray();

            for(int j = 0; j < grid[i].length; j++){
                JSONObject cellJson = new JSONObject();
                cellJson.put("type", grid[i][j].getType());
                if(grid[i][j].getType().equals("Hidden")){
                    cellJson.put("marked", ((CellHidden) grid[i][j]).isMarked());
                } else if (grid[i][j].getType().equals("CloseBomb")){
                    cellJson.put("numBombClose", ((CellCloseBomb) grid[i][j]).getNumBombClose());
                }

                colJson.add(cellJson);
            }

            rowJson.add(colJson);
        }

        return rowJson;
    }

    /**
     * Legge dal file data.json e costruisce un MinesweeperGames
     * 
     * @return
     */
    public static MinesweeperGames load(){
        ArrayList<MinesweeperGame> games = new ArrayList<>();

        JSONParser jsonParser  = new JSONParser();
        try (Reader reader = new FileReader("data.json")){

        Object obj = jsonParser.parse(reader);

        JSONArray gamesJson = (JSONArray) obj;
        //System.out.println(gamesJson);

        gamesJson.forEach(game -> parseMinesweeperGame((JSONObject) game, games));

        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new MinesweeperGames(games);
    }

    /** 
     * Ricompone un game da un JSONObject, viene utilizzato in MinesweeperStore.load
     */
    private static ArrayList<MinesweeperGame> parseMinesweeperGame(JSONObject gameJson, ArrayList<MinesweeperGame> games){
        int id = (int) ((long) gameJson.get("id"));
        int size = (int) ((long) gameJson.get("size"));
        int numBomb = (int) ((long) gameJson.get("numBomb"));
        int numBombRemain = (int) ((long) gameJson.get("numBombRemain"));
        Boolean isDone = (Boolean) gameJson.get("isDone");
        Boolean isWin = (Boolean) gameJson.get("isWin");
        
        
        GridGame game = null;
        if((JSONArray) gameJson.get("game") != null){
            game = new GridGame(parseCell((JSONArray) gameJson.get("game"), size));
        }
        
        GridDisplay display = new GridDisplay(parseCell((JSONArray) gameJson.get("display"), size));

        MinesweeperGame tmp = new MinesweeperGame(id, size, numBomb, numBombRemain, isDone, isWin, game, display);

        games.add(tmp);

        return games;
    }

    /**
     * Ricompone una cella da un JSONArray, viene utilizzato in MinesweeperStore.parseMinesweeperGame
     */
    private static Cell[][] parseCell(JSONArray gridJson, int size){
        Cell grid[][] = new Cell[size][size];
        for(int i = 0; i < size; i++){
            JSONArray colJson = (JSONArray) gridJson.get(i);
            for(int j = 0; j < size; j++){
                JSONObject cellJson = (JSONObject) colJson.get(j);
                String type = (String) cellJson.get("type");
                if(type.equals("Hidden")){
                    Boolean marked = (Boolean) cellJson.get("marked");
                    grid[i][j] = new CellHidden(marked);
                } else if (type.equals("CloseBomb")){
                    int numBombClose = (int) ((long) cellJson.get("numBombClose"));
                    grid[i][j] = new CellCloseBomb(numBombClose);
                } else if (type.equals("Bomb")){
                    grid[i][j] = new CellBomb();
                } else if (type.equals("Clear")){
                    grid[i][j] = new CellClear();
                }
            }
        }

        return grid;
    }
}

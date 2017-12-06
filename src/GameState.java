import java.io.Serializable;

public class GameState implements Serializable{

    private Map map;
    private GameChar gameChar;

    public GameState(Map m, GameChar gc) {
        this.map = m;
        this.gameChar = gc;
    }

    public Map getMap() {
        return map;
    }

    public GameChar getGameChar() {
        return gameChar;
    }
}
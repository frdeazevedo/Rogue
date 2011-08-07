import javax.swing.*;

public class GameLoop {
    public static void main(String args[]) {
        GameController gc = new GameController();
        
        try {
            gc.loadObstacleMatrix("res/maps/map1_obstacle.txt");
            gc.loadBaseMatrix("res/maps/map1_terrain.txt");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

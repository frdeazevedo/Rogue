import javax.swing.*;

public class GameLoop {
    public static void main(String args[]) {
        GameController gc = new GameController(352, 384);
        
        try {
            gc.loadObstacleMatrix("res/maps/map1_obstacle.txt");
            gc.loadActiveMatrix("res/maps/map1_active.txt");
            gc.loadBaseMatrix("res/maps/map1_terrain.txt");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        gc.setActiveTileImage(2, 2, "res/sprites/hero.png");
    }
}

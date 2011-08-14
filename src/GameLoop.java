import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GameLoop {
    public static void main(String args[]) {
        GameController gc = new GameController(352, 384);
        
        try {
            gc.loadActiveMatrix("res/maps/map1_active.txt");
            gc.loadObstacleMatrix("res/maps/map1_obstacle.txt");
            gc.loadBaseMatrix("res/maps/map1_terrain.txt");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        try{
            ImageManager imageManager = ImageManager.getInstance();
            imageManager.putImage("hero", "res/sprites/hero.png");
            
            gc.setActiveTileImage(0, 0, imageManager.getImage("hero"));
            gc.updateScreen();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

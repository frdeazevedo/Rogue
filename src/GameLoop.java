import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GameLoop {
    public static void main(String args[]) {
        GameController gc = new GameController(352, 384);
        
        try {
            gc.loadObstacleMatrix("res/maps/map1_obstacle.txt");
            gc.loadActiveMatrix("res/maps/map1_active.txt");
            gc.loadBaseMatrix("res/maps/map1_terrain.txt");
            gc.centerScreen();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        try{
            Creature hero = new Creature();
            hero.mImage = ImageIO.read(new File("res/sprites/hero.png"));
            hero.mCoord = new Coord2(0, 0);
            
            gc.setActiveTileImage(hero.mCoord.getX(), hero.mCoord.getY(), hero.mImage);
            gc.updateScreen();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

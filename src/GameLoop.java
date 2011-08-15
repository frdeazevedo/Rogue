import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GameLoop {
    public static void main(String args[]) {
        GameController gc = new GameController(352, 384);
        ImageManager mImageManager = ImageManager.getInstance();
        
        try {
            gc.loadActiveMatrix("res/maps/map1_active.txt");
            gc.loadObstacleMatrix("res/maps/map1_obstacle.txt");
            gc.loadBaseMatrix("res/maps/map1_terrain.txt");
            
            mImageManager.putImage("hero", "res/sprites/hero.png");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        Creature hero = new Creature();
        hero.setCoords(2, 2);
        hero.setImage(mImageManager.getImage("hero"));
        
        gc.setHero(hero);
        gc.centerScreenOnCoord(hero.getCoords().getX(), hero.getCoords().getY());
    }
}

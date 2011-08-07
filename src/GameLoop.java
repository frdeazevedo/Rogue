import javax.swing.*;

public class GameLoop {
    public static void main(String args[]) {
/*        GameWindow gw = new GameWindow(500, 500);
        
        gw.getActiveTileMatrix().getTileView(2, 2).getTile().setImage("res/woodenchest.png");
        
        gw.setVisible(true);*/
        
        GameController gc = new GameController();
        
        try {
            gc.loadBaseMatrix("res/map1.txt");
        }
        catch(Exception e) {
        }
    }
}

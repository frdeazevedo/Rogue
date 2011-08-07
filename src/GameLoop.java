import javax.swing.*;

public class GameLoop {
    public static void main(String args[]) {
        GameController gc = new GameController();
        
        try {
            gc.loadBaseMatrix("res/maps/map1.txt");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

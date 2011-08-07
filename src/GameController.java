import java.io.*;

public class GameController {
    public GameController() {
        mGameWindow = new GameWindow(500,500);
        mGameWindow.setVisible(true);
    }

    public void loadBaseMatrix(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        String dimension[] = in.readLine().split(",");
        
        TileMatrix tm = new TileMatrix(Integer.parseInt(dimension[0]),
                                       Integer.parseInt(dimension[1]),
                                       "res/empty.png",
                                       "res/empty.png");
        
        int l = 0;
        while(in.ready()) {
            String ids[] = in.readLine().split(" ");
            
            for(int c = 0; c < ids.length; c++) {
                if(ids[c].equals("1")) {
                    tm.getTileView(l, c).getTile().setImage("res/sandstone.gif");
                }
                else if(ids[c].equals("2")) {
                    tm.getTileView(l, c).getTile().setImage("res/woodenchest.png");
                }
            }
            
            l++;
        }
        
        in.close();
        
        mGameWindow.setBaseTileMatrix(tm);
    }

    private GameWindow mGameWindow;
}

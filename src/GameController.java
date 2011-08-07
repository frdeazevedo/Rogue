import java.io.*;
import java.util.*;

public class GameController {
    public GameController() {
        mGameWindow = new GameWindow(500,500);
        mGameWindow.setVisible(true);
        
        mTerrainsIdFilePath = new HashMap<String, String>();
        mObstaclesIdFilePath = new HashMap<String, String>();
        mActivesIdFilePath = new HashMap<String, String>();
        
        try {
            loadConfigurations();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void loadBaseMatrix(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        String dimension[] = in.readLine().split(",");
        
        TileMatrix tm = new TileMatrix(Integer.parseInt(dimension[0]),
                                       Integer.parseInt(dimension[1]),
                                       "res/sprites/empty.png",
                                       "res/sprites/empty.png");
        
        int l = 0;
        while(in.ready()) {
            String ids[] = in.readLine().split(" ");
            
            for(int c = 0; c < ids.length; c++) {
                String filepath = mTerrainsIdFilePath.get(ids[c]);
                
                if(filepath != null) {
                    tm.getTileView(l, c).getTile().setImage(filepath);
                }
            }
            
            l++;
        }
        
        in.close();
        
        mGameWindow.setBaseTileMatrix(tm);
    }
    
    public void loadConfigurations() throws IOException {
        loadTerrains("res/config/terrains.txt");
        //loadObstacles("res/config/obstacles.txt");
        //loadActives("res/config/actives.txt");
    }
    
    private void loadTerrains(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        while(in.ready()) {
            String pair[] = in.readLine().split("=");
            
            mTerrainsIdFilePath.put(pair[0], pair[1]);
        }
        
        in.close();
    }
    
    private void loadObstacles(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        while(in.ready()) {
            String pair[] = in.readLine().split("=");
            
            mObstaclesIdFilePath.put(pair[0], pair[1]);
        }
        
        in.close();
    }
    
    private void loadActives(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        while(in.ready()) {
            String pair[] = in.readLine().split("=");
            
            mActivesIdFilePath.put(pair[0], pair[1]);
        }
        
        in.close();
    }
    
    private HashMap<String, String> mTerrainsIdFilePath;
    private HashMap<String, String> mObstaclesIdFilePath;
    private HashMap<String, String> mActivesIdFilePath;

    private GameWindow mGameWindow;
}

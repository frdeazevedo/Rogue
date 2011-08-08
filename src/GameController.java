import java.io.*;
import java.util.*;

public class GameController {
    public GameController(int width, int height) {
        mWidth = width;
        mHeight = height;
    
        mGameWindow = new GameWindow(mWidth, mHeight);
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
    
    public void loadObstacleMatrix(String filename) throws IOException {
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
                String filepath = mObstaclesIdFilePath.get(ids[c]);
                
                if(filepath != null) {
                    tm.getTileView(l, c).getTile().setImage(filepath);
                }
            }
            
            l++;
        }
        
        in.close();
        
        mGameWindow.setObstacleTileMatrix(tm);
    }
    
    public void loadActiveMatrix(String filename) throws IOException {
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
                String filepath = mActivesIdFilePath.get(ids[c]);
                
                if(filepath != null) {
                    tm.getTileView(l, c).getTile().setImage(filepath);
                }
            }
            
            l++;
        }
        
        in.close();
        
        mGameWindow.setActiveTileMatrix(tm);
    }
    
    public void loadConfigurations() throws IOException {
        loadTerrains("res/config/terrains.txt");
        loadObstacles("res/config/obstacles.txt");
        loadActives("res/config/actives.txt");
    }
    
    public void setBaseTileImage(int x, int y, String filename) {
        mGameWindow.getBaseTileMatrix().getTileView(x, y).getTile().setImage(filename);
    }
    
    public void setObstacleTileImage(int x, int y, String filename) {
        mGameWindow.getObstacleTileMatrix().getTileView(x, y).getTile().setImage(filename);
    }
    
    public void setActiveTileImage(int x, int y, String filename) {
        mGameWindow.getActiveTileMatrix().getTileView(x, y).getTile().setImage(filename);
    }
    
    public void setBaseTileImageRevealed(int x, int y, String filename) {
        mGameWindow.getBaseTileMatrix().getTileView(x, y).getTile().setRevealedImage(filename);
    }
    
    public void setObstacleTileImageRevealed(int x, int y, String filename) {
        mGameWindow.getObstacleTileMatrix().getTileView(x, y).getTile().setRevealedImage(filename);
    }
    
    public void setActiveTileImageRevealed(int x, int y, String filename) {
        mGameWindow.getBaseTileMatrix().getTileView(x, y).getTile().setRevealedImage(filename);
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
    
    private int mWidth;
    private int mHeight;
}

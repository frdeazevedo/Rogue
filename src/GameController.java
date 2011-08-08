import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GameController implements GameWindowListener {
    public GameController(int width, int height) {
        mWidth = width;
        mHeight = height;
        mMatricesXOffset = 0;
        mMatricesYOffset = 0;
    
        mGameWindow = new GameWindow(mWidth, mHeight);
        mGameWindow.setGameWindowListener(this);
        
        mTerrainsIdFilePath = new HashMap<String, String>();
        mObstaclesIdFilePath = new HashMap<String, String>();
        mActivesIdFilePath = new HashMap<String, String>();
        
        try {
            loadConfigurations();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        
        mGameWindow.setVisible(true);
    }
    
    @Override
    public void handleScrolling(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            mMatricesXOffset += mGameWindow.getActiveTileMatrix().getTileView(0, 0).getWidth();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            mMatricesXOffset -= mGameWindow.getActiveTileMatrix().getTileView(0, 0).getWidth();
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            mMatricesYOffset += mGameWindow.getActiveTileMatrix().getTileView(0, 0).getHeight();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            mMatricesYOffset -= mGameWindow.getActiveTileMatrix().getTileView(0, 0).getHeight();
        }
        
        mGameWindow.getBaseTileMatrix().resetLocations(mMatricesXOffset, mMatricesYOffset);
        
        if(mGameWindow.getActiveTileMatrix() != null) {
            mGameWindow.getActiveTileMatrix().resetLocations(mMatricesXOffset, mMatricesYOffset);
        }
        
        if(mGameWindow.getObstacleTileMatrix() != null) {
            mGameWindow.getObstacleTileMatrix().resetLocations(mMatricesXOffset, mMatricesYOffset);
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
    private int mMatricesXOffset;
    private int mMatricesYOffset;
}

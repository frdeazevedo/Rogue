import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class GameController implements GameWindowListener {
    public GameController(int width, int height) {
        mWidth = width;
        mHeight = height;
        mMatricesXOffset = 0;
        mMatricesYOffset = 0;
        mVariableImageSize = 0;
    
        mGameWindow = new GameWindow(mWidth, mHeight);
        
        mImageManager = ImageManager.getInstance();
        
        try {
            loadConfigurations();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        
        mGameWindow.setLocationRelativeTo(null);
        mGameWindow.setGameWindowListener(this);
        mGameWindow.setVisible(true);
    }
    
    @Override
    public void handleScrolling(KeyEvent e) {
        int px = mHero.getCoords().getX();
        int py = mHero.getCoords().getY();
        int x = px;
        int y = py;
    
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(x - 1 >= 0) x -= 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(x + 1 < mXTiles) x += 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            if(y - 1 >= 0) y -= 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(y + 1 < mYTiles) y += 1;
        }
        
        setActiveTileImage(px, py, null);
        
        if(x >= 0 && x < mXTiles && y >= 0 && y < mYTiles) {
            mHero.setCoords(x, y);
            setActiveTileImage(x, y, mHero.getImage());
        }
        
        //if((mXTiles * mVariableImageSize) + (mMatricesXOffset * mVariableImageSize) >= mGameWindow.getWidth() ||
        //   (mYTiles * mVariableImageSize) + (mMatricesYOffset * mVariableImageSize) >= mGameWindow.getHeight()) {
            mGameWindow.centerOnTile(x, y, mVariableImageSize, mVariableImageSize);
        //}
        
        //updateStates
        //processAI
    }
    
    public void centerScreenOnCoord(int x, int y) {
        mGameWindow.centerOnTile(x, y, mVariableImageSize, mVariableImageSize);
        mGameWindow.repaint();
    }
    
    public void setHero(Creature h) {
        mHero = h;
    }
    
    public void loadBaseMatrix(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        String dimension[] = in.readLine().split(",");
        
        mXTiles = Integer.parseInt(dimension[0]);
        mYTiles = Integer.parseInt(dimension[1]);
        
        TileMatrix tm = new TileMatrix(Integer.parseInt(dimension[0]),
                                       Integer.parseInt(dimension[1]),
                                       null,
                                       null);
        
        int l = 0;
        while(in.ready()) {
            String ids[] = in.readLine().split(" ");
            
            for(int c = 0; c < ids.length; c++) {
                tm.getTileView(l, c).getTile().setImage(mImageManager.getImage(ids[c]));
                tm.getTileView(l, c).resetLocation(mVariableImageSize, mVariableImageSize, mMatricesXOffset, mMatricesYOffset);
            }
            
            l++;
        }
        
        in.close();
        
        mGameWindow.setBaseTileMatrix(tm);
    }
    
    public void loadObstacleMatrix(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        String dimension[] = in.readLine().split(",");
        
        mXTiles = Integer.parseInt(dimension[0]);
        mYTiles = Integer.parseInt(dimension[1]);
        
        TileMatrix tm = new TileMatrix(Integer.parseInt(dimension[0]),
                                       Integer.parseInt(dimension[1]),
                                       null,
                                       null);
        
        int l = 0;
        while(in.ready()) {
            String ids[] = in.readLine().split(" ");
            
            for(int c = 0; c < ids.length; c++) {
                tm.getTileView(l, c).getTile().setImage(mImageManager.getImage(ids[c]));
                tm.getTileView(l, c).resetLocation(mVariableImageSize, mVariableImageSize, mMatricesXOffset, mMatricesYOffset);
            }
            
            l++;
        }
        
        in.close();
        
        mGameWindow.setObstacleTileMatrix(tm);
    }
    
    public void loadActiveMatrix(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        String dimension[] = in.readLine().split(",");
        
        mXTiles = Integer.parseInt(dimension[0]);
        mYTiles = Integer.parseInt(dimension[1]);
        
        TileMatrix tm = new TileMatrix(Integer.parseInt(dimension[0]),
                                       Integer.parseInt(dimension[1]),
                                       null,
                                       null);
        
        int l = 0;
        while(in.ready()) {
            String ids[] = in.readLine().split(" ");
            
            for(int c = 0; c < ids.length; c++) {
                tm.getTileView(l, c).getTile().setImage(mImageManager.getImage(ids[c]));
                tm.getTileView(l, c).resetLocation(mVariableImageSize, mVariableImageSize, mMatricesXOffset, mMatricesYOffset);
            }
            
            l++;
        }
        
        in.close();
        
        mGameWindow.setActiveTileMatrix(tm);
    }
    
    public void loadConfigurations() throws IOException {
        loadVariables("res/config/variables.txt");
        loadTerrains("res/config/terrains.txt");
        loadObstacles("res/config/obstacles.txt");
        loadActives("res/config/actives.txt");
    }
    
    public void setBaseTileImage(int x, int y, BufferedImage image) {
        mGameWindow.getBaseTileMatrix().getTileView(x, y).getTile().setImage(image);
    }
    
    public void setObstacleTileImage(int x, int y, BufferedImage image) {
        mGameWindow.getObstacleTileMatrix().getTileView(x, y).getTile().setImage(image);
    }
    
    public void setActiveTileImage(int x, int y, BufferedImage image) {
        mGameWindow.getActiveTileMatrix().getTileView(x, y).getTile().setImage(image);
        mGameWindow.getActiveTileMatrix().getTileView(x, y).repaint();
    }
    
    public void setBaseTileImageRevealed(int x, int y, BufferedImage image) {
        mGameWindow.getBaseTileMatrix().getTileView(x, y).getTile().setRevealedImage(image);
    }
    
    public void setObstacleTileImageRevealed(int x, int y, BufferedImage image) {
        mGameWindow.getObstacleTileMatrix().getTileView(x, y).getTile().setRevealedImage(image);
    }
    
    public void setActiveTileImageRevealed(int x, int y, BufferedImage image) {
        mGameWindow.getBaseTileMatrix().getTileView(x, y).getTile().setRevealedImage(image);
    }
    
    public void updateScreen() {
        mGameWindow.repaint();
    }
    
    private void loadVariables(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        while(in.ready()) {
            String pair[] = in.readLine().split("=");
            
            if(pair[0].equalsIgnoreCase("image_size")) {
                mVariableImageSize = Integer.parseInt(pair[1]);
            }
        }
    }
    
    private void loadTerrains(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        while(in.ready()) {
            String pair[] = in.readLine().split("=");
            
            mImageManager.putImage(pair[0], pair[1]);
        }
        
        in.close();
    }
    
    private void loadObstacles(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        while(in.ready()) {
            String pair[] = in.readLine().split("=");
            
            mImageManager.putImage(pair[0], pair[1]);
        }
        
        in.close();
    }
    
    private void loadActives(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        
        while(in.ready()) {
            String pair[] = in.readLine().split("=");
            
            mImageManager.putImage(pair[0], pair[1]);
        }
        
        in.close();
    }

    private GameWindow mGameWindow;
    
    private ImageManager mImageManager;
    
    private int mWidth;
    private int mHeight;
    private int mMatricesXOffset;
    private int mMatricesYOffset;
    private int mVariableImageSize;
    private int mXTiles;
    private int mYTiles;
    
    Creature mHero;
}

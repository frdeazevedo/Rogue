import java.awt.Container;
import java.awt.image.*;
import javax.imageio.*;

public class TileMatrix {
    public TileMatrix(int lines, int columns, BufferedImage baseImage, BufferedImage baseRevealedImage) {
        mTiles = new TileView[lines][columns];
        
        for(int i = 0; i < lines; i++) {
            for(int j = 0; j < columns; j++) {
                mTiles[i][j] = new TileView(baseImage, baseRevealedImage);
                mTiles[i][j].getTile().getCoords().setX(i);
                mTiles[i][j].getTile().getCoords().setY(j);
                mTiles[i][j].getTile().setVisible(true);
                mTiles[i][j].resetLocation(0, 0);
            }
        }
    }
    
    public void addToContainer(Container c) {
        for(int i = 0; i < mTiles.length; i++) {
            for(int j = 0; j < mTiles[i].length; j++) {
                c.add(mTiles[i][j]);
            }
        }
    }
    
    public void removeFromContainer(Container c) {
        for(int i = 0; i < mTiles.length; i++) {
            for(int j = 0; j < mTiles[i].length; j++) {
                c.remove(mTiles[i][j]);
            }
        }
    }
    
    public void resetLocations(int w, int h, int x, int y) {
        for(int i = 0; i < mTiles.length; i++) {
            for(int j = 0; j < mTiles[i].length; j++) {
                mTiles[i][j].resetLocation(w, h, x, y);
            }
        }
    }
    
    public TileView getTileView(int x, int y) {
        return mTiles[x][y];
    }
    
    public int getWidth() {
        return mTiles[0][0].getWidth() * mTiles.length;
    }
    
    public int getHeight() {
        return mTiles[0][0].getHeight() * mTiles[0].length;
    }

    protected TileView mTiles[][];
}

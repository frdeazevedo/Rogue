import java.awt.*;
import javax.swing.*;

public class TileView extends JPanel {
    public TileView(String filename, String revealedFilename) {
        setLayout(null);
        
        mTile = new Tile(filename, revealedFilename);
        
        setSize(mTile.getImage().getWidth(), mTile.getImage().getHeight());
        setLocation(mTile.getCoords().getX() * mTile.getImage().getWidth(),
                    mTile.getCoords().getY() * mTile.getImage().getHeight());
    }
    
    public void resetLocation(int offsetX, int offsetY) {
        setLocation(mTile.getCoords().getX() * mTile.getImage().getWidth() + offsetX,
                    mTile.getCoords().getY() * mTile.getImage().getHeight() + offsetY);
    }
    
    @Override
    public int getWidth() {
        return mTile.getImage().getWidth();
    }
    
    @Override
    public int getHeight() {
        return mTile.getImage().getHeight();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if(mTile.isVisible() || mTile.isRevealed()) {
            mTile.draw(g);
        }
        else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
    
    public void setTileVisible(boolean visible) {
        mTile.setVisible(visible);
    }
    
    public void setRevealed(boolean revealed) {
        mTile.setRevealed(revealed);
    }
    
    public Tile getTile() {
        return mTile;
    }

    private Tile mTile;
}

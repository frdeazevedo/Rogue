import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class TileView extends JPanel {
    public TileView(BufferedImage image, BufferedImage revealedImage) {
        setLayout(null);
        
        mTile = new Tile(image, revealedImage);
        
        if(revealedImage != null && image != null) {
            setSize(mTile.getImage().getWidth(), mTile.getImage().getHeight());
            setLocation(mTile.getCoords().getX() * mTile.getImage().getWidth(),
                        mTile.getCoords().getY() * mTile.getImage().getHeight());
        }
    }
    
    public void resetLocation(int offsetX, int offsetY) {
        if(mTile.getImage() != null) {
            setLocation(mTile.getCoords().getX() * mTile.getImage().getWidth() + offsetX,
                        mTile.getCoords().getY() * mTile.getImage().getHeight() + offsetY);
        }
    }
    
    public void resetLocation(int w, int h, int offsetx, int offsety) {
        //System.out.println("T: " + (mTile.getCoords().getX() * w + offsetx) + "\nL: " + (mTile.getCoords().getY() * h + offsety));
        System.out.println("w = " + w + " h = " + h + " (" + offsetx + "," + offsety + ")");
        setLocation(mTile.getCoords().getX() * w + offsetx,
                    mTile.getCoords().getY() * h + offsety);
    }
    
    @Override
    public int getWidth() {
        if(mTile.getImage() == null) {
            return 0;
        }
    
        return mTile.getImage().getWidth();
    }
    
    @Override
    public int getHeight() {
        if(mTile.getImage() == null) {
            return 0;
        }
    
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

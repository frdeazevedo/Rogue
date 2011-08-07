import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile {
    public Tile(String filename, String revealedFilename) {
        setImage(filename);
        setRevealedImage(revealedFilename);
        setCoords(0, 0);
        setVisible(false);
        setRevealed(false);
    }
    
    public Tile(String filename, String revealedFilename, int x, int y) {
        setImage(filename);
        setRevealedImage(revealedFilename);
        setCoords(x, y);
        setVisible(false);
        setRevealed(false);
    }
    
    public Tile(String filename, String revealedFilename, int x, int y, boolean visible, boolean revealed) {
        setImage(filename);
        setRevealedImage(revealedFilename);
        setCoords(x, y);
        setRevealed(revealed);
        setVisible(visible);
    }

    public void setImage(String filename) {
        try {
            mImage = ImageIO.read(new File(filename));
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public void setRevealedImage(String filename) {
        try {
            mRevealedImage = ImageIO.read(new File(filename));
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public void setCoords(int x, int y) {
        mCoord = new Coord2(x, y);
    }
    
    public Coord2 getCoords() {
        return mCoord;
    }
    
    public void setVisible(boolean visible) {
        if(visible) {
            setRevealed(true);
        }
    
        mVisible = visible;
    }
    
    public boolean isVisible() {
        return mVisible;
    }
    
    public void setRevealed(boolean revealed) {
        mRevealed = revealed;
    }
    
    public boolean isRevealed() {
        return mRevealed;
    }

    public void draw(Graphics g) {
        if(isVisible() && mImage != null) {
            g.drawImage(mImage, 0, 0, null);
        }
        else if(isRevealed() && mRevealedImage != null) {
            g.drawImage(mRevealedImage, 0, 0, null);
        }
    }
    
    public BufferedImage getImage() {
        return mImage;
    }
    
    public BufferedImage getRevealedImage() {
        return mRevealedImage;
    }

    protected boolean mVisible;
    protected boolean mRevealed;
    protected Coord2 mCoord;
    protected BufferedImage mImage;
    protected BufferedImage mRevealedImage;
}

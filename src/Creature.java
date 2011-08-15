import java.awt.image.*;

public class Creature {
    public Creature() {
       mCoord = new Coord2();
       mImage = null;
    }

    public Coord2 getCoords() {
        return mCoord;
    }
    
    public void setCoords(int x, int y) {
        mCoord.setX(x);
        mCoord.setY(y);
    }
    
    public void setImage(BufferedImage image) {
        mImage = image;
    }
    
    public BufferedImage getImage() {
        return mImage;
    }

    private BufferedImage mImage;
    private Coord2 mCoord;
}
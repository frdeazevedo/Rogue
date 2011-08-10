import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class ImageManager {
    private static ImageManager mInstance = null;
    
    private ImageManager() {
        mHashMapImages = new HashMap<String, BufferedImage>();
    }
    
    public static ImageManager getInstance() {
        if(mInstance == null) {
            return new ImageManager();
        }
        
        return mInstance;
    }
    
    public void putImage(String keyname, String filename) throws IOException {
        if(!mHashMapImages.containsKey(keyname)) {
            mHashMapImages.put(keyname, ImageIO.read(new File(filename)));
        }
    }
    
    public BufferedImage getImage(String keyname) {
        return (BufferedImage)mHashMapImages.get(keyname);
    }
    
    public void removeImage(String keyname) {
        mHashMapImages.remove(keyname);
    }
    
    private HashMap<String, BufferedImage> mHashMapImages;
}
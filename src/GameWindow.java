import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

public class GameWindow extends JFrame
                        implements KeyListener {
    public GameWindow(int width, int height) {
        super();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/icon64.png"));
        
        mTileMatrixXOffset = 0;
        mTileMatrixYOffset = 0;
        
        mViewport = new JPanel();
        mViewport.setLayout(null);
        mViewport.setSize(width, (int)(0.8 * height));
        mViewport.setBackground(Color.BLACK);
        
        getContentPane().add(mViewport);
        
        setLayout(null);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.GRAY);
        addKeyListener(this);
        getContentPane().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        mGameWindowListener.handleScrolling(e);
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public void setBaseTileMatrix(TileMatrix base) {
        if(mTileMatrix != null) {
            mTileMatrix.removeFromContainer(mViewport);
        }
    
        mTileMatrix = base;
        mTileMatrix.addToContainer(mViewport);
        
        repaint();
    }
    
    public void centerOnTile(int x, int y, int tw, int th) {
        int w = (int)mViewport.getSize().getWidth();
        int h = (int)mViewport.getSize().getHeight();
        int a = x * tw;
        int b = y * th;
        
        mObstacleTileMatrix.resetLocations(tw, th, w/2-a, h/2-b);
        mTileMatrix.resetLocations(tw, th, w/2-a, h/2-b);
        mActiveTileMatrix.resetLocations(tw, th, w/2-a, h/2-b);
    }
    
    public TileMatrix getBaseTileMatrix() {
        return mTileMatrix;
    }
    
    public void setObstacleTileMatrix(TileMatrix obstacle) {
        if(mObstacleTileMatrix != null) {
            mObstacleTileMatrix.removeFromContainer(mViewport);
        }
        
        mObstacleTileMatrix = obstacle;
        mObstacleTileMatrix.addToContainer(mViewport);
        
        repaint();
    }
    
    public TileMatrix getObstacleTileMatrix() {
        return mObstacleTileMatrix;
    }
    
    public void setActiveTileMatrix(TileMatrix active) {
        if(mActiveTileMatrix != null) {
            mActiveTileMatrix.removeFromContainer(mViewport);
        }
        
        mActiveTileMatrix = active;
        mActiveTileMatrix.addToContainer(mViewport);
        
        repaint();
    }
    
    public TileMatrix getActiveTileMatrix() {
        return mActiveTileMatrix;
    }
    
    public JPanel getGameViewport() {
        return mViewport;
    }
    
    public void setGameWindowListener(GameWindowListener listener) {
        mGameWindowListener = listener;
    }
    
    JPanel mViewport;
    
    TileMatrix mTileMatrix;
    TileMatrix mObstacleTileMatrix;
    TileMatrix mActiveTileMatrix;
    
    GameWindowListener mGameWindowListener;
    
    int mTileMatrixXOffset;
    int mTileMatrixYOffset;
}

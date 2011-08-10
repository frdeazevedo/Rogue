import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GameWindow extends JFrame
                        implements KeyListener {
    public GameWindow(int width, int height) {
        super();
        
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

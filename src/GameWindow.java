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
        handleScrolling(e);
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
    
    private void handleScrolling(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            mTileMatrixXOffset += mTileMatrix.getTileView(0, 0).getWidth();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            mTileMatrixXOffset -= mTileMatrix.getTileView(0, 0).getWidth();
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            mTileMatrixYOffset += mTileMatrix.getTileView(0, 0).getHeight();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            mTileMatrixYOffset -= mTileMatrix.getTileView(0, 0).getHeight();
        }
        
        mTileMatrix.resetLocations(mTileMatrixXOffset, mTileMatrixYOffset);
        
        if(mActiveTileMatrix != null) {
            mActiveTileMatrix.resetLocations(mTileMatrixXOffset, mTileMatrixYOffset);
        }
        
        if(mObstacleTileMatrix != null) {
            mObstacleTileMatrix.resetLocations(mTileMatrixXOffset, mTileMatrixYOffset);
        }
    }
    
    JPanel mViewport;
    
    TileMatrix mTileMatrix;
    TileMatrix mObstacleTileMatrix;
    TileMatrix mActiveTileMatrix;
    
    int mTileMatrixXOffset;
    int mTileMatrixYOffset;
}

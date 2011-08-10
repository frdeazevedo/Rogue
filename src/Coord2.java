public class Coord2 {
    public Coord2() {
        mX = 0;
        mY = 0;
    }
    
    public Coord2(int x, int y) {
        mX = x;
        mY = y;
    }
    
    public void setX(int x) {
        mX = x;
    }
    
    public void setY(int y) {
        mY = y;
    }
    
    public int getX() {
        return mX;
    }
    
    public int getY() {
        return mY;
    }
    
    @Override
    public String toString() {
        return mX + ", " + mY;
    }

    private int mX;
    private int mY;
}

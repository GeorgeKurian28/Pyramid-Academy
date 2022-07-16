public class TreasureChest {
    private int points;
    private int positionX;
    private int positionY;

    public TreasureChest(int positionX, int positionY){
        this(100, positionX,positionY);
    }

    public TreasureChest(int points, int positionX, int positionY){
        this.points = points;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int[] getPositionX() {
        int [] arr = {positionX, positionY};
        return arr;
    }
}

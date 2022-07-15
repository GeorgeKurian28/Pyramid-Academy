public class Drops {
    private int num;
    private int positionX;
    private int positionY;
    public Drops(int num,int positionX, int positionY){
        this.num = num;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Drops(int positionX, int positionY){
        this(20,positionX,positionY);
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

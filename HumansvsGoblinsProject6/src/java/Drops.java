public class Drops {
    /****************
     *
     * @param num          -Integer value of the number of drops
     * @param positionX    -Integer value of the X coordinate of the position
     * @param positionY    -Integer value of the Y coordinate of the position
     */
    private int num;
    private int positionX;
    private int positionY;

    /****************
     * There parameter constructor
     *
     * @param num
     * @param positionX
     * @param positionY
     */
    public Drops(int num,int positionX, int positionY){
        this.num = num;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /************
     * Two parameter constructor
     *
     * @param positionX
     * @param positionY
     */
    public Drops(int positionX, int positionY){
        this(20,positionX,positionY);
    }

    /****************
     * GetNum method is a getter for teh number of drops
     *
     * @return the number of drops an Integer
     */
    public int getNum() {
        return num;
    }

    /**************
     * SetNum is a setter method for the number of drops
     *
     * @param num is the number of drops that have to be updated
     */
    public void setNum(int num) {
        this.num = num;
    }
}

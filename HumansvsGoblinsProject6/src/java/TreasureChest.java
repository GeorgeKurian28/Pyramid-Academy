public class TreasureChest {

    /********************
     * The treasure Chest class which is the made for the random treasrues that will be created each time a combat occurs
     *
     * @param -points     -Integer variable that stores the points for each Chest
     * @param -positionX  -Integer variable that stores the X coordinate of reach Chest
     * @param -positionY  -Integer variable that stores the Y coordinate of reach Chest
     */
    private int points;
    private int positionX;
    private int positionY;

    /*************
     * Two argument constructor that only takes the position and give a value of 100 for the points
     *
     * @param -positionX  -Integer variable that stores the X coordinate of reach Chest
     * @param -positionY  -Integer variable that stores the Y coordinate of reach Chest
     */
    public TreasureChest(int positionX, int positionY){
        this(100, positionX,positionY);
    }

    /************************
     * Three argument constructor that only takes the position and the number of points
     *
     * @param -points     -Integer variable that stores the points for each Chest
     * @param -positionX  -Integer variable that stores the X coordinate of reach Chest
     * @param -positionY  -Integer variable that stores the Y coordinate of reach Chest
     */
    public TreasureChest(int points, int positionX, int positionY){
        this.points = points;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /*********************
     * the Getpoints method is a getter that get the points in the chest
     *
     * @return the integer variable containing the number of points
     */
    public int getPoints() {
        return points;
    }

    /*********************
     * Setter method to set the points
     *
     * @param -points     -Integer variable that stores the points for each Chest
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /*****************
     * Getter method to get the position of the chest
     *
     * @return an array that contains the X and Y coordinate of the position
     */
    public int[] getPosition() {
        int [] arr = {positionX, positionY};
        return arr;
    }
}

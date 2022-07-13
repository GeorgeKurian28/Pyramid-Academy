public class Human {
    /******
     * The Human Object has these attributes and methods,
     *
     * @param -health    -Integer value that stores the health of the human
     * @param -cnt       -Static Integer that is used to set the id for all the humans created
     * @param -iD        -Integer that stores the iD of the human
     * @param -positionX -Integer stores the x coordinate of the position in the Grid
     * @param -positionY -Integer stores the Y coordinate of the position in the Grid
     */
    private int health;
    private static int cnt = 1;
    private int iD;
    private int positionX = 0, positionY = 0;

    /*************
     * The GetID methid return the Id of the human
     *
     * @return returns the Integer value which is the Id of the human
     */
    public int getiD(){  return iD;    }

    /*****
     * The two argument constructor for the Human object
     *
     * The ID and health are fixed but can be modified as needed
     */
    public Human(int positionX, int positionY){
        iD = cnt ++;
        health = 110;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void attackGoblin(Goblin goblin){
        if(getHealth() > goblin.getHealth()) {
            goblin.setHealth(goblin.getHealth() - 10);
        }
        else
            setHealth(getHealth()-5);
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        if(this.health > 0)
            this.health = health;
    }

    /******
     * Setterfor the position variables they take two values and return two values
     *
     * @param -positionX   - int the X potion coordinate
     * @param -positionY   - int the Y position coordinate
     */
    public void setPostion(int  positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /******
     * Getter for the position variables they take two values and return two values
     *
     * @param -position     - int[] the postion coordinates
     * @return returns the potion arrat with the X yariable at index 0 and the Y variable at index 1
     */
    public int[] getPosition(){
        int[] position = {positionX,positionY};
        return position;
    }

    public boolean isDead(){
        return getHealth() <= 0;
    }

    @Override
    public String toString(){
        return "Human - " + this.iD + "\nHealth - " + this.health + "\n Position (x,y)  - ("+this.positionX+","+this.positionY+")";
    }
}

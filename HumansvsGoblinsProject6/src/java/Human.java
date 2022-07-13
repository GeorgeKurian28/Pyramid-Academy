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

    /*****
     * The two argument constructor for the Human object
     *
     * This calls the constructor with three arguments
     */
    public Human(int positionX, int positionY){
        this(positionX,positionY,cnt++);//calls the 3 argument constructor
    }

    /*****
     * The three argument constructor for the Human object
     *
     * This calls the four argument constructor
     */
    public Human(int positionX, int positionY, int iD){
        this(positionX,positionY,110,iD);//calls the 4 argument constructor
    }

    /*****
     * The four argument constructor for the Human object
     *
     */
    public Human(int positionX, int positionY, int health, int iD){
        this.iD = iD;
        this.health = health;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /*************
     * The GetID methid return the Id of the human(Getter)
     *
     * @return returns the Integer value which is the Id of the human
     */
    public int getiD(){  return iD;    }

    /*****
     * The attackGoblin method takes the Goblin object and compares the health og the goblin and the human, and the attack is based on their health
     *
     * @return The goblin object is returned at a different state than what it came in if it was weaker than the human
     */
    public Goblin attackGoblin(Goblin goblin){
        if(getHealth() > goblin.getHealth()) {
            goblin.setHealth(goblin.getHealth() - 10);
        }
        else
            setHealth(getHealth()-5);
        return goblin;
    }

    /*************
     * The Getter method getHealth() returns the health of the human
     *
     * @return returns the Integer value of the health of the human
     */
    public int getHealth(){
        return health;
    }

    /*************
     * The Setter method setHealth() returns the health of the human, takes a integer value helath that updates the health
     *
     */
    public void setHealth(int health){
        if(this.health > 0 & health > 0)//only if the human is alive we can improve his health
            this.health = health;
        else
            this.health = 0;//The human is dead so the health will always be 0
    }

    /******
     * Setterfor the position variables they take two values and validates the values with the grid size and
     * throws illegal argumant exception if the positions are outside the grid size
     *
     * @param -positionX   - int the X potion coordinate
     * @param -positionY   - int the Y position coordinate
     */
    public void setPosition (int  positionX, int positionY, int[] matrix){
        if (positionX >= 0  && positionX < matrix[0])
            this.positionX = positionX;
        else
            throw new IllegalArgumentException("Position X has to be greater than 0  and less than "+ matrix[0]);
        if (positionY >= 0  && positionY < matrix[1])
            this.positionY = positionY;
        else
            throw new IllegalArgumentException("Position Y has to be greater than 0  and less than "+ matrix[1]);
    }

    /******
     * Getter for the position variables that returns two values
     *
     * @param -position     - int[] the postion coordinates
     * @return returns the potion arrat with the X yariable at index 0 and the Y variable at index 1
     */
    public int[] getPosition(){
        int[] position = {positionX,positionY};
        return position;
    }

    /*************
     * The Getter method isDead() returns if the human is dead or alive
     *
     * @return returns the boolean value true if the human is dead
     */
    public boolean isDead(){
        return getHealth() <= 0;
    }

    @Override
    public String toString(){
        return "Human - " + this.iD + "\nHealth - " + this.health + "\n Position (x,y)  - ("+this.positionX+","+this.positionY+")";
    }
}

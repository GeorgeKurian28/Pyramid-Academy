public class Goblin {
    /******
     * The Goblin Object has these attributes and methods,
     *
     * @param -health    -Integer value that stores the health of the human
     * @param -cnt       -Static Integer that is used to set the id for all the humans created
     * @param -iD        -Integer that stores the iD of the human
     * @param -positionX -Integer stores the x coordinate of the position in the Grid
     * @param -positionY -Integer stores the Y coordinate of the position in the Grid
     */
    Human human;
    private int health = 100;
    private static int cnt = 1;
    private int iD;
    private int drops;
    private int positionX = 0, positionY = 0;

    /*****
     * The two argument constructor for the Goblin object
     *
     * This calls the constructor with three arguments
     */
    public Goblin(int positionX, int positionY) {
        this(positionX,positionY,cnt++);
    }

    /*****
     * The three argument constructor for the Goblin object
     *
     * This calls the four argument constructor
     */
     public Goblin(int positionX, int positionY, int iD){
         this(positionX,positionY,iD,100);
     }

    /*****
     * The four argument constructor for the Goblin object
     *
     */
     public Goblin(int positionX,int positionY, int iD, int health){
         this.iD = iD;
         this.health = health;
         this.positionX = positionX;
         this.positionY = positionY;
         drops = health/5;
     }

    /*************
     * The GetID methid return the Id of the human(Getter)
     *
     * @return returns the Integer value which is the Id of the human
     */
    public int getiD(){
        return iD;
    }

    /*****
     * The attackHuman method takes the Human object and compares the health of the goblin and the human, and the attack is based on their health
     *
     * @return The human object is returned at a different state than what it came in if it was weaker than the goblin
     */
    public Human attackHuman(Human human){
        if(getHealth() > human.getHealth()) {
            human.setHealth(human.getHealth() - 10);
            setHealth(getHealth() + 5);
        }
        else{
            human.setHealth(human.getHealth() - 5);
        }
        return human;
    }

    /******
     * Setterfor the position variables they take two values and validates the values with the grid size and
     * throws illegal argumant exception if the positions are outside the grid size
     *
     * @param -positionX   - int the X potion coordinate
     * @param -positionY   - int the Y position coordinate
     */
    public void setPosition(int  positionX, int positionY, int[] matrix){
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
     * Getter for the position variables that returns the  position coordinates
     *
     * @param -position     - int[] the postion coordinates
     * @return returns the potion array with the X variable at index 0 and the Y variable at index 1
     */
    public int[] getPosition(){
        int[] position = {positionX,positionY};
        return position;
    }

    /*************
     * The Getter method getHealth() returns the health of the Goblin
     *
     * @return returns the Integer value of the health of the goblin
     */
    public int getHealth(){
        return health;
    }

    /*************
     * The Setter method setHealth() returns the health of the goblin, takes a integer value helath that updates the health
     *
     */
    public void setHealth(int health){
        if(this.health > 0)
            this.health = health;
    }

    /*************
     * The Getter method isDead() returns if the GOBLIN is dead or alive
     *
     * @return returns the boolean value true if the gobin is dead
     */
    public boolean isDead(){
        return getHealth() <= 0;
    }

    /*************
     * The getPrey method takes the list of the humans and the land grid to determine its prey based on the distance
     *
     * @param   -human     -Human Ohject that will be returned is the prey for that goblin
     * @param   -oldDist   -This Integer variable stores the distance it is inititated with the longest distance possible
     *                      in the grid the diagonal distance
     * @param   -newDist   -This Integer variable is used to swap the distance values to eventually reach the shortest distance.
     * @return returns the Human object that is closest to the goblin which will be its prey
     */
    public Human getPrey(Human[] humans, Land land){
        Human human = humans[0];
        int oldDist = getDistance(land.getGridSize(), new int[2]) + 2;
        int newDist;
        for(Human human1 : humans){
            if(!human1.isDead()){
                newDist = getDistance(human1.getPosition(), this.getPosition());
                if( newDist < oldDist){
                    oldDist = newDist;
                    human = human1;
                }
            }
        }
        return human;
    }

    /*************
     * The getDistance method takes the human position and the goblin position as the input variables
     *
     * @return returns the integer value that is the distance between the two
     */
    public int getDistance(int[] humanPos, int[] goblinPos){
        return (int)(Math.sqrt( (humanPos[0]-goblinPos[0])*(humanPos[0]-goblinPos[0]) + (humanPos[1]-goblinPos[1])*(humanPos[1]-goblinPos[1]) ));
    }

    /*************
     * The getDrops method returns the number of drops available in the Goblin
     *
     * @return returns the integer value that is health/5
     */
    public int getDrops(){ return getHealth()/5; }

    @Override
    public String toString(){
        return "Goblin - " + this.iD + "\nHealth - " + this.health + "\n Position (x,y)  - ("+this.positionX+","+this.positionY+")";

    }


}

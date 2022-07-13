public class Goblin {

    Human human;
    private int health = 100;
    private static int cnt = 1;
    private int iD;
    private int positionX = 0, positionY = 0;

    private Human prey;

    public int getiD(){
        return iD;
    }

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
     }

    public void attackHuman(Human human){
        if(getHealth() > human.getHealth()) {
            human.setHealth(human.getHealth() - 10);
            setHealth(getHealth() + 5);
        }
        else{
            human.setHealth(human.getHealth() - 5);
            human.setHealth(human.getHealth() - 5);
        }
    }

    /******
     * Setterfor the position variables they take two values and return two values
     *
     * @param -positionX   - int the X potion coordinate
     * @param -positionY   - int the Y position coordinate
     */
    public void setPosition(int  positionX, int positionY){
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
    public int getHealth(){
        return health;
    }

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

    public int getDistance(int[] humanPos, int[] goblinPos){
        return (int)(Math.sqrt( (humanPos[0]-goblinPos[0])*(humanPos[0]-goblinPos[0]) + (humanPos[1]-goblinPos[1])*(humanPos[1]-goblinPos[1]) ));
    }

    public void setHealth(int health){
        if(health < 0)
            System.out.println("Cant move for " + Math.abs(health) + "time");
        this.health = health;
    }

    public boolean isDead(){
        return getHealth() <= 0;
    }

    @Override
    public String toString(){
        return "Goblin - " + this.iD + "\nHealth - " + this.health + "\n Position (x,y)  - ("+this.positionX+","+this.positionY+")";

    }


}

import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<String,Integer> weapons = new HashMap<>();
    private Weapon weapon;
    private static int points = 0;

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
     * The attackGoblin method takes the Goblin object and compares the health of the goblin and the human, and the attack is based on their health
     * But if the human has a weapon then depending on the strength of the weapon the intensity if the attack increases
     *
     * @return The goblin object is returned at a different state than what it came in if it was weaker than the human
     */
    public Goblin attackGoblin(Goblin goblin){
        if(hasWeaponInHand()){
            if(this.weapon.getStrength() >= 40){
                goblin.setHealth(0);
                this.weapon = null;
            }
            else if(this.weapon.getStrength() > 20){
                goblin.setHealth(goblin.getHealth() - 20);
                this.weapon.setStrength(this.weapon.getStrength() - 20);
            }
        }
        else if(getHealth() > goblin.getHealth()) {
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

    /*************
     * The method takeInventory() adds the health from the inventory to the human health and adds the weapons to the humans inventory
     *
     *
     */
    public void takeInventory(Inventory inventory){
        this.setHealth(getHealth()+inventory.getHealth());// add to human health
        this.takeWeapons(inventory.getWeapons());// add weapons to the weapons stock
    }

    /*************
     * The method takeWeapons() updates the weapon for the human to use
     *
     */
    public void takeWeapons(HashMap<String,Integer> weapons) {
        if(this.weapons.isEmpty())
            this.weapons = weapons;
        else{
            for(String name: weapons.keySet()) {
                if(this.weapons.containsKey(name))
                    this.weapons.put(name, this.weapons.get(name) + weapons.get(name));// If the weapon is present increase its strength with the new amount
                else
                    this.weapons.put(name,weapons.get(name));
                if(this.weapon != null & this.weapon.getName().equals(name)){
                    this.weapon.setStrength(this.weapon.getStrength()+ weapons.get(name));
                }
            }
        }

    }

    /*************
     * The method hasWeaponsInStock() returns boolean true if the human has weapons
     *
     * @return returns a boolean true if the human has weapons
     */
    public boolean hasWeaponInStock(){
        return !(weapons.isEmpty());
    }

    /*************
     * The method hasWeaponInHand() returns boolean true if the human has weapon in hand
     *
     * @return returns a boolean true if the human has weapons
     */
    public boolean hasWeaponInHand(){
        return (weapon != null);
    }

    /*******************
     * getWeaponInHand method return String the name of the weapon in hand and its strength
     *
     */
    public String getWeaponInHand(){
        return("  Name : - "+this.weapon.getName() + " Strength :- " + this.weapon.getStrength());
    }

    /*******************
     * getWeaponInHand method return String the name of the weapon in hand and its strength
     *
     */
    public HashMap<String,Integer> getWeaponInStock(){
         return weapons;
    }

    /*************
     * The method useWeapons() will put a weapon in the hand of the human, if he has the weapon in stock, if he is already
     * holding a weapon then the present weapon goes back in stock and he gets the new weapon,
     * but he doesnot have the weapon in stock he gets the message that he doesnot have it
     *
     *
     */
    public void useWeapon(String weapon){

            if (weapons.containsKey(weapon)) {
                if(this.weapon != null)
                    weapons.put(this.weapon.getName(),this.weapon.getStrength());
                this.weapon = new Weapon(weapon, weapons.remove(weapon));
            }
            else
                System.out.println("You don't have this weapon !!!!!!");

    }

    public void setPoints(int points) {
        if(points > 0)
            this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString(){
        return "Human - " + this.iD + "\nHealth - " + this.health + "\n Position (x,y)  - ("+this.positionX+","+this.positionY+")";
    }
}

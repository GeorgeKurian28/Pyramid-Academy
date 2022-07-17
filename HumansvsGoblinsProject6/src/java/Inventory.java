import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    /******
     * The Goblin Object has these attributes and methods,
     *
     * @param -health    -Integer value that stores the health in the inventory
     * @param -weapons   -HashMap with the name of the weapon as the key and the strength of the weapon as an integer
     * @param -location  -Integer array that stores the x and y coordinate of the position in the Grid
     */
    private int health;
    private HashMap<String, Integer> weapons = new HashMap<>();
    private int[] location;

    /*****
     * The four argument constructor for the Inventory object
     *
     */
    public Inventory(int positionX, int positionY,int health, String ...arr){
        this.health = health;
        setLocation(positionX,positionY);
        for(int  i = 0 ; i < arr.length; i += 2)
            this. weapons.put(arr[i],Integer.parseInt(arr[i+1]));
    }

    /*****
     * The three argument constructor for the Inventory object
     *
     * This calls the constructor with four arguments
     */
    public Inventory(int positionX, int positionY, String ...arr){
        this(positionX,positionY,50,arr);
    }

    /*****
     * The two argument constructor for the Inventory object
     *
     * This calls the constructor with three arguments
     */
    public Inventory(int positionX, int positionY){
        this(positionX,positionY,"Gun","20","Grenade","40");
    }

    /*****
     * The getHealth Method returns the health in the inventory in the cell
     *
     * @return returns the int variable health
     */
    public int getHealth() {
        return health;
    }

    /*****
     * The getLocation method return the location of the inventory
     *
     * @return returns the location which is an integer array
     */
    public int[] getLocation() {
        return location;
    }

    /**************
     * The setlocation method sets the location of the inventory
     *
     * @param positionX -Integer value that contains the x value of the position
     * @param positionY -Integer value that contains the y value of the position
     */
    public void setLocation(int positionX, int positionY) {
        int[] pos = {positionX,positionY};
        this.location = pos;
    }

    /**************
     * The setWeapons method adds a new weapon to the stack and if the weapon is already present increases updates its strength
     *
     * @param weapon
     * @param strength
     */
    public void setWeapons(String weapon, int strength ){
        if(weapons.containsKey(weapon))
            weapons.put(weapon,weapons.get(weapon)+strength);
        else
            weapons.put(weapon,strength);
    }

    /***************
     * The getWeapons method, returns a HashMap of the wepons in the location
     *
     * @return returns a HashMap of weapons
     */
    public HashMap<String, Integer> getWeapons(){
        return weapons;
    }

    @Override
    public String toString(){
        return "Health : - " + this.health + "  Locaton : - ("+ this.location[0] +"," + this.location[1]+")" + weapons.toString();
    }
}

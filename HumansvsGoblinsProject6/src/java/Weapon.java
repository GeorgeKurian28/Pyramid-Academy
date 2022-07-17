public class Weapon {
    /******
     * The Weapon Object has these attributes and methods,
     *
     * @param -strength    -Integer value that stores the strength in the weapon
     * @param -name        -String value that stores the name of the weapon
     */
    private String name;
    private int strength;

    /*****************
     * Two Argument constructor for Weapons class
     *
     * @param name      -String value Stores the name of the weapon
     * @param strength  -Integer values stores the strength of the weapon
     */
    public Weapon(String name, int strength){
        this.name = name;
        this.strength = strength;
    }

    /************
     * Getter method to get the name of the weapon
     *
     * @return returns a String value of the name of the weapon
     */
    public String getName() {
        return name;
    }

    /******************
     * Setter method to set the strength of the weapon
     *
     * @param strength Integer value that stores the strength of the weapon
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /*********************
     * Getter method that returns the strength of the weapon
     *
     * @return returns an integer value strength of the weapon
     */
    public int getStrength() {
        return strength;
    }

    @Override
    public String toString(){
        return "Name  : - " + this.name + "** Strength :- "+this.strength;
    }
}

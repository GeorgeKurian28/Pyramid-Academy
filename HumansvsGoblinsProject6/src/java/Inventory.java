import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private int health;
    //private ArrayList<Weapon> weapons = new ArrayList<>();

    private HashMap<String, Integer> weapons = new HashMap<>();
    private int[] location;

    public Inventory(int positionX, int positionY){
        health = 50;
        setLocation(positionX,positionY);
        weapons.put("Gun",20);
        weapons.put("Grenade",40);
        //weapons.add(new Weapon("Gun",20));
        //weapons.add(new Weapon("Grenade", 10));
    }

    public int getHealth() {
        return health;
    }

    public void setQuantity(int health) {
        this.health = health;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int positionX, int positionY) {
        int[] pos = {positionX,positionY};
        this.location = pos;
    }

    public void setWeapons(String weapon, int strength ){
        weapons.put(weapon,strength);
    }

    public HashMap<String, Integer> getWeapons(){
        return weapons;
    }

    @Override
    public String toString(){
        return "Health : - " + this.health + "  Locaton : - ("+ this.location[0] +"," + this.location[1]+")" + weapons.toString();
    }
}

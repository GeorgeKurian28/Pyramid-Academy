public class Weapon {
    private String name;
    private int strength;

    public Weapon(String name, int strength){
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String toString(){
        return "Name  : - " + this.name + "** Strength :- "+this.strength;
    }
}

public class Inventory {

    private Weapon weapon;
    private Armor armor;
    private boolean water,firewood,food;



    public Inventory() {
        this.weapon = new Weapon("yumruk",-1,0,0);
        this.armor = new Armor(-1,"paçavra",0,0);
        this.water = water;
        this.firewood = firewood;
        this.food = food;


    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }


    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }
}

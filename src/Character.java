public abstract class Character implements Fighter {

    private String name;
    private int HP;
    private int strength;
    private int dexterity;
    private int xp;
    private int gold;

    public Character(String name, int HP, int strength, int dexterity, int xp, int gold) {
        this.name = name;
        this.HP = HP;
        this.strength = strength;
        this.dexterity = dexterity;
        this.xp = xp;
        this.gold = gold;
    }

    @Override
    public int attack() {
        if (dexterity * 4 > getRandomValue()) return strength;
        else if (dexterity/2 >= getRandomValue()) return strength * 2;
        else return 0;
    }

    //    public int crit() {
//        if (dexterity >= getRandomValue()) return strength*2;
//        else return 0;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, HP);
    }
}
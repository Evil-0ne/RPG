public class Battle {
    public void fight(Character hero, Character monster, Main.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                } else {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(Character defender, Character attacker, Main.FightCallback fightCallback) {
        int hit = attacker.attack();
//        int crit = attacker.crit();
        int defenderHealth = defender.getHP() - hit;
        if (hit != 0) {
            System.out.printf("%s Нанес удар в %d единиц!%n", attacker.getName(), hit);
            System.out.printf("У %s осталось %d единиц здоровья...%n", defender.getName(), defenderHealth);
        } else {
            System.out.printf("%s промахнулся!%n", attacker.getName());
        }
        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("Извините, вы пали в бою...");
            fightCallback.fightLost();
            System.exit(0);
            return true;

        } else if (defenderHealth <= 0) {
            System.out.printf("Враг повержен! Вы получаете %d опыт и %d золота%n", defender.getXp(), defender.getGold());
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHP(defenderHealth);
            return false;
        }
    }
}
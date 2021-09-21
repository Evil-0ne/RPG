import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br;
    private static Character player = null;
    private static Battle battle = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.printf("Спасти наш мир от драконов вызвался %s! Да будет его броня крепка и бицепс кругл!%n", player.getName());

            printNavigation();
        }

        switch (string) {
            case "1" -> {
                System.out.println("Торговец еще не приехал");
                command(br.readLine());
            }
            case "2" -> {
                commitFight();
            }
            case "3" -> System.exit(1);
            case "да" -> command("2");
            case "нет" -> {
                printNavigation();
                command(br.readLine());
            }
            default -> System.out.println("Введите одну из доступных комманд.");
        }
        command(br.readLine());
    }

    //    private static void heal() {
//
//    }
    private static void commitFight() {
        battle.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.printf("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d единиц здоровья.%n", player.getName(), player.getXp(), player.getGold(), player.getHP());
                System.out.println("Желаете продолжить поход? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
//        System.out.println("");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }

    private static Character createMonster() {
        int a = 0;
        int b = 10;
        int random = a + (int) (Math.random() * b);
        if (random > 5) return new Goblin(
                "Гоблин",
                50,
                10,
                10,
                100,
                20
        );
        else if (random < 5) return new Skeleton(
                "Скелет",
                25,
                20,
                20,
                100,
                10
        );
        else return new Dragon(
                    "Дракон",
                    100,
                    30,
                    10,
                    500,
                    50
            );
    }

//    private static Character createBoss() {
//
//    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }
}
import java.util.Scanner;

public class DungeonAdventure {

    public static void main(String[] args) {

        String playGame = "y";
        do {
            playGame = init(playGame);
        } while (playGame.equals("y"));

        //kb.close();


    }

    public static String init(String playGame) {
        Scanner kb = new Scanner(System.in);
        //Provides an introduction to the game describing what the game is about and how to play
        System.out.println("Welcome brave player, to Dungeon Adventure, in this game you will have to \n" +
                "descend into a dungeon in search of the mighty \"Pillars of OO\". In your quest you will have to explore \n" +
                "the dungeon room by room, but beware there are rumors of monsters and traps. Use your potions with\n" +
                " care you never know when you will find another...\n");
        //Creates a Dungeon Object and a Hero Object (based on user choice)
        int dungeon_size = 5;
        DungeonCharacterFactory factory = new DungeonCharacterFactory();
        Dungeon map = new Dungeon(dungeon_size);
        Hero player;

        try {
            player = factory.createHero(characterSelect(kb), characterName(kb));

        } catch (Exception e) {
            System.out.println("failed to create player... generic player selected:\nname: Aragorn class: warrior");
            player = factory.createHero(1, "Aragorn");
        }
        gameMenu(kb, player, map, factory);

        System.out.println("Play again (y/n)?");
        playGame = kb.nextLine();


        return playGame;
    }

    private static void gameMenu(Scanner kb, Hero player, Dungeon map, DungeonCharacterFactory factory) {
        if (kb == null || player == null || map == null || factory == null)
            throw new IllegalArgumentException("Parmesan passed as null.");
        do {
            int choice = 0;
            System.out.println(map.toString());
            System.out.println("______________________");
            System.out.println(player.toString());

            System.out.println("______________________");


            System.out.println("1. Move Up");
            System.out.println("2. Move Down");
            System.out.println("3. Move Right");
            System.out.println("4. Move Left");


            System.out.println("5. Use Health Potion");
            System.out.println("6. Use Vision Potion");
            try {
                choice = kb.nextInt();
                kb.nextLine();
            } catch (Exception e) {
                System.out.println("type mismatch");
                System.out.println("choice:" + choice);
            }
            if (choice == 1) {
                map.movePlayer(1, player, factory);
            } else if (choice == 2) {
                map.movePlayer(2, player, factory);
            } else if (choice == 3) {
                map.movePlayer(3, player, factory);
            } else if (choice == 4) {
                map.movePlayer(4, player, factory);
            } else if (choice == 5) {
                player.useHealthPotion();
            } else if (choice == 6) {
                player.useVisionPotion();
                map.visionUsed(map.getPos());
            }else if(choice == 42){                    // diagnostic tool as per spec
                map.allVisible();
            }else
                System.out.println("invalid option please try again.");

        } while (player.getFoundPillars() != 4 && player.getCurrentHP() > 0 && map.getPos() != map.getExitPos());
        map.allVisible();
    }

    private static String characterName(Scanner kb) {
        System.out.println("What is your name brave adventurer?\n");

        return kb.nextLine();

    }

    private static int characterSelect(Scanner kb) {
        int choice = 0;
        System.out.println("What class do you want to play as?\n");
        while (choice == 0) {
            System.out.println("1.Warrior\n" +
                    "2.Sorceress\n" +
                    "3.Thief\n" +
                    "4.Cleric\n" +
                    "5.Alchemist\n->");
            try {
                choice = kb.nextInt();
                kb.nextLine();

            } catch (Exception e) {
                System.out.println("type mismatch");
                System.out.println("Fail-back selected: you are a warrior (maybe next time select a valid choice...just saying).");
                choice = 1;
            }

        }
        return choice;
    }

}

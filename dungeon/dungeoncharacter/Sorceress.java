package dungeoncharacter;
import java.util.*;
public class Sorceress extends Hero {

   public Sorceress(final String name) {
      super(name, Stats.getSorceressStats());
   }
   
   public void iceBlast(DungeonCharacter opponent) {
        
        System.out.println(super.getName() + " casts Ice Blast at " + opponent.getName() + ":");
        super.attack(opponent);
        System.out.println("The enemy is slowed down and an extra turn is added.");
        super.setNumTurns(getNumTurns() + 1);
        System.out.println();
    }
   
   public void attack(DungeonCharacter opponent) {
        System.out.println(super.getName() + " casts a spell of fireball at " + opponent.getName() + ":");
        super.attack(opponent);
    }

   public void battleChoices(DungeonCharacter opponent) {
        Scanner kb = new Scanner(System.in);
        int choice;

        super.battleChoices(opponent);

        do {
            System.out.println("1. Attack Opponent");
            System.out.println("2. Cast Ice Blast");
            System.out.print("Choose an option: ");
            choice = kb.nextInt();

            if(choice == 1) {
               this.attack(opponent);
            }
            else if (choice == 2) {
               this.iceBlast(opponent);
            }
            else {
               System.out.println("invalid choice!");
            }
            
            super.setNumTurns(super.getNumTurns() - 1);
            if (super.getNumTurns() > 0)
                System.out.println("Number of turns remaining is: " + super.getNumTurns());
        } while (super.getNumTurns() > 0);
    }
 }
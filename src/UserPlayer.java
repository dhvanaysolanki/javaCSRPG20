import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class UserPlayer extends CharPlayer {

    // class attributes

    private HashMap<String, int[]> playerInventory;
    private int playerExp;

    // Constructor of class

    public UserPlayer(String playerName, String playerSpecial) {
        super(playerName, new int[]{150, 500, 100, 300, 1, 20});

        this.playerInventory = new HashMap<>();

        if (Objects.equals(playerSpecial, "attack")) {
            this.characterAttack += 150;
        } else if (Objects.equals(playerName, "health")) {
            this.characterHealth += 150;
        } else if (Objects.equals(playerName, "defense")) {
            this.characterDefense += 10;
        }

        this.playerExp = 0;
    }

    // Printing out player inventory and stats
    void playerInventoryStats() {
        System.out.println("\nYou have the following elements in your inventory:");

        for (Map.Entry<String, int[]> entry : this.playerInventory.entrySet()) {
            System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
        }


        System.out.println("Your current stats are: " +
                this.characterHealth + " health, "
                + this.characterAttack + " attack, "
                + this.characterDefense + " defense.");
    }


    // Getters and setters for changing/viewing playerExp (Done for an example of encapsulation)
    void setPlayerExp() {
        this.playerExp += 1;
    }

    int getPlayerExp() {
        return this.playerExp;
    }

    // Getters and Setters for the playerInventory
    void playerInventoryAdd(String item, int[] value) {
        this.playerInventory.put(item.toLowerCase(), value);
        this.recalculateDefense();
    }

    HashMap<String, int[]> viewPlayerInventory() {
        return this.playerInventory;
    }

    /*Dilshaan
       This method is needed to recalculate our defense points every time our player finds or puts on armour/swords, which may
       happen multiple times in our code, hence the reason we made it a method. It actually gets called by the playerInventoryAdd
       method. what it does, is that it goes thourgh all the values in the hashmap, and takes the second pairing, and then adds them
       up.
     */

    //
    private void recalculateDefense() {
        for (Map.Entry<String, int[]> entry : this.playerInventory.entrySet()) {
            this.characterDefense += entry.getValue()[1];
        }
    }



    // Checks if player is living, otherwise sends them to the start via recursion
    void checkLiving() {
        if (this.characterHealth <= 0) {
            System.out.println("💀 Death has landed upon you. We are sending you back -- make wiser choices!\n");
            Main.main(null);
        }
    }


}




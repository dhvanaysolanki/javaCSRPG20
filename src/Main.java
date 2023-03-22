class Main {

    // player as a public variable

    private static UserPlayer uPlayer;

    public static void main(String[] args) {
        uPlayer = userIntro();
        storyIntro();

        label:
        while (true) {

            String directionChosen = directionChoose();

            switch (directionChosen) {
                case "N" -> {
                    if (northStory()) {
                        break label;
                    }
                }
                case "E" -> eastStory();
                case "S" -> southStory();
                case "W" -> westStory();
            }

        }


    }


    // User Intro

    public static UserPlayer userIntro() {

        String userName = GameControl.capitalCase(GameControl.strInputValidation(new String[]{""}, "What is your name: "));

        String userSpecial = GameControl.strInputValidation(new String[]{"Attack", "Health", "Defense"}, "Player what is your special? (attack, health, defense): ");

        System.out.println("Hello " + userName + ", your special is: " + userSpecial + "!");
        return new UserPlayer(userName, userSpecial);
    }


    // Story Intro

    public static void storyIntro() {
        uPlayer.playerInventoryStats();
        System.out.println("\n");
        System.out.println("You wake up un a dark forest, a glimpse of sunlight shines through the draping canopies displaying a wooden stick clearly within reach. \n"
                + "You leap backwards as a bright beam of light dashes into your eye." + " In the corner of a near tree, you notice a silver sword glistening in the sunlight. \n" + "Now it's your turn:" +
                "CHOOSE YOUR DESTINY! \n" + "The ancient wooden stick? OR The Newly-Polished Silver Sword: ");

        String stickOrSword = GameControl.strInputValidation(new String[]{"Stick", "Sword"}, "Player " + uPlayer.characterName
                + ", what is your ultimate choice? (Stick, OR Sword): ");

        if (stickOrSword.equals("stick")) {
            System.out.println("\nYou feel the power of the ancient wooden stick, feeling its power surge through your hands!");
            uPlayer.playerInventoryAdd("Wood Stick", new int[]{50, 10});
            System.out.println("\nBecoming one with this strength, you decide to continue searching for more.");
        } else {

            System.out.println("You gain 200 attack ... But you accidentally cut yourself -- losing 150 health!");

            uPlayer.playerInventoryAdd("Silver Sword", new int[]{200, 10});
            uPlayer.damageTaken(150);
            uPlayer.checkLiving();
            uPlayer.playerInventoryStats();
        }


    }


    // North story

    public static boolean northStory() {
        if (uPlayer.getPlayerExp() < 3) {
            System.out.println(":) you fall of the edge of a giant cliff, get better luck!" +
                    "\n Gain 3XP and COME BACK!");
            uPlayer.damageTaken(1000);
            uPlayer.checkLiving();
            uPlayer.playerInventoryStats();

        } else {
            bossBattle();
            System.out.println("\nHonestly, we didn't think you could do this, but congrats -- we will have to better train Charizard after that.\n" +
                    "Hope you enjoyed your time in our FantasyWorld, and here is our final gift to you!\n");
            System.out.println("         />_________________________________");
            System.out.println("[########[]_________________________________>");
            System.out.println("         />");
            System.out.println("Bestowed upon by the ancient lords, we give you this souvenir to commemorate your success!");
            return true;
        }
        return false;
    }


    // East story

    public static void eastStory() {
        uPlayer.setPlayerExp();
        System.out.println("Welcome to the Aqua Realm. Gazing around you notice a school of mermaids, leaving a yellowish "
                + "trail. Leading your hand into the yellowish stream, \n" + "the water surges into your fingertips -- offering you 1XP! \n" +
                "Realizing you don't know how to swim, you flail your arms in the empty space." + "\nLosing 15 health gradually, " +
                "a seashell swiftly lays your body on it's surface, and glides you to safety." + "\nGrabbing this shell as a shield -- You gain 2% more Defense!");
        uPlayer.damageTaken(15);
        uPlayer.checkLiving();

        uPlayer.playerInventoryAdd("Rigid Seashell Shield", new int[]{1, 2});
        uPlayer.playerInventoryStats();
        System.out.println("You place the chestplate on your body, feeling strong as ever.\n");

    }

    // West story

    public static void westStory() {
        uPlayer.setPlayerExp();
        uPlayer.setPlayerExp();
        System.out.println("As you head West -- the terrain flattens and your shoes dig into the mounds of sand.\n" + "Whilst the smothering hot sun was slowly drying your skin out, your sweat and panting attracted some movement, a swarm of snakes rise out of the sand.");


        UserEnemy[] snakeList = new UserEnemy[]{
                new UserEnemy("Dhvanay The Snake", new int[]{0, 100, 0, 45, 0, 29}),
                new UserEnemy("Damien The Snake", new int[]{0, 100, 0, 45, 0, 29}),
                new UserEnemy("Dilshaan The Snake", new int[]{0, 100, 0, 45, 0, 29}),
        };


        for (UserEnemy enemy : snakeList) {
            String weaponOfChoice = GameControl.strInputValidation(uPlayer.viewPlayerInventory().keySet().toArray(new String[0]),
                    uPlayer.characterName + " which weapon would you like to choose to attack the snake: ");

            System.out.println(weaponOfChoice);

            while (enemy.enemyCheckLiving()) {

                System.out.println(enemy.characterName + ", lunges towards you!\n");
                enemy.attackChar(uPlayer, 0);
                uPlayer.checkLiving();

                System.out.println(weaponOfChoice);

                uPlayer.attackChar(enemy, uPlayer.viewPlayerInventory().get(weaponOfChoice)[0]);
                System.out.println("Swinging back, you use " + weaponOfChoice + ".");
            }


            System.out.println(uPlayer.characterName + " kills " + enemy.characterName + " with " + weaponOfChoice);


        }

        System.out.println("Power surges through your body, and your confidence grows, examining the dead snakes. \n");
        System.out.println("Upon them you see a golden snake eye! Grasping this squishy material, the slime surrounding your eye surges into your skin -- turning your veins to gold! \n" +
                " You gain 500 health!");
        uPlayer.damageTaken(-500);
        uPlayer.playerInventoryStats();
    }

    public static void southStory() {
        uPlayer.setPlayerExp();
        if (uPlayer.getPlayerExp() >= 2) {
            System.out.println("You've gained 1 XP! Welcome to the biome of the rocky giants!" +
                    "Here you'll meet the beautiful ranges of the Mount Alps, and all the mythical creatures they enclose. \n" + "AVALANCHE! RUN,RUN,RUN! You sprint away from the mountain, dodging around the trees swiftly, adrenaline rushing \n" +
                    "With your life on the line, you run into a deep dark cave, nearly getting swept under the snow of the" +
                    "avalanche. \n You step forward, to see a giant sleeping bear right in front of you.");

            UserEnemy buffBear = new UserEnemy("Buff Bear", new int[]{0, 420, 0, 125, 0, 21});


            String bearScene = GameControl.strInputValidation(new String[]{"attack", "run", "feed"}, "Player " + uPlayer.characterName + " what would you like to do? (attack, run or feed)");
            System.out.println(bearScene);


            switch (bearScene) {
                case "attack" -> {
                    while (buffBear.enemyCheckLiving()) {

                        String weaponOfChoice = GameControl.strInputValidation(uPlayer.viewPlayerInventory().keySet().toArray(new String[0]), uPlayer.characterName + " Which weapon would you like to choose. ");
                        System.out.println(weaponOfChoice);

                        uPlayer.attackChar(buffBear, uPlayer.viewPlayerInventory().get(weaponOfChoice)[0]);
                        uPlayer.playerInventoryStats();


                        buffBear.attackChar(uPlayer, 0);
                        System.out.println("The bear towering over you, and slashes its giant claw, dealing immense damage");
                        uPlayer.checkLiving();

                    }
                }
                case "run" -> {
                    System.out.println("You try running away, planning to dig out the tunnel as fast as possible," +
                            "but the bear notices and hits you in the back of the head with its claw. You fall to your knees" +
                            "and watch in horror as the bear starts ripping your limbs off of your body with its teeth");
                    uPlayer.damageTaken(1000);
                    uPlayer.checkLiving();
                    uPlayer.playerInventoryStats();
                }
                case "feed" -> {
                    System.out.println("You frantically take out a small piece of leftover candy in your pocket, sliding" +
                            "the food over to the animal. Good decision of waking it up and" +
                            "feeding it, its currently in the process of pulling your leg of and eating it");
                    uPlayer.damageTaken(1000);
                    uPlayer.checkLiving();
                    uPlayer.playerInventoryStats();
                }
            }

            System.out.println("You defeat the bear, and start digging your way out of the cave, glancing back occasionally " +
                    "for any challengers");

        } else {
            System.out.println("You head a loud growling sound, frightened, you stumble back an instinctively run -- taking 1 XP with you.");
            uPlayer.setPlayerExp();
        }


    }

    /* Dhvanay Solanki:
    1. What does the method do/function: This method's function is to stimulate a FINAL BOSS BATTLE, from the components of an announcer calling  upon the
    "challenge" and the challenger to laying out the actions the player can take (attack, dodge or retreat). This public static void (doesn't return any values)
     initializes the UserEnemy(Charizard); and uses I/P/O through taking in a string, matching that string to various cases, and for instance, outputting the
     various levels of combat.
    2.Why is it needed? This method is essential as in one variable the code is able to conceptualize all of the finalBossBattle, allowing it to be quickly placed
    into other methods such as our direction-based stories. Furthermore, this would allow user to take different routes but still end up
    at finalBossBattle through one line of code.
    3. Parameters? There aren't any parameters, the method mainly utilizes I/P/O, and ensure that the process works in all scenarios.
    A small parameter present would be input Validation, where all invalid ints, random strings and a combination of the two are immediately removed, and the prompt
    is restated.
    4. Precondition: The input isn't blank and must have the right elements. The string must attach to the cases we have presented, and carry out the method coded.
    5. Postconditions: Intended result coming out at the end of each case: "Attack" -- User either successfully defeats boss
    and wins boss battle, or must restart the game after their health reaches 0. "Dodge" -- Charizard aims to attack,
     and 60% of the time will miss. User should be given case options again. "Retreat" -- User takes damage for attempting to run,
      but is presented with cases again to choose more wisely.
   6. In order to debug the method, there was a creation of boolean true loop, where this switch-case method will
   continue running after the user completes the code in a case. This included loop is also given to break,
   to indicate the user has won, and the cases don't need to be brought up again.
     */
    public static void bossBattle() {
        System.out.println("Dun! Dun! Dun! Welcome to the most thrill you have ever experienced in your life."
                + " Now that you've received various attack, health and armor upgrades \n-- let's see if you can make it out of this RPG!");
        System.out.println("Your final challenge is: Charizard from the Pokemon of Oz.");
        UserEnemy charizardEnemy = new UserEnemy("Charizard", new int[]{0, 820, 0, 200, 0, 16});

        loop: while (true) {
            String bossScene = GameControl.strInputValidation(new String[]{"attack", "dodge", "retreat"}, "Player " + uPlayer.characterName + " be careful with your choices, Charizard will seriously burn you. (attack, dodge or retreat)");
            switch (bossScene) {
                case "attack" -> {
                    while (charizardEnemy.enemyCheckLiving()) {
                        String weaponOfChoice = GameControl.strInputValidation(uPlayer.viewPlayerInventory().keySet().toArray(new String[0]), uPlayer.characterName + " Which weapon would you like to choose. ");
                        System.out.println(weaponOfChoice);

                        uPlayer.attackChar(charizardEnemy, uPlayer.viewPlayerInventory().get(weaponOfChoice)[0]);
                        uPlayer.playerInventoryStats();


                        charizardEnemy.attackChar(uPlayer, 0);
                        System.out.println("Charizard, with a small smirk -- opens his jaw and launches a raging fireball.");
                        uPlayer.checkLiving();
                        System.out.println("You only have " + uPlayer.characterHealth + " health remaining!");

                    }
                    break loop;
                }
                case "dodge" -> {
                    System.out.println("Aiming to run from incoming shots, you dive to your left! " +
                            "Will Charizard be able to adapt?");
                    charizardEnemy.attackCharDodge(uPlayer, 0);
                    uPlayer.checkLiving();
                    uPlayer.playerInventoryStats();
                }
                case "retreat" -> {
                    System.out.println("HAHAHA! You thought you could just leave? This is where destinies are made, and retreating will never be an option!\n" +
                            "Take your punishment, and be glad I've given you another chance!");
                    uPlayer.damageTaken(100);
                    uPlayer.checkLiving();
                    uPlayer.playerInventoryStats();

                }
            }

        }

    }




    private static String directionChoose() {
        String userCDirection = GameControl.strInputValidation(new String[]{"N", "E", "S", "W"}, uPlayer.characterName +
                ", Which direction would you like to go? (N,E,S,W): ");
        return userCDirection.toUpperCase();
    }

}

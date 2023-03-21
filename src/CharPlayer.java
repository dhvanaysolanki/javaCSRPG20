class CharPlayer extends GameControl {

    String characterName;
    int characterHealth;
    int characterAttack;
    int characterDefense;

    public CharPlayer(String CharacterName, int[] statRanges) {

        this.characterName = CharacterName;
        this.characterHealth = randomNum(statRanges[0], statRanges[1]);
        this.characterAttack = randomNum(statRanges[2], statRanges[3]);
        this.characterDefense = randomNum(statRanges[4], statRanges[5]);

    }


    /* Damien
    This method is used for taking in the damage player recieves and applying that to the characters health while also taking into account the character's defense.
    We methodized this because it is used often in any event the player recieves damage.
    Parameters for this method would be that the damage input has to be an int
    */

    public void damageTaken(int damage) {
        System.out.println(this.characterHealth); // Test Case
        this.characterHealth -= damage * (1 - (this.characterDefense / 100));
        System.out.println(this.characterHealth);

    }


    /*
    This method determines weather the attack done by a character hits the target or not, by using the random number method and seeing if the random number falls in the range of an attack hitting.
    This is methoidzed as it is used in any combat situations which happens often in the game.
    Parameters would be the target object and the int weapon Boost
     */
    void attackChar(CharPlayer dTarget, int weaponBoost) {

        if (randomNum(0, 5) < 4) {
            dTarget.damageTaken(this.characterAttack + weaponBoost);
            System.out.println("Critical Hit!");
        } else {
            System.out.println("\n" + this.characterName + " missed their attack! on " + dTarget.characterName);
        }

    }


}

























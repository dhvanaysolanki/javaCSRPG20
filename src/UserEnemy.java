class UserEnemy extends CharPlayer {

    // Inheritance from CharPlayer class
    UserEnemy(String enemyName, int[] statRanges) {
        super(enemyName, statRanges);
    }

    // Checks if enemy is living or not
    public boolean enemyCheckLiving() {
        return (this.characterHealth > 0);
    }
}

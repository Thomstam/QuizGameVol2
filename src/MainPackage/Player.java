package MainPackage;



/**
 * This class represents a player of the game with his/her attributes(the name, the score that he/she has, the bet that
 * he/she makes for each question in the Betting game mode, and the answer that he/she picks int every question).
 */
public class Player {

    private String username;
    private int score;
    private int bet;
    private String answer;

    /**
     * Constructor
     * When a new player is created his score gets initialized with 0.
     * @param username The username of the player
     */
    public Player(String username){

        this.username=username;
        this.score=0;

    }

    /**
     * @return the username of the player
     */
    public String getUsername(){ return this.username; }

    /**
     * Sets the name of the player
     * @param username The username of the player
     */
    public void setUsername(String username){this.username=username;}

    /**
     * @return the current score of the player
     */
    public int getScore(){return this.score;}

    /**
     * Sets the score of the player
     * @param score The points of the player
     */
    public void setScore(int score){this.score=score;}

    /**
     * @return the number of points that the player bets during the Betting gamemode
     */
    public int getBet() { return this.bet; }

    /**
     * Sets the bet of a player for the Betting gamemode
     * @param bet The number of points that the player bets
     */
    public void setBet(int bet) { this.bet = bet; }

    /**
     * @return the answer that the player chose in a question
     */
    public String getAnswer() { return this.answer; }

    /**
     * Sets the answer that the player picked
     * @param answer the answer that the player chose in a question
     */
    public void setAnswer(String answer) { this.answer = answer; }
}

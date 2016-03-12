import java.util.Random;

/**
 * Created by admin on 2016-03-10.
 */
public class Game {

    //Fields
    private Board board;
    private Player player1;
    private Player player2;

    public Game(boolean AI) {

        board = new Board();

        if(!AI) {
            //Human Players
            player1 = new Player(board, true, false);
            player2 = new Player(board, false, false);
            play();
        } else {
            //AI Players
            player1 = new Player(board, true, true);
            player2 = new Player(board, false, true);
            playAI();
        }
    }

    //Game will be played in a CCW manner
    public void play() {

        //Randomly determine who plays first
        Random r = new Random();
        int startingPlayer = r.nextInt(2);

        boolean player1TurnNow = false;

        //First move
        if(startingPlayer == 0) {
            System.out.println("Player 1 Starts");
            player1.move();
        } else {
            System.out.println("Player 2 Starts");
            player2.move();
            player1TurnNow = true;
        }
        board.draw();
        //While the game isn't done, keep playing
        while(!board.isGameFinished()) {
            if(player1TurnNow) {
                System.out.println("Player 1 Turn");
                player1.move();
                player1TurnNow = false;
                board.draw();
            } else {
                System.out.println("Player 2 Turn");
                player2.move();
                player1TurnNow = true;
                board.draw();
            }
        }

        //Determine winner
        boolean winner = board.whoWon();
        if(winner) {
            System.out.println("Player 1 is the winner, Total stones is: " + board.countMancala()[0]);
        } else {
            System.out.println("Player 2 is the winner, Total stones is: " + board.countMancala()[1]);
        }
    }

    public void playAI() {

    }

    public static void main(String[] args) {
        Game game = new Game(false);
    }

}

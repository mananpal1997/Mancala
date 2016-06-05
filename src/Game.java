package game;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 */
public class Game {

    private Board board;
    private Player player1;
    private Player player2;

    public Game(int option) {

        board = new Board();
        
        if(option == 1) {
            player1 = new Player(board, true, false);
            player2 = new Player(board, false, false);
            play();
        }
        else if(option == 2) {
            player1 = new Player(board, true, false);
            player2 = new Player(board, false, true);
            playHumanVsAI();
        }
        else {
        	player1 = new Player(board, true, true);
            player2 = new Player(board, false, true);
            playAIVsAI();
        }
        
    }

    public void play() {

        Random r = new Random();
        int startingPlayer = r.nextInt(2);

        boolean player1TurnNow = false;

        if(startingPlayer == 0) {
            System.out.println("Player 1 Starts");
            player1.move();
        } else {
            System.out.println("Player 2 Starts");
            player2.move();
            player1TurnNow = true;
        }
        board.draw();
        
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

        boolean winner = board.whoWon();
        if(winner) {
            System.out.println("Player 1 is the winner, Total stones is: " + board.countMancala()[0]);
        } else {
            System.out.println("Player 2 is the winner, Total stones is: " + board.countMancala()[1]);
        }
    }

    public void playHumanVsAI() {

        Random r = new Random();
        int startingPlayer = r.nextInt(2);

        boolean player1TurnNow = false;
        board.draw();

        if(startingPlayer == 0) {
            System.out.println("Player 1 Starts");
            player1.move();
        } else {
            System.out.println("Player 2 Starts");
            player2.moveAI(player2);
            player1TurnNow = true;
        }
        board.draw();

        while(!board.isGameFinished()) {
            if(player1TurnNow) {
                System.out.println("Before Player 1 goes:");
                board.draw();
                System.out.println("Player 1 Turn");
                player1.move();
                player1TurnNow = false;
                System.out.println("After Player 1 goes:");
                board.draw();
            } else {
                System.out.println("Before Player 2 goes:");
                board.draw();
                System.out.println("Player 2 Turn");
                player2.moveAI(player2);
                player1TurnNow = true;
                System.out.println("After Player 2 goes:");
                board.draw();
            }
        }        

        boolean winner = board.whoWon();
        if(winner) {
            System.out.println("Player 1 is the winner, Total stones is: " + board.countMancala()[0]);
        } else {
            System.out.println("Player 2 is the winner, Total stones is: " + board.countMancala()[1]);
        }
    }
    
    public void playAIVsAI() {

        Random r = new Random();
        int startingPlayer = r.nextInt(2);

        boolean player1TurnNow = false;
        board.draw();

        if(startingPlayer == 0) {
            System.out.println("Player 1 Starts");
            player1.moveAI(player1);
        } else {
            System.out.println("Player 2 Starts");
            player2.moveAI(player2);
            player1TurnNow = true;
        }
        board.draw();

        while(!board.isGameFinished()) {
            if(player1TurnNow) {
                System.out.println("Before Player 1 goes:");
                board.draw();
                System.out.println("Player 1 Turn");
                player1.move();
                player1TurnNow = false;
                System.out.println("After Player 1 goes:");
                board.draw();
            } else {
                System.out.println("Before Player 2 goes:");
                board.draw();
                System.out.println("Player 2 Turn");
                player2.moveAI(player2);
                player1TurnNow = true;
                System.out.println("After Player 2 goes:");
                board.draw();
            }
        }
        
        boolean winner = board.whoWon();
        if(winner) {
            System.out.println("Player 1 is the winner, Total stones is: " + board.countMancala()[0]);
        } else {
            System.out.println("Player 2 is the winner, Total stones is: " + board.countMancala()[1]);
        }
    }

	public static void main(String[] args) {
    	System.out.print("1 for Player Vs Player \t 2 for Player Vs Computer \t 3 for Computer Vs Computer : ");
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	int np = sc.nextInt();
		Game game;
    	if(np == 1)
    		game = new Game(1);
    	else if(np == 2)
    		game = new Game(2);
    	else
    		game = new Game(3);
    }
}
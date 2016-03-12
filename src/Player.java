/**
 * Created by admin on 2016-03-10.
 */

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Player {

    private boolean isAI;
    private Board board;
    private Scanner s;
    private boolean isPlayer1;
    private int sideOfBoard;
    private ArrayList<Hole> playerBoardView;
    private HashMap<Integer, Hole> indexHoleMap;


    public Player(Board board, boolean isPlayer1, boolean isAI) {
        this.board = board;
        this.isAI = isAI;
        this.isPlayer1 = isPlayer1;
        if(isPlayer1) {
            sideOfBoard = 0;
        } else {
            sideOfBoard = 1;
        }
        s = new Scanner(System.in);
        playerBoardView = new ArrayList<>();
        indexHoleMap = new HashMap<>();
        initializePlayerBoardView();
    }

    //Convert 2D array to Single Array for easy bi-directional navigation
    public void initializePlayerBoardView() {
        int counter = 0;
        if(isPlayer1) {

            for(int i = board.getWidth() - 1 ; i >= 0 ; i--) {
                Hole holeToAdd = board.getHole(0,i);
                playerBoardView.add(holeToAdd);
                indexHoleMap.put(counter, holeToAdd);
                counter++;
            }

            for(int i = 0; i < board.getWidth(); i++) {
                Hole holeToAdd = board.getHole(1,i);
                playerBoardView.add(holeToAdd);
                indexHoleMap.put(counter, holeToAdd);
                counter++;
            }
        } else {
            for(int i = 0; i < board.getWidth(); i++) {
                Hole holeToAdd = board.getHole(1,i);
                playerBoardView.add(holeToAdd);
                indexHoleMap.put(counter, holeToAdd);
                counter++;
            }

            for(int i = board.getWidth() - 1 ; i >= 0 ; i--) {
                Hole holeToAdd = board.getHole(0,i);
                playerBoardView.add(holeToAdd);
                indexHoleMap.put(counter, holeToAdd);
                counter++;
            }
        }
    }

    public boolean isAI() {
        return isAI;
    }

    public void move() {
        System.out.println("Choose the circle to take stones from:");
        System.out.println("Options are: ");
        ArrayList<Integer> choicesMovement = new ArrayList<>();
        for(int i = 1; i < board.getWidth() - 1 ; i++) {
            if(!board.getHole(sideOfBoard, i).isEmpty()) {
                choicesMovement.add(i);
                System.out.print(i + " ");
            }
        }
        int choice = s.nextInt();
        while(!choicesMovement.contains(choice)) {
            System.out.println("Invalid choice: " + choice);
            choice = s.nextInt();
        }
        Hole chosenHole = board.getHole(sideOfBoard, choice);
        int numStones = chosenHole.getStones();
        chosenHole.removeStones();
        if(isPlayer1) {
            choice = board.getWidth() - 1 - choice;
        }
        int counter = 1;
        int stoneIncrementer = 1;
        while(stoneIncrementer <= numStones) {
            if(choice + counter > board.getWidth()*2 - 1) {
                counter = 0;
                choice = 0;
            }
            Hole currentHole = indexHoleMap.get(choice + counter);
            if(isPlayer1) {
               if(currentHole.isMancala() && !currentHole.isBlue()) {
                   counter++;
               } else {
                   indexHoleMap.get(choice + counter).addStone();
                   counter++;
                   stoneIncrementer++;
               }
            } else {
                if(currentHole.isMancala() && currentHole.isBlue()) {
                    //System.out.println("Encountered a mancala that p2 can't go on at : " + indexHoleMap.get(choice + counter).getKey());
                    counter++;
                } else {
                    //System.out.println("Key of the hole is: " + indexHoleMap.get(choice + counter).getKey());
                    indexHoleMap.get(choice + counter).addStone();
                    counter++;
                    stoneIncrementer++;
                }
            }
        }
    }

    public void moveAI(){

    }
}

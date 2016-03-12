import java.util.HashMap;
import java.util.Arrays;

/**
 * Created by admin on 2016-03-09.
 */
public class Board {

    //Globals
    public static int DEFAULT_FILLED_CIRCLES = 6;
    public static int DEFAULT_EMPTY_CIRCLES = 2;
    public static int HEIGHT = 2;
    public static int DEFAULT_STONES = 1;
    public static int EMPTY = 0;

    //Fields
    private String[][] board;
    private HashMap<String, Hole> map;
    private int width;
    private int starting_stone_count;

    //Constructors
    public Board() {
        this(DEFAULT_FILLED_CIRCLES, DEFAULT_STONES);
    }

    public Board(int numCircles, int stones) {

        width = numCircles + DEFAULT_EMPTY_CIRCLES;
        board = new String[HEIGHT][width];
        map = new HashMap<>();
        starting_stone_count = stones;

        //Fill representation & Initialize Holes
        initBoard();
    }

    //Initialize representation of board
    private void initBoard() {
        initHashMap();
    }

    public int getWidth() {
        return width;
    }

    //Set Mancala representations to 0 initially
    private void initMancalaValues() {

        //Mancalas on left side of board
        map.put(generateKey(0, 0), new Hole(EMPTY, 0, 0, true));
        map.put(generateKey(1, 0), new Hole(EMPTY, 1, 0, true, true));

        //Mancalas on right side of the board
        map.put(generateKey(0, width - 1), new Hole(EMPTY, 0, width - 1, true));
        map.put(generateKey(1, width - 1), new Hole(EMPTY, 1, width - 1, true, true));
    }

    //Create a mapping of Location in Board (2D Array) => Number of Stones
    private void initHashMap() {
        for(int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Hole temp =  new Hole(starting_stone_count, i, j, false);
                map.put(generateKey(i,j), temp);
            }
        }
        initMancalaValues();
    }

    //Helper Method: Concatenation of indicies to create a key
    public String generateKey(int row, int column) {
        return "" + row + "," + column;
    }

    //Returns a hole on the board
    public Hole getHole(int row, int column) {
        return map.get(generateKey(row, column));
    }

    //Prints out the board
    public void draw() {
        for(int i = 0; i < HEIGHT ; i++) {
            for(int j = 0; j < width ; j++) {
                if(j != width - 1) {
                    System.out.print(getHole(i, j) + "-");
                } else {
                    System.out.print(getHole(i, j));
                }

            }
            System.out.println();
        }
        System.out.println("\n");
    }

    //Returns a boolean. True => Player1 Won, False => Player2 Won
    public boolean whoWon() {
        boolean isPlayer1Winner = false;
        Integer[] scoreHolder = countMancala();
        int player1Score = scoreHolder[0];
        int player2Score = scoreHolder[1];
        if(player1Score > player2Score) {
             isPlayer1Winner = true;
        }
        return isPlayer1Winner;
    }

    //Returns an array of Integers where Index: 0 => Player1Score , 1 => Player2Score
    public Integer[] countMancala() {
        Integer[] scoreHolder = new Integer[2];
        //count mancala for player1
        scoreHolder[0] = getHole(0, 0).getStones() + getHole(0, width - 1).getStones();
        //count mancala for player2
        scoreHolder[1] = getHole(1, 0).getStones() + getHole(1, width - 1).getStones();
        return scoreHolder;
    }

    //Checks if the game is done by checking if all holes on any side excluding Mancalas are empty
    public boolean isGameFinished() {

        boolean isDone = false;

        //Check player1 holes (Blue)
        for(int i = 1; i < width - 1 ; i++) {
            if(getHole(0, i).isEmpty()) {
                isDone = true;
            } else {
                isDone = false;
                break;
            }
        }

        //If Player1 Side was Empty, Game has ended
        if(isDone) {
            return isDone;
        }

        //If not, check Player2's side
        for(int i = 1; i < width - 1 ; i++) {
            if(getHole(1, i).isEmpty()) {
                isDone = true;
            } else {
                isDone = false;
                break;
            }
        }

        //Return if player2 was empty or not
        return isDone;
    }
}



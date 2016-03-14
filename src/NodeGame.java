/**
 * Created by admin on 2016-03-13.
 */

import java.util.ArrayList;

public class NodeGame {
    NodeGame parent;
    private Board board;
    private ArrayList<NodeGame> children;
    private int decisionChosen;
    private Player player;
    private Player opponent;
    private int heuristicValue;

    public NodeGame(Board passedBoard, Player player, Player opponent) {
        this(null, passedBoard, player, opponent, 0);

    }

    public NodeGame(NodeGame parent, Board passedBoard, Player player, Player opponent, int move) {
        this.parent = parent;
        board = new Board(passedBoard);
        children = new ArrayList<>();
        this.player = player.copy();
        this.opponent = opponent.copy();
        decisionChosen = move;
    }


/*    public NodeGame(NodeGame parent, Board passedBoard, Player player) {
        this.parent = parent;
        board = new Board(passedBoard);
        children = new ArrayList<>();
        this.player = player.copy();
        decisionChosen = moveChoice;
    }*/

    public int getDecisionChosen() {
        return decisionChosen;
    }

    public void setHeuristicValue(int value) {
        heuristicValue = value;
    }

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public Board getBoard() {
        return board;
    }

    public void createFringe(boolean isMaximizingPlayer) {
        ArrayList<Integer> choicesMovement = new ArrayList<>();
        if(isMaximizingPlayer) {
            for(int i = 1; i < board.getWidth() - 1 ; i++) {
                if(!board.getHole(player.getSideOfBoard(), i).isEmpty()) {
                    choicesMovement.add(i);
                }
            }
            for(int move : choicesMovement) {
                NodeGame childAdded = new NodeGame(this, board, player, opponent, move);
                children.add(childAdded);
                childAdded.player.executeMove(move);
            }
        } else {
            for(int i = 1; i < board.getWidth() - 1 ; i++) {
                if(!board.getHole(opponent.getSideOfBoard(), i).isEmpty()) {
                    choicesMovement.add(i);
                }
            }
            for(int move : choicesMovement) {
                NodeGame childAdded = new NodeGame(this, board, player, opponent, move);
                children.add(childAdded);
                childAdded.opponent.executeMove(move);
            }
        }

    }

    public int getisPlayer1Max() {
        return player.getSideOfBoard();
    }

    public int heuristicValue() {
        return board.getMancalaDifference(player.getSideOfBoard());
    }

    public ArrayList<NodeGame> getChildren() {
        return children;
    }
}

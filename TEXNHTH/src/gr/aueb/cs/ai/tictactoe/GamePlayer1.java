package gr.aueb.cs.ai.tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class GamePlayer1
{
    //Variable that holds the maximum depth the MiniMax algorithm will reach for this player
	private int maxDepth;
    //Variable that holds which letter this player controls
	private int playerColour;
	
	public GamePlayer1()
	{
		maxDepth = 2;
		playerColour = Board.WHITE;
	}
	
	public GamePlayer1(int maxDepth, int playerLetter)
	{
		this.maxDepth = maxDepth;
		this.playerColour = playerLetter;
	}

    //Initiates the MiniMax algorithm
	public Move MiniMax(Board board)
	{
            
            int alpha = Integer.MIN_VALUE;
            int beta = Integer.MAX_VALUE;
           
            //If WHITE plays then it wants to MAXimize the heuristics value
            if (playerColour == Board.WHITE)
            {
                return max(new Board(board), 0, alpha, beta);
            }
            //If the BLACK plays then it wants to MINimize the heuristics value
            else
            {
                return min(new Board(board), 0, alpha, beta);
            }
	}

    // The max and min functions are called interchangingly, one after another until a max depth is reached
	public Move max(Board board, int depth, int alpha, int beta)
	{
            

            /* If MAX is called on a state that is terminal or after a maximum depth is reached,
             * then a heuristic is calculated on the state and the move returned.
             */
            if((board.isTerminal()) || (depth == maxDepth))
            {
                    Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
                    System.out.println("terminal alpha value: "+alpha);
                    return lastMove;
                    
            }
            
            //The children-moves of the state are calculated
            ArrayList<Board> children = new ArrayList<>(board.getChildren(Board.WHITE));
            Move maxMove = new Move();
            
            for (Board child : children)
            {
                
                //prune the rest of the tree
                if(alpha >= beta)
                {
                    break;
                }
                //And for each child min is called, on a lower depth
                Move move = min(child, depth + 1, alpha, beta);
                
              
                //The child-move with the greatest value is selected and returned by max
                if(move.getValue() >= alpha)
                {
                    alpha = move.getValue();
                    
                 
                    maxMove.setRow(child.getLastMove().getRow());
                    maxMove.setCol(child.getLastMove().getCol());
                    maxMove.setValue(board.evaluate());
                 
                }
            }

            return maxMove;
	}

    //Min works similarly to max
	public Move min(Board board, int depth, int alpha, int beta)
	{
            

            if((board.isTerminal()) || (depth == maxDepth))
            {
                    Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
                    System.out.println("terminal beta value: "+beta);
                    return lastMove;
            }

            ArrayList<Board> children = new ArrayList<>(board.getChildren(Board.BLACK));
            Move minMove = new Move();
            Move move;

            for (Board child : children)
            {
                 //prune the rest of the tree
                if(alpha >= beta)
                {
                    break;
                }
                
                move = max(child, depth + 1, alpha, beta);

                if(move.getValue() <= beta)
                {
                    beta = move.getValue();

                    minMove.setRow(child.getLastMove().getRow());
                    minMove.setCol(child.getLastMove().getCol());
                    minMove.setValue(board.evaluate());
                     
                   

                }
                
            }
            return minMove;
        }
}
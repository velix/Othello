package gr.aueb.cs.ai.tictactoe;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
		int i,j;//XMoveRow, XmoveCol
		int player;
                int ai;
		int depth;
		
            //We create the players and the board
            //MaxDepth for the MiniMax algorithm is set to 2; feel free to change the values
		System.out.println("Choose depth");
		depth = sc.nextInt();
                
		GamePlayer1 WhitePlayer = new GamePlayer1(depth, Board.WHITE);
		GamePlayer1 BlackPlayer = new GamePlayer1(depth, Board.BLACK);
                
		Board board = new Board();
		board.print();
		
		System.out.println("BLACK plays first.\n Are you BLACK? [Y/N]");
		String choice = sc.next();
                
                //assign colours to player
                //i want the lastColourPlayed to be WHITE so BLACK can play first
                if(choice.equalsIgnoreCase("y"))
                {
                    player = Board.BLACK;
                    ai = Board.WHITE;
                    
                    board.setLastColourPlayed(ai);
                }
                else if(choice.equalsIgnoreCase("n"))
                {
                    player = Board.WHITE;
                    ai = Board.BLACK;
                    
                    board.setLastColourPlayed(player);
                }
                else
                {
                    System.out.println("Invalind choice. Defaults yes");
                    player = Board.BLACK;
                    ai = Board.WHITE;
                    
                    board.setLastColourPlayed(ai);
                }
		
        //While the game has not finished
		while(!board.isTerminal())
		{
                    System.out.println();
                    System.out.println("now playing: " + board.colourToPlayer());
                    //by default when a board is created the 
                    switch (board.getLastColourPlayed())
                    {
                        case Board.BLACK:
                            if(player == Board.WHITE)  
                           {
                               Move playerMove;
                                do 
                                {
                                    System.out.println("Choose line");
                                    i = sc.nextInt();

                                    System.out.println("Choose column");
                                    j = sc.nextInt();
                                    
                               }while(!board.validate(i,j));
                                
                                
                                playerMove = new Move(i,j, Board.WHITE);
                                board.makeMove(playerMove);
                                
                            }
                        
                            if(ai == Board.WHITE)
                            {
                                Move aiMove;
                                
                                do
                                {
                                    aiMove = WhitePlayer.MiniMax(board);
                                    
                                } while(!board.validate(aiMove));
                              
                                board.makeMove(aiMove);
                                System.out.println("The mighty AI played thusly: \nrow: " + aiMove.getRow() + "\ncol: " + aiMove.getCol());
                            }
                        
                            break;
                        
                        case Board.WHITE:
                            if(player == Board.BLACK)
                            {
                                Move playerMove;
                                do {
                                    System.out.println("Choose line");
                                    i = sc.nextInt();

                                    System.out.println("Choose column");
                                    j = sc.nextInt(); 
                                    
                                
                                }while(!board.validate(i,j));
                                
                                
                                playerMove = new Move(i,j, Board.BLACK);
                                board.makeMove(playerMove);
                                
                            }
                        
                            if(ai == Board.BLACK)
                            {
                                Move aiMove;
                                do
                                {
                                    
                                    aiMove = BlackPlayer.MiniMax(board);
                                    
                                    
                                }while(!board.validate(aiMove));
                                
                                board.makeMove(aiMove);
                                System.out.println("The mighty AI played thusly: \nrow: " + aiMove.getRow() + "\ncol: " + aiMove.getCol());
                            }
                        
                            break;
                            
                        default:
                            break;
                            
                        
                    }
			board.print();
			System.out.println("WHITE:" + board.counter(Board.WHITE) + "  BLACK:" + board.counter(Board.BLACK) );
		}
		if(board.counter(Board.WHITE) > board.counter(Board.BLACK)) {
			System.out.println("WHITE player wins!");
		}
		else if (board.counter(Board.WHITE) < board.counter(Board.BLACK)){
			System.out.println("BLACK player wins!");
		}
                else
                {
                        System.out.println("TIE");   
                }

	}

}
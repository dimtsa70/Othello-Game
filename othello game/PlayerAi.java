

import java.util.ArrayList;
import java.util.Arrays;

//Klash tou Ai pou periexei tis
//validMoves: methodos pou epistrefei tis diathesimes kinhseis se object (row,col) se lista
//alphabeta: o kyrios algorithmos pou apofasizei poia kinhsh tha paiksei to Ai.
public class PlayerAi {

    public static ArrayList<MovesAi> validMoves(Pawn[][] board, Pawn setColor, Pawn oppositeColor, Pawn star, Pawn dot) {
        ArrayList<MovesAi> movesList = new ArrayList<>(); //head of the list
        
        MovesAi moves;
        int i, j, row, col, countMoves = 0;
        for(i = 0; i < 8; i++) {
            for(j = 0; j < 8; j++) {
                if(board[i][j] == oppositeColor) {
                    moves = null;
                    //tsekarisma panw
                    if(i + 1 < 8 && board[i + 1][j] == setColor) {
                        for(row = i - 1; row >= 0 && board[row][j] != setColor; row--) {
                            if(board[row][j].color == '*') {
                                break;
                            }
                            else if(board[row][j].color == '.') {
                                board[row][j] = null;
                                board[row][j] = star;
                                moves = new MovesAi(row, j);
                                movesList.add(moves);
                                countMoves++;
                                break;
                            }
                        }
                    }    
                    //tsekarisma katw
                    if(i - 1 >= 0 && board[i - 1][j] == setColor) {
                        for(row = i + 1; row < 8 && board[row][j]!= setColor; row++) {
                            if(board[row][j].color == '*') {
                                break;
                            }
                            if(board[row][j].color == '.') {
                                board[row][j] = null;
                                board[row][j] = star;
                                moves = new MovesAi(row, j);
                                movesList.add(moves);
                                countMoves++;
                                break;
                            }
                        }
                    } 
                    //tsekarisma deksia
                    if( j - 1 >= 0 && board[i][j - 1] == setColor && j > 0) {
                            for(col = j + 1; col < 8 && board[i][col] != setColor; col++) {
                                if(board[i][col].color == '*') {
                                    break;
                                }
                                if(board[i][col].color == '.') {
                                    board[i][col] = null;
                                    board[i][col] = star;
                                    moves = new MovesAi(i, col);
                                    movesList.add(moves);
                                    countMoves++;
                                break;
                            }
                        }
                    }
                    //tsekarisma aristera
                    if(j + 1 < 8 && board[i][j + 1] == setColor) {
                        for(col = j - 1; col >= 0 && board[i][col] != setColor; col--) {
                            if(board[i][col].color == '*') {
                                    break;
                            }
                            if(board[i][col].color == '.') {
                                board[i][col] = null;
                                board[i][col] = star;
                                moves = new MovesAi(i, col);
                                movesList.add(moves);
                                countMoves++;
                                break;
                    
                            }
                        }
                    }
                    //tsekarisma panw-aristera
                    if(i + 1 < 8 && j + 1 < 8 && board[i + 1][j + 1] == setColor) {
                        for(row = i - 1, col = j - 1; row >= 0 && col >= 0 && board[row][col]!= setColor; row--, col--) {
                            if(board[row][col].color == '*') {
                                    break;
                            }
                            if(board[row][col].color == '.') {
                                board[row][col] = null;
                                board[row][col] = star;
                                moves = new MovesAi(row, col);
                                movesList.add(moves);
                                countMoves++;
                                break;
                            }
                    
                        }
                    }    
                    //tsekarisma panw-deksia
                    if((i + 1 < 8 && j - 1 >= 0) && board[i + 1][j - 1] == setColor) {
                        for(row = i - 1, col = j + 1; row >= 0 && col < 8 && board[row][col] != setColor; row--, col++) {
                            if(board[row][col].color == '*') {
                                    break;
                            }
                            if(board[row][col].color == '.') {
                                board[row][col] = null;
                                board[row][col] = star;
                                moves = new MovesAi(row, col);
                                movesList.add(moves);
                                countMoves++;
                                break;
                            }
                        }
                    }
                    //tsekarisma katw-aristera
                    if(i - 1 >= 0 && j + 1 < 8 && board[i - 1][j + 1] == setColor) {
                        for(row = i + 1, col = j - 1; row < 8 && col >= 0 && board[row][col] != setColor; row++, col--) {
                            if(board[row][col].color == '*') {
                                    break;
                            }
                            if(board[row][col].color == '.') {
                                board[row][col] = null;
                                board[row][col] = star;
                                moves = new MovesAi(row, col);
                                movesList.add(moves);
                                countMoves++;
                                break;
                            }
                        }
                    }
                    //tsekarisma katw-deksia
                    if(i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == setColor) {
                        for(row = i + 1, col = j + 1; row < 8 && col < 8 && board[row][col] != setColor; row++, col++) {
                            if(board[row][col].color == '*') {
                                    break;
                            }
                            if(board[row][col].color == '.') {
                                board[row][col] = null;
                                board[row][col] = star;
                                moves = new MovesAi(row, col);
                                movesList.add(moves);
                                countMoves++;
                                break;
                            }
                        }    
                    }
                } 
            }
        }
        Board.resetDot(board, dot);
     return(movesList);   
    }
    
    
    public static NextMove alphabeta(NextMove move, Pawn[][] board, Pawn setColor, Pawn oppositeColor, Pawn star, Pawn dot, int alpha, int beta, int depth, boolean maximizer) {  
        NextMove newMove;
        
        //Epitreptes diathesimes kinhseis
        ArrayList<MovesAi> futureMoves = validMoves(board, setColor, oppositeColor, star, dot);
       
        //ypologismos score analoga me to an paizei o max/min allazei kai to color
        if(depth == 0 || futureMoves.isEmpty()) {
            if(maximizer) {
                move.score = NextMove.score(board, setColor, dot);
            
            }
            else {
                move.score = NextMove.score(board, oppositeColor, dot);
                
            }    
            return move;
            
        }
        //maximizer
        if(maximizer) {
            int maxEval = Integer.MIN_VALUE;
            NextMove maxMove = null;
            
            //gia kathe diathesimi kinhsh elegxei kai enhmerwnei ena antigrafo tou pinaka
            //kai kalei thn alphabeta. epeita elegxei to score ths kinhshs pou epestrefe h klhsh ths
            //methodou kai kanei tis aparaithtes allages an autes xreiazontai
            for(MovesAi futureMove : futureMoves) {
                int row = futureMove.row;
                int col = futureMove.col;
                Pawn[][] copyBoard = Arrays.stream(board).map(Pawn[]::clone).toArray(Pawn[][]::new);
                NextMove child = new NextMove(copyBoard, setColor, oppositeColor, row, col);
                newMove = alphabeta(child, copyBoard, oppositeColor, setColor, star, dot, alpha, beta, depth - 1, false);
                if(newMove.score > maxEval) {
                    maxEval = newMove.score;
                    maxMove = child;
                    maxMove.score = newMove.score;
                }
                
                //veltistopoihsh tou algorithmou gia na trexei grhgorotera
                alpha = Math.max(alpha, maxEval);
                if(beta <= alpha) {
                    break;
                }
                        
            }
            return maxMove;
        }
        //minimizer-antistoixa ki edw
        else {
            int minEval = Integer.MAX_VALUE;
            NextMove minMove = null;
            for(MovesAi futureMove : futureMoves) {
                int row = futureMove.row;
                int col = futureMove.col;
                Pawn[][] copyBoard = Arrays.stream(board).map(Pawn[]::clone).toArray(Pawn[][]::new);  
                NextMove child = new NextMove(copyBoard, setColor, oppositeColor, row, col);
                newMove = alphabeta(child, copyBoard, oppositeColor, setColor, star, dot, alpha, beta, depth - 1, true);
                if(newMove.score < minEval) {
                   minEval = newMove.score;
                   minMove = child;
                   minMove.score = newMove.score;
                }
                
                beta = Math.min(beta, minEval);
                if(beta <= alpha) {
                    break;
                }
            }
            return minMove;
        } 
    }       
}

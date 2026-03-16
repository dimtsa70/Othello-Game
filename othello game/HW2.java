

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class HW2 {

    public static void main(String[] args) {
        String color = " ",move; 
        int forwardMoves, row = -1, col = -1, blacks = 0, whites = 0;
        Pawn[][] copyBoard;
        Pawn black = new PawnBlack('O');
        Pawn white = new PawnWhite('X');
        Pawn dot = new Dot('.');
        Pawn star = new Star('*');
        Pawn setColor, oppositeColor;
        boolean playerAi = false, movesHuman = true;
        ArrayList<MovesAi> movesAi = new ArrayList<>();
        Board game = new Board();
        NextMove nextMove = null;
        char colChar;
        ArrayList<String> moveHistory = new ArrayList<>();
        
        
        //Epilogh color
        System.out.printf("Welcome to Othello!\n\n");
        System.out.printf("Choose color (black/white/b/w/B/W/O/X/o/x): ");
        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNext()) {
            color = sc.next();
            if(color.equals("black") || color.equals("white")
                    || color.equals("b") || color.equals("w")
                    || color.equals("B") || color.equals("W")
                    || color.equals("O") || color.equals("X")
                    || color.equals("o") || color.equals("x")) {
                break;
            }
            else {
                System.out.printf("Invalid color. Try again...\n");
                System.out.printf("Choose color (black/white/b/w/B/W/O/X/o/x): ");
            }
        }
        
        //elegxos epiloghs
        if(color.equals("black") || color.equals("b") 
                    || color.equals("B") 
                    || color.equals("O") 
                    || color.equals("o")) {
            setColor = black;
            oppositeColor = white;
        }
        else {
            setColor = white;
            oppositeColor = black;
        }
        
        do {
            System.out.printf("Estimate forward moves [1,9]: ");
            forwardMoves = sc.nextInt();
            if(forwardMoves < 1 || forwardMoves > 9) {
                System.out.printf("Invalid moves. Try again...\n");
            }
            else
                break;
        }while(true);
        game.board = game.createBoard(black, white, dot);
        Board.printBoard(game.board);
        
        //elegxos seiras xrhsth-Ai
        if(oppositeColor == white) {
            playerAi = false;
            
        }
        else {
            playerAi = true;
        }
        
        do {
            //otan den yparxoun diathesimes kinhseis kai gia tous dyo
            //vlepe grammh 160
            if(!movesHuman && nextMove != null && nextMove.row == -1) {
                break;
            }
            //paizei to Ai
            if(playerAi) {
                System.out.printf("Player's %c turn\n\n", oppositeColor.color);
                Star.validMoves(game.board, oppositeColor, setColor, star, playerAi);
                Board.printBoard(game.board);
                Board.resetDot(game.board, dot);
                
                //antigrafh original pinaka gia na mhn xasoume to twrino stigmiotypo
                copyBoard = Arrays.stream(game.board).map(Pawn[]::clone).toArray(Pawn[][]::new);
                nextMove = new NextMove(copyBoard, oppositeColor, setColor, -1, -1);
                nextMove = PlayerAi.alphabeta(nextMove, copyBoard, oppositeColor, setColor, star, dot, Integer.MIN_VALUE, Integer.MAX_VALUE, forwardMoves, true);
                
                if(nextMove != null && nextMove.row == -1 && nextMove.col == -1) {
                    System.out.printf("No available moves!\n");
                    playerAi = false;
                    continue;
                }
                
                //metatroph row, col se 'c2' typo.
                row = nextMove.row; 
                col = nextMove.col;
                colChar = (char) ('a' + col);
                game.board = game.updateBoard(playerAi, row, col, oppositeColor, setColor);
                
                move = String.valueOf(colChar) + Integer.toString(row + 1);
                
                //ektypwsh pinaka
                Board.printBoard(game.board);
                
                System.out.printf("Player %c played: %s\n", oppositeColor.color, move);
                
                moveHistory.add(move);
                Board.moveHistory(moveHistory);
                //allagh seiras
                playerAi = false;
            
            }
            
            //paizei o xrhsths
            else {
                System.out.printf("Player's %c turn\n\n", setColor.color);
                movesHuman = Star.validMoves(game.board, setColor, oppositeColor, star, playerAi);
                if(!movesHuman) {
                    System.out.printf("No available moves!\n");
                    playerAi = true;
                    continue;
                }
                Board.printBoard(game.board);
                do {
                    System.out.printf("Enter your move (e.g. c2): ");
                    move = sc.next();
                    char[] characters = move.toCharArray();
                    row = Character.getNumericValue(characters[1]) - 1;
                    col = characters[0] - 'a';
                    if(game.board[row][col]!= star) {
                        System.out.printf("Invalid move. Try again!\n\n");
                    }
                    else
                        break;      
                }while(true);
                
                Board.resetDot(game.board, dot);
                game.board = game.updateBoard(playerAi, row, col, setColor, oppositeColor);
                
                Board.printBoard(game.board);
                System.out.printf("Player %c played: %s\n", setColor.color, move);
                moveHistory.add(move);
                Board.moveHistory(moveHistory);
                playerAi = true;
        
            }
        }while(movesHuman ||  (nextMove != null && nextMove.row != -1));
        
       Board.countPawns(blacks, whites, game.board);
        System.exit(0);
        
        
    }
}



import java.util.ArrayList;


//klash pou perigrafei ton pinaka antikeimenwn Pawn
public class Board {
    Pawn[][] board;
    
    public Board() {
        board = new Pawn[8][8];
    }
    
    //dhmiourgia Pinaka
    public Pawn[][] createBoard(Pawn black, Pawn white, Pawn dot) {
        int i, j;
        for(i = 0; i < 8; i++) {
            for(j = 0; j < 8; j++) {
                board[i][j] = dot;
            }
        }
        board[3][3] = white;
        board[3][4] = black;
        board[4][4] = white;
        board[4][3] = black;
        return(board);
    }
    
    //Ektypwsh pinaka
    public static void printBoard(Pawn[][] board) {
        int i, j;
        
        System.out.printf("  a b c d e f g h\n");
        for(i = 0; i < 8; i++) {
            System.out.printf("%d ", i + 1);
            for(j = 0; j < 8; j++) {
                System.out.printf("%c ", board[i][j].color);
            }
            System.out.printf("\n");
        }        
        System.out.printf("\n\n");
    }
    
    //Epanafora teleias se stoixeia ta opoia htan shmeiwmena me '*'
    public static void resetDot(Pawn[][] board, Pawn dot) {
        int i, j;
        for(i = 0; i < 8; i++) {
            for(j = 0; j < 8; j++) {
                if(board[i][j].color == '*') {
                    board[i][j] = dot;
                }
            }
        }
    }
    
    

    //elegxos kai metattoph Pawns gia ton xrhsth (Gia to Ai yparxei allh methodos sthn klash NextMove.java
    public Pawn[][] updateBoard(boolean playerAi, int y, int x, Pawn setcolor, Pawn oppcolor) {
        int row = y;
        int col = x;
        
        
        this.board[row][col] = setcolor;
        //tsekarisma katw kai metatroph
        for(row = y + 1; row < 8 && this.board[row][x] == oppcolor; row++) {}
        if(row < 8 && this.board[row][x] == setcolor) {
            int i = row;
            for(row = y + 1; row < i; row++) {
                this.board[row][x] = setcolor;
            }
        }
        
        //tsekarisma panw kai metatroph
        for(row = y - 1; row >= 0 && this.board[row][x] == oppcolor; row--) {}
        if(row >= 0 && this.board[row][x] == setcolor) {
            int i = row;
            for(row = y - 1; row > i; row--) {
                this.board[row][col] = setcolor;
            }
        }
        
        //tsekarisma deksia kai metatroph
        for(col = x + 1; col < 8 && this.board[y][col] == oppcolor; col++) {}
        if(col < 8 && this.board[y][col] == setcolor) {
            int i = col;
            for(col = x + 1; col < i; col++) {
                this.board[y][col] = setcolor;
            }
        }
        
        //tsekarisma aristera kai metatroph
        for(col = x - 1; col >= 0 && this.board[y][col] == oppcolor; col--) {}
        if(col >= 0 && this.board[y][col] == setcolor) {
            int i = col;
            for(col = x -1; col > i; col--) {
                this.board[y][col] = setcolor;
            }  
        }
        
        //tsekarisma panw aristera kai metatroph
        for(row = y - 1, col = x - 1; row >= 0 && col >= 0 && this.board[row][col] == oppcolor; row--, col--) {}
        if( row >= 0 && col >= 0 && this.board[row][col] == setcolor) {
            int i = row;
            int j = col;
            for(row = y -1, col = x - 1; row > i && col > j; row--, col--) {
                this.board[row][col] = setcolor;
            }
        }
     
        //tsekarisma panw deksia kai metatroph
        for(row = y - 1, col = x + 1; row >= 0 && col < 8 && this.board[row][col] == oppcolor; row--, col++) {}
        if(row >=0 && col < 8 && this.board[row][col] == setcolor) {
            int i = row;
            int j = col;
            for(row = y - 1, col = x + 1; row > i && col < j; row--, col++) {
                this.board[row][col] = setcolor;
            }
        }
        //tsekarisma katw deksia kai metatroph
        for(row = y + 1, col = x + 1; row < 8 && col < 8 && this.board[row][col] == oppcolor; row++, col++) {}
        if(row < 8 && col < 8 && this.board[row][col] == setcolor) {
            int i = row;
            int j = col;
            for(row = y + 1, col = x + 1; row < i && col < j; row++, col++) {
                this.board[row][col] = setcolor;
            }
        }    
        
        //tsekarisma katw aristera kai metatroph
        for(row = y + 1, col = x - 1; row < 8 && col >= 0 && this.board[row][col] == oppcolor; row++, col--) {}
        if(row < 8 && col >= 0 && this.board[row][col] == setcolor) {
            int i = row;
            int j = col;
            for(row = y + 1, col = x - 1; row < i && col > j; row++, col--) {
                this.board[row][col] = setcolor;
            }
        }
        
    
    Pawn[][] returnBoard = this.board;
    return(returnBoard);
    }
    
    
    //Methodos pou ektypwnei to istoriko twn kinhsewn kai twn dyo paiktwn
    public static void moveHistory(ArrayList<String> move) {
        System.out.printf("Moves history: ");
        for(String oneMove: move) {
            System.out.printf("%s ", oneMove);
        }
        System.out.printf("\n\n");
    }
    
    //Metrhsh twn black, white Pawns kai anadeiksh nikhth 'h isopalias
    public static void countPawns(int blacks, int whites, Pawn[][] board) {
        int i, j;
        for(i = 0; i < 8; i++) {
            for(j = 0; j < 8; j++) {
                if(board[i][j].color == 'O') {
                    blacks++;
                }
                if(board[i][j].color == 'X') {
                    whites++;
                }
            }
        }
        System.out.printf("X:%d/O:%d ",whites, blacks);
        if(blacks > whites) {
            System.out.printf("Player O won!\n\n");
        }
        else if(blacks < whites) {
            System.out.printf("Player X won\n\n");
        }
        else {
            System.out.printf("It's a draw!\n\n");
        }
    }
}    

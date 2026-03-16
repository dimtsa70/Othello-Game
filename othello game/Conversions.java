
package ce326.hw2;

public class Conversions {
    char[][] board, previousBoard;
    char color, oppositeColor;
    int xCord, yCord, score;
    
    static int[][] weightBoard = {{500, -20, 10, 5, 5, 10, -20, 500},
                                  {-20, -50, -2, -2, 2, -2, -50, -20},
                                  {10, -2, 1, 1, 1, 1, -2, 10},
                                  {5, -2, 1, 0, 0, 1, -2, 5},
                                  {5, -2, 1, 0, 0, 1, -2, 5},
                                  {10, -2, 1, 1, 1, 1, -2, 10},
                                  {-20, -50, -2, -2, -2, -2, -50, -20},
                                  {500, -20, 10, 5, 5, 10, -20, 500}};
    
    public Conversions(char[][] previousBoard, int xCord, int yCord, char color, boolean player) {
        this.previousBoard = previousBoard;
        this.board = new char[8][8];
        
        
        this.xCord = xCord;
        this.yCord = yCord;
        this.color = color;
        
        if(color == 'X') {//X = white color, O = black color
            oppositeColor = 'O';
        }
        else {
            oppositeColor = 'X';
        }
        /*if(xCord >= 0 && yCord >= 0) {
            updateBoard(player, xCord, yCord);
        }*/
        this.score = 60;
    }
    
    public  void createBoard() {
        int i, j;
        for(i = 0; i < 8; i++) {
            for(j = 0; j < 8; j++) {
                this.board[i][j] = '.';
            }
        }
        this.board[3][3] = 'X';
        this.board[3][4] = 'O';
        this.board[4][4] = 'X';
        this.board[4][3] = 'O';
    }
    
    public void updateBoard(boolean player, int xCord, int yCord) {
        char setcolor = this.color;
        char oppcolor = this.oppositeColor;
        int row, col;
        
        if(!player) {
            if(this.color == 'X') {
                setcolor = 'O';
                oppcolor = 'X';
            }
            else {
                setcolor = 'X';
                oppcolor = 'O';
            }
        
        }
        this.board[yCord][xCord] = setcolor;

        //tsekarisma katw
        for(row = yCord + 1; row < 8 && this.board[row][xCord] == oppcolor; row++) {}
        if(row <= 8 && this.board[row][xCord] == setcolor) {
           flip("S", setcolor, yCord, xCord, row, xCord);
  
        }
        //tsekarisma panw
        for(row = yCord - 1; row >= 0 && this.board[row][xCord] == oppcolor; row--) {}
        if(row > 0 && this.board[row][xCord] == setcolor) {
            flip("N", setcolor, yCord, xCord, row, xCord);
        }
        
        //tsekarisma deksia
        for(col = xCord + 1; col < 8 && this.board[yCord][col] == oppcolor; col++) {}
        if(col <= 8 && this.board[yCord][col] == setcolor){
            flip("E", setcolor, yCord, xCord, yCord, col);
        }
        
        //tsekarisma aristera
        for(col = xCord - 1; col >= 0 && this.board[yCord][col] == oppcolor; col--) {}
        if(col > 0 && this.board[yCord][col] == setcolor){
            flip("W", setcolor, yCord, xCord, yCord, col);
        }
        
        //tsekarisma katw-aristera
        for(row = yCord + 1, col = xCord - 1; row < 8 && col >= 0 && this.board[row][col] == oppcolor; row++, col--) {}
        if(row <= 8 && col > 0 && this.board[row][col] == setcolor) {
            flip("SW", setcolor, yCord, xCord, row, col);
        }
        
        //tsekarisma katw-deksia
        for(row = yCord + 1, col = xCord + 1; row < 8 && col < 8 && this.board[row][col] == oppcolor; row++, col++) {}
        if(row <= 8 && col <= 8 && this.board[row][col] == setcolor) {
            flip("SE", setcolor, yCord, xCord, row, col);
        }
        
        //tsekarisma panw-deksia
        for(row = yCord - 1, col = xCord + 1; row >= 0 && col < 8 && this.board[row][col] == oppcolor; row--, col++) {}
        if(row > 0 && col <= 8 && this.board[row][col] == setcolor) {
            flip("NE", setcolor, yCord, xCord, row, col);
        }
        
        //tsekarisma panw-aristera
        for(row = yCord - 1, col = xCord - 1; row >= 0 && col >= 0 && this.board[row][col] == oppcolor; row--, col--) {}
        if(row > 0 && col > 0 && this.board[row][col] == setcolor) {
            flip("NW", setcolor, yCord, xCord, row, col);
        }
    }

    
    public int flip(String direction, char color, int yCord, int xCord, int y2, int x2) {
        int flipped = 0, row, col;
        
        switch (direction) {
            case "S":
                for(row = yCord + 1; row < y2; row++) {
                    this.board[row][xCord] = color;
                    flipped++;
                }
                break;
            case "N":
                for(row = y2 + 1; row < yCord; row++) {
                    this.board[row][xCord] = color;
                    flipped++;
                }
                break;
            case "E":
                for(col = xCord + 1; col < x2; col++) {
                    this.board[yCord][col] = color;
                    flipped++;
                }
                break;
            case "W":
                for(col = x2 + 1; col < xCord; col++) {
                    this.board[yCord][col] = color;
                    flipped++;
                }
                break;
            case "SW":
                for(row = yCord + 1, col = xCord - 1; row < y2 && col > x2; row++, col--) {
                    this.board[yCord][col] = color;
                    flipped++;
                }
                break;
            case "SE":
                for(row = yCord + 1, col = xCord + 1; row < y2 && col < x2; row++, col++) {
                    this.board[yCord][col] = color;
                    flipped++;
                }
                break;   
            case "NE":
                for(row = yCord - 1, col = xCord + 1; row > y2 && col < x2; row--, col++) {
                    this.board[yCord][col] = color;
                    flipped++;
                }
                break;
            case "NW":
                for(row = yCord - 1, col = xCord - 1; row > y2 && col > x2; row--, col--) {
                    this.board[yCord][col] = color;
                    flipped++;
                }
                break;
        }
        return(flipped);
    }
    
    
    public int score() {
        int sumScore = 0, i, j;
        
        for(i = 0; i < 8; i++) {
            for(j = 0; j < 8; j++) {
                if(board[i][j] == this.color) {
                    sumScore += Conversions.weightBoard[i][j];
                }
            }
    
        }
        return(sumScore);
    }
}

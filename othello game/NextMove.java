


//h klash pou kanei thn epomenh kinhsh
public class NextMove {
    int row, col, score;
    Pawn[][] board;
    Pawn setColor, oppositeColor;
    
     static int[][] evaluateBoard = {
        {500, -20, 10, 5, 5, 10, -20, 500},
        {-20, -50, -2, -2, -2, -2, -50, -20},
        {10,   -2,  1,  1,  1,  1,  -2,  10},
        {5,    -2,  1,  0,  0,  1,  -2,  5},
        {5,    -2,  1,  0,  0,  1,  -2,  5},
        {10,   -2,  1,  1,  1,  1,  -2,  10},
        {-20, -50, -2, -2, -2, -2, -50, -20},
        {500, -20, 10, 5, 5, 10, -20, 500}};
    
    public NextMove(Pawn[][] board, Pawn setColor, Pawn oppositeColor, int row, int col) {
        
        this.setColor = setColor;
        this.oppositeColor = oppositeColor;
        this.row = row;
        this.col = col;
        if(row >=0 && col >=0) {
            this.board = updateBoard(board, row, col, setColor, oppositeColor);
            this.score = 0; //to score to ypologizw sthn PlayerAi kathws edw kathe fora allazoun ta setColor
                            //kai oppositeColor
        }
        else {  //periptwsh pou o alphabeta den vrei diathesimi kinhsh
            this.board = board;
            this.score = 0;
        }
    }
    //ypologismos score
     public static int score(Pawn[][] board, Pawn setColor, Pawn dot) {
        int sumScore = 0;
        int i, j;
        for( i = 0; i < 8; i++) {
            for(j = 0; j < 8; j++) {
                if(board[i][j] == setColor) {
                    sumScore = sumScore + evaluateBoard[i][j];
                }
                else if(board[i][j] != setColor && board[i][j] != dot) {
                    sumScore = sumScore - evaluateBoard[i][j];
                }
            }
        }
        return sumScore;
    }

     
     //elegxos kai metatroph Pawn me Ai
     public static Pawn[][] updateBoard(Pawn[][] board, int y, int x, Pawn setcolor, Pawn oppcolor) {
        int row = y;
        int col = x;
        
        
        board[row][col] = setcolor;
        //tsekarisma katw kai metatroph
        for(row = y + 1; row < 8 && board[row][x] == oppcolor; row++) {}
        if(row < 8 && board[row][x] == setcolor) {
            int i = row;
            for(row = y + 1; row < i; row++) {
                board[row][x] = setcolor;
            }
        }
        
        //tsekarisma panw kai metatroph
        for(row = y - 1; row >= 0 && board[row][x] == oppcolor; row--) {}
        if(row >= 0 && board[row][x] == setcolor) {
            int i = row;
            for(row = y - 1; row > i; row--) {
                board[row][col] = setcolor;
            }
        }
        
        //tsekarisma deksia kai metatroph
        for(col = x + 1; col < 8 && board[y][col] == oppcolor; col++) {}
        if(col < 8 && board[y][col] == setcolor) {
            int i = col;
            for(col = x + 1; col < i; col++) {
                board[y][col] = setcolor;
            }
        }
        
        //tsekarisma aristera kai metatroph
        for(col = x - 1; col >= 0 && board[y][col] == oppcolor; col--) {}
        if(col >= 0 && board[y][col] == setcolor) {
            int i = col;
            for(col = x -1; col > i; col--) {
                board[y][col] = setcolor;
            }  
        }
        
        //tsekarisma panw aristera kai metatroph
        for(row = y - 1, col = x - 1; row >= 0 && col >= 0 && board[row][col] == oppcolor; row--, col--) {}
        if( row >= 0 && col >= 0 && board[row][col] == setcolor) {
            int i = row;
            int j = col;
            for(row = y -1, col = x - 1; row > i && col > j; row--, col--) {
                board[row][col] = setcolor;
            }
        }
     
        //tsekarisma panw deksia kai metatroph
        for(row = y - 1, col = x + 1; row >= 0 && col < 8 && board[row][col] == oppcolor; row--, col++) {}
        if(row >=0 && col < 8 && board[row][col] == setcolor) {
            int i = row;
            int j = col;
            for(row = y - 1, col = x + 1; row > i && col < j; row--, col++) {
                board[row][col] = setcolor;
            }
        }
        //tsekarisma katw deksia kai metatroph
        for(row = y + 1, col = x + 1; row < 8 && col < 8 && board[row][col] == oppcolor; row++, col++) {}
        if(row < 8 && col < 8 && board[row][col] == setcolor) {
            int i = row;
            int j = col;
            for(row = y + 1, col = x + 1; row < i && col < j; row++, col++) {
                board[row][col] = setcolor;
            }
        }    
        
        //tsekarisma katw aristera kai metatroph
        for(row = y + 1, col = x - 1; row < 8 && col >= 0 && board[row][col] == oppcolor; row++, col--) {}
        if(row < 8 && col >= 0 && board[row][col] == setcolor) {
            int i = row;
            int j = col;
            for(row = y + 1, col = x - 1; row < i && col > j; row++, col--) {
                board[row][col] = setcolor;
            }
        }
        
    
    Pawn[][] returnBoard = board;
    return(returnBoard);
    }
}

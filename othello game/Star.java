


public class Star extends Pawn {
    public Star(char color) {
        this.color = color;
    }
    
    //h methodos epistrefei tis diathesimes kinhseis ston xrhsth
    public  static boolean validMoves(Pawn[][] board, Pawn setColor, Pawn oppositeColor,Pawn star, boolean player) {
        int i, j, row, col, countMoves = 0;
        for(i = 0; i < 8; i++) {
            for(j = 0; j < 8; j++) {
                if(board[i][j] == oppositeColor) {
                    //tsekarisma panw
                    if(i + 1 < 8 && board[i + 1][j] == setColor) {
                        for(row = i - 1; row >= 0 && board[row][j] != setColor; row--) {
                            if(board[row][j].color == '*') {
                                break;
                            }
                            else if(board[row][j].color == '.') {
                                board[row][j] = null;
                                board[row][j] = star;
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
                                countMoves++;
                                break;
                            }
                        }
                    } 
                    //tsekarisma deksia
                    if(j - 1 >= 0 && board[i][j - 1] == setColor && j > 0) {
                            for(col = j + 1; col < 8 && board[i][col] != setColor; col++) {
                                if(board[i][col].color == '*') {
                                    break;
                                }
                                if(board[i][col].color == '.') {
                                    board[i][col] = null;
                                    board[i][col] = star;
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
                            if(row >= 0 && col >= 0 && board[row][col].color == '.') {
                                board[row][col] = null;
                                board[row][col] = star;
                                countMoves++;
                                break;
                            }
                    
                        }
                    }    
                    //tsekarisma panw-deksia
                    if(i + 1 < 8 && j - 1 >= 0 && board[i + 1][j - 1] == setColor) {
                        for(row = i - 1, col = j + 1; row >= 0 && col < 8 && board[row][col] != setColor; row--, col++) {
                            if(board[row][col].color == '*') {
                                    break;
                            }
                            if(row >= 0 && board[row][col].color == '.') {
                                board[row][col] = null;
                                board[row][col] = star;
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
                            if(col >= 0 && board[row][col].color == '.') {
                                board[row][col] = null;
                                board[row][col] = star;
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
                                countMoves++;
                                break;
                            }
                        }    
                    }
                } 
            }
        }    
        
        if(countMoves == 0) {
            return(false);
        }
        return (true);
    }

}

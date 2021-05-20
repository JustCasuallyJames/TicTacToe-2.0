/**
 * GameLogic class is
 */
public class GameLogic {
    private int _winning_row_num;
    private Board _board;
    public GameLogic(Board b, int winning_row_num){
        this._winning_row_num = winning_row_num;
        this._board = b;
    }
    //getters
    /**
     * get_winning_row_num gets the number you want for a winning row
     * @return the winning row number
     */
    public int get_winning_row_num() {
        return _winning_row_num;
    }

    //setters
    /**
     * set_winning_row_num sets the number you want for a winning row
     * @param winning_row_num - the number you want for a winning row
     */
    public void set_winning_row_num(int winning_row_num) {
        this._winning_row_num = winning_row_num;
    }

    /**
     * did_win tries to see if the piece will win based on the winning_row_num
     * @param piece - the player piece
     * @return - whether or not you will win
     */
    public boolean did_win(char piece){
        return win_vertical(piece) || win_horizontal(piece) || win_diagonal(piece);
    }

    //helper functions

    /**
     * win_vertical helps find out if the player piece has any potential vertical wins
     * @param piece - the piece
     * @return - whether or not the player piece has won.
     */
    private boolean win_vertical(char piece){
        int cols = _board.get_cols(); //finds out how many cols there are
        int rows = _board.get_rows(); //finds out how many rows there are
        int counter = 0;
        for (int i = 0 ; i < cols; i++){ //THIS IS FOR THE COLUMNS
            for (int j = 0; j < rows;j++){
                if (_board.get_cell(j,i) == piece){ // [1][0] [2][0] [3][0]
                    //if the board piece is equal to the piece you're checking for
                    counter++;
                    if (counter == _winning_row_num){
                        //if the number of pieces in a row is equal to the winning row num, then you win
                        return true;
                    }
                }else{
                    counter = 0;
                }
            }
        }
        return false; //since the player never won
    }

    private boolean win_horizontal(char piece){
        int cols = _board.get_cols(); //finds out how many cols there are
        int rows = _board.get_rows(); //finds out how many rows there are
        int counter = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (_board.get_cell(i,j) == piece){ // [0][1] [0][2] [0][3]
                    //if the board piece is equal to the piece you're checking for
                    counter++;
                    if (counter == _winning_row_num){
                        //if the number of pieces in a row is equal to the winning row num, then you win
                        return true;
                    }
                }else{ //then that means it wasn't in a row
                    counter = 0;
                }
            }
        }
        return false;
    }

    private boolean win_diagonal(char piece){
        return win_left_diagonal(piece) || win_right_diagonal(piece);
    }
    /*
         left diagonal
            0 1 2 3 4
         0  O . . . .
         1  X O . . .
         2  . X O . .
         3  . . X O .
         4  . . . X O

        Even Number Board
            0 1 2 3 4 5
         0  X . . . . .
         1  . X . . . .
         2  . . X . . .
         3  O . . X . .
         4  . O . . X .
         5  . . O . . X
 */
    public boolean win_left_diagonal(char piece){
        int rows = _board.get_rows();
        int cols = _board.get_cols();
        int counter = 0;
        int counter1 = 0;
        int increment = 2;
        //this handles the bottom half the left complex diagonal
        for (int i = rows-3; i >= 0; i--, increment--){
            //start at a certain point and work left diagonally
            for (int j = 0; j < rows-increment; j++){
                /*
                if (j+i == rows || j+i == cols){ //to make sure we do not go out of bounds
                    //this is also needed for _winning_row_num being smaller than the number of rows (complex diagonal)
                    break;
                }else
                 */
                if(_board.get_cell(i+j,j) == piece){ //checks for simple diagonal and complex diagonal
                    //does the bottom half of triangle (if you divided the board into a triangle)
                    counter++;
                    if(counter == _winning_row_num){
                        return true;
                    }
                }else{
                    counter = 0;
                }
            }
        }
            /*
        simple left diagonal
            0 1 2 3 4
         0  O . . . .
         1  X O . . .
         2  . X O . .
         3  . . X O .
         4  . . . X O

        Even Number Board
            0 1 2 3 4 5
         0  X . . X . .
         1  . X . . X .
         2  . . X . . X
         3  O . . X . .
         4  . O . . X .
         5  . . O . . X

 */
        increment = 0;
        //upper half of the diagonal
        for (int i = 0; i <= rows-3; i++, increment++){ //rows-3 since the minimum is 3 in a row, so we count for all
            //possibilities
            for(int j = 0; j < rows-increment; j++){ //rows-increment starts at the simple diagonal inching upwards
                if(_board.get_cell(j,j+i) == piece){
                    counter1++;
                    if(counter1 == _winning_row_num){ //if X number in a row, then it's true
                        return true;
                    }
                }else{
                    counter1=0;
                }
            }
        }
        return false;
    }
    /*
        0 1 2 3 4
     0  X . O . .
     1  . X . O .
     2  X . X . O
     3  . X . X .
     4  . . X . X

        0 1 2 3 4
     0  . X . . .
     1  O . X . .
     2  X O . X .
     3  . X O . .
     4  . . X O .

        0 1 2 3 4
     0  . . . . .
     1  . . . . .
     2  . . . . .
     3  . . . . .
     4  . . . . .

    */
    public boolean win_right_diagonal(char piece){
        int rows = _board.get_rows();
        int cols = _board.get_cols();
        int counter = 0;
        int counter1 = 0;
        int increment = 2;
        /*
                0 1 2 3 4
             0  . . . X O
             1  . . X O .
             2  . X O . X
             3  X O . X .
             4  O . X . .

            Even Number Board
                0 1 2 3 4 5
             0  . . O . . X
             1  . O . . X .
             2  O . . X . .
             3  . . X . . O
             4  . X . . O .
             5  X . . O . .
        */
        //this handles the upper half of the triangle (if it was split like a triangle)
        for (int i = 2; i < rows; i++, increment++){
            //start at a certain point and work left diagonally
            for (int j = 0; j <= increment; j++){
                if(_board.get_cell(i-j,j) == piece){ //checks for simple diagonal and complex diagonal
                    //does the bottom half of triangle (if you divided the board into a triangle)
                    counter++;
                    if(counter == _winning_row_num){
                        return true;
                    }
                }else{
                    counter = 0;
                }
            }
        }

        increment = 0;
        int k;
        /*
        1st: i = 0; i <= 5
        2nd: i = 1; i <= 4
        3rd: i = 2; i <= 3
        4th: i = 3; i <= 2 <== doesn't run

        1st: i = 0; i <= 6
        2nd: i = 1; i <= 5
        3rd: i = 2; i <= 4
        4th: i = 3; i <= 3
        5th: i = 4; i <= 2 <== doesn't run
         */
        for (int i = 0; i <= rows-increment; i++, increment++){
            k = i;
            for(int j = rows - 1; j >= increment; j--, k++){
                if(_board.get_cell(j,k) == piece){
                    counter1++; //indicates that there's some value there
                    if (counter1 == _winning_row_num){
                        return true; //if there's some number in a row (the winning row number), then winner!
                    }
                } else{
                    counter1 = 0;
                }
            }
        }
        return false;
    }
}

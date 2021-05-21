/**
 * The board class is a Board object that displays the board and its contents
 */
public class Board {
    private char[][] _board;
    private final char _EMPTY = '.';
    private int _rows;
    private int _cols;
    /**
     * Constructs a board with a given amount of rows and cols.
     * @param rows
     * @param cols
     */
    public Board(int rows, int cols){
        this._rows = rows;
        this._cols = cols;
        this._board = new char[rows][cols]; //this creates a board with some number of rows and cols
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                this._board[i][j] = _EMPTY; //this sets each element to a period to indicate that it's vacant
            }
        }
    }
    //getters
    /**
     * get the amount of rows
     * @return
     */
    public int get_rows() {
        return _rows;
    }

    /**
     * get the amount of cols
     * @return - the columns of the board
     */
    public int get_cols() {
        return _cols;
    }
    /**
     * get_board returns the board with all of its contents
     * @return
     */
    public char[][] get_board() {
        return _board;
    }

    /**
     * get_cell returns the value at the given row and col
     * @param row - the row
     * @param col - the col
     * @return - the value at that point
     */
    public char get_cell(int row, int col){
        return _board[row][col];
    }
    //setters

    /**
     * sets the number of cols you want in the board
     * @param _cols - the number of the columns
     */
    public void set_cols(int _cols) {
        this._cols = _cols;
    }

    /**
     * sets the amount of rows you want in the board
     * @param _rows - the number of rows
     */
    public void set_rows(int _rows) {
        this._rows = _rows;
    }

    /**
     * set_cell sets a certain cell on the board to a specific character piece
     * @param row - the row you want to be at
     * @param col - the col you want to be at
     * @param piece - the piece that you want to set the cell to
     */
    public void set_cell(int row, int col, char piece){
        this._board[row][col] = piece;
    }

    /**
     * is_vacant checks if the cell at that point is empty
     * @param row - the row you want to check
     * @param col - the col you want to check
     * @return - whether or not the board is empty at that cell
     */
    public boolean is_vacant(int row, int col){
        return this._board[row][col] == _EMPTY; //this means that if the board at that row and col is a period, it's empty.
    }

    /**
     * is_full_board checks whether the board is all filled up with anything but an empty cell
     * @return false if the board isn't full
     */
    public boolean is_full_board(){
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _cols; j++) {
                if(_board[i][j] == '.'){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * print_board prints out the board with the given rows and columns
     */
    public void print_board(){
        System.out.print("  ");
        for(int k = 0; k < _rows; k++){
            System.out.print(k + " ");
        }
        System.out.println();
        int l = 0;
        for(int i = 0; i < _rows; i++, l++){
            System.out.print(l + " ");
            for (int j = 0; j < _cols; j++){
                System.out.print(_board[i][j] + " "); //prints out the value at that array index
            }
            System.out.println();
        }
    }
}

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Board _board;
    private GameLogic _gamelogic;
    private ArrayList<Player> _players;

    public Game(){
        this._players = new ArrayList<Player>();
    }
    public Game(Board b, GameLogic gl){
        this._board = b;
        this._gamelogic = gl;
        this._players = new ArrayList<Player>();
    }
    public void play(){
        System.out.println("Welcome to TicTacToe 2.0!");
        Scanner scan = new Scanner(System.in);
        player_prompt(scan); //calls the player_prompt method to execute the functionality
        create_board(); //creates the board
        in_a_row_prompt(scan); //
        playing_game(scan);
    }

    private void player_prompt(Scanner scan){
        int numPlayers = 0;
        boolean keepGoingPlayers = true;
        //handled input validation for the number of players that will be playing.
        do{
            try{
                System.out.println("How many players will be playing? (3-10 players only)");
                numPlayers = scan.nextInt();
                scan.nextLine();
                if (numPlayers >= 3 && numPlayers <= 10) {
                    keepGoingPlayers = false;
                }else{
                    System.out.println("Only 3-10 players in this TicTacToe Game. Please re-enter the number of players");
                }
            }catch(InputMismatchException e){ //checks for characters that are non integers
                System.out.println("You typed in an non-integer, please type in an integer.");
                scan.nextLine();
                keepGoingPlayers = true;
            }

        }while(keepGoingPlayers);

        for(int i = 1; i <= numPlayers; i++){
            System.out.printf("What is the name of player #%o", i);
            System.out.print(":");
            if(scan.hasNextLine()){ //checks if there is a string (for the player name)
                String name = scan.nextLine();
                char charPiece = '\0'; //have to initialize it to a null character
                //do-while loop is needed for input validation regarding the character piece
                boolean keepGoingPiece = true;
                do{
                    System.out.printf("What is the character piece of player #%o", i);
                    System.out.print(":");
                    if(scan.hasNextLine()){ //checks if there is a char (for the character piece)
                        String piece = scan.nextLine();
                        if(piece.length() > 1){
                            System.out.println("Please insert a single character instead of a word for your character piece.");
                        }else if(piece.charAt(0) == '.'){ //if it's equal to the board piece
                            System.out.println("Please choose a different character piece other than '.' ");
                        }else{
                            charPiece = piece.charAt(0);
                            keepGoingPiece = false;
                        }
                    }
                    //needed to check if the player piece will be the same.
                    if(!keepGoingPiece){
                        for (Player player : _players) { //for each player
                            if (charPiece == player.get_piece()) { //checks if the character piece is the same as
                                System.out.println("You have selected someone else's character piece. Please choose a different character piece.");
                                keepGoingPiece = true;
                            }
                        }
                    }
                }while(i <= numPlayers && keepGoingPiece);
                //add a new player because at this point, all input has been validated.
                _players.add(new Player(name,charPiece));
            }
        }
    }

    private void create_board(){
        _board = new Board(_players.size()+1,_players.size()+1);
        System.out.printf("\nA board of size: %o", _players.size()+1);
        System.out.print(" has been created.\n");
        _board.print_board();
    }

    private void in_a_row_prompt(Scanner scan){
        boolean keepGoingInARow = true;
        do{
            System.out.printf("\nHow many pieces in a row defines a winner? (min of 3 and a max of the board row size: %o",
                    _players.size()+1);
            System.out.print(")");
            try{
                int in_a_row = scan.nextInt();
                if(in_a_row >= 3 && in_a_row <= _board.get_rows()){ //this means that it's a valid pieces in a row
                    _gamelogic = new GameLogic(_board, in_a_row);
                    keepGoingInARow = false;
                }else{
                    System.out.print("Please type in a valid number with the given criteria above.");
                }
            }catch(InputMismatchException e){ //invalid input
                System.out.println("You entered a non integer value. Please type it again.");
                scan.nextLine();
            }
        }while(keepGoingInARow);
    }

    private void playing_game(Scanner scan){
        boolean keepGoing = true;
        while(keepGoing){
            for(int i = 0; i < _players.size(); i++){
                System.out.printf("It is %s", _players.get(i).get_name());
                System.out.print(" turn to play.\n");
                int[] ans = row_and_col_prompt(scan); //ans[0] is the row, ans[1] is the col
                if(_board.is_vacant(ans[0], ans[1])){ //if there's no piece there.
                    //sets that position on the board to the piece
                    _board.set_cell(ans[0],ans[1],_players.get(i).get_piece());
                    if(_gamelogic.did_win(_players.get(i).get_piece())){ //if the player won, then we congratulate them!
                        congratulations(_players.get(i));
                        keepGoing = false;
                        _board.print_board();
                        break;
                    }else if(_board.is_full_board()){
                        tie_game();
                        keepGoing = false;
                    }
                }else{
                    System.out.println("That place is occupied. Please choose a different position.");
                    i--; //This is so that the for loop starts from where it was before (since it does a i++)
                }
            }
        }
    }

    private int[] row_and_col_prompt(Scanner scan){
        int[] input = new int[2];
        int row,col;
        boolean keepGoingRow = true;
        boolean keepGoingCol = true;
        //the double while loop keeps running until the player types in a valid column.
        while(keepGoingRow){
            _board.print_board();
            try{
                System.out.println("Please enter a row (where the first row is the 0th row):");
                row = scan.nextInt();
                input[0] = row;
                if (row >= 0 && row < _players.size()+1){
                    keepGoingRow = false;
                    while(keepGoingCol){
                        try{
                            System.out.println("Please enter a column (where the first column is the 0th column):");
                            col = scan.nextInt();
                            input[1] = col;
                            if(col >= 0 && col < _players.size()+1){
                                keepGoingCol = false;
                            }else{
                                System.out.println("Please type in a valid column number.");
                            }
                        }catch(InputMismatchException e){ //invalid column input
                            System.out.println("You typed in a non integer for the column. Please type it again.");
                            scan.nextLine();
                        }
                    }
                }else{
                    System.out.println("Please type in a valid row number.");
                }
            }catch (InputMismatchException e){ //catch the invalid row character
                System.out.println("You typed in a non integer for the row. Please type it again.");
                scan.nextLine();
            }
        }
        return input;
    }

    public void congratulations(Player p){
        System.out.printf("Congratulations! %s", p.get_name());
        System.out.print(" has won!!\n");
    }

    public void tie_game(){
        System.out.println("This game is tied! No one wins!");
    }
}

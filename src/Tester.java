public class Tester {
    boolean DEBUG_LEFT_DIAGONAL = true;
    boolean DEBUG_RIGHT_DIAGONAL = true;
    boolean DEBUG_WIN = true;
    public void left_diagonal_test(){
        Board b = new Board(5,5);
        Board b1 = new Board(5,5);
        Board b2 = new Board(5,5);
        Board b3 = new Board(5,5);
        Board b4 = new Board(5,5);
        GameLogic gl = new GameLogic(b,5);
        GameLogic gl1 = new GameLogic(b1,4);
        GameLogic gl2 = new GameLogic(b2,3);
        GameLogic gl3 = new GameLogic(b3,4);
        GameLogic gl4 = new GameLogic(b4,3);

        //this is for five in a row
        b.set_cell(0,0,'O');
        b.set_cell(1,1,'O');
        b.set_cell(2,2,'O');
        b.set_cell(3,3,'O');
        b.set_cell(4,4,'O');
        b.print_board();
        System.out.println();
        if (DEBUG_LEFT_DIAGONAL){
            System.out.println(gl.win_left_diagonal('O'));
            assert gl.win_left_diagonal('O');
        }

        //this is for the three in a row
        b2.set_cell(2,0,'X');
        b2.set_cell(3,1,'X');
        b2.set_cell(4,2,'O');

        b2.set_cell(1,0,'X');
        b2.set_cell(2,1,'X');
        b2.set_cell(3,2,'X');

        b2.print_board();
        if (DEBUG_LEFT_DIAGONAL){
            System.out.println(gl2.win_left_diagonal('X'));
            assert gl2.win_left_diagonal('X') ;
        }

        //this is for the four in a row
        b1.set_cell(1,0,'X');
        b1.set_cell(2,1,'X');
        b1.set_cell(3,2,'X');
        b1.set_cell(4,3,'X');
        b1.print_board();
        System.out.println();
        if(DEBUG_LEFT_DIAGONAL){
            System.out.println(gl1.win_left_diagonal('X'));
            assert gl1.win_left_diagonal('X');
        }

        b3.set_cell(0,1,'X');
        b3.set_cell(1,2,'X');
        b3.set_cell(2,3,'X');
        b3.set_cell(3,4,'X');
        b3.print_board();
        System.out.println();
        if (DEBUG_LEFT_DIAGONAL){
            System.out.println(gl3.win_left_diagonal('X'));
            assert gl3.win_left_diagonal('X');
        }

        b4.set_cell(0,2, 'X');
        b4.set_cell(1,3, 'X');
        b4.set_cell(2,4, 'X');
        b4.print_board();
        if(DEBUG_LEFT_DIAGONAL){
            System.out.println(gl4.win_left_diagonal('X'));
            assert gl4.win_left_diagonal('X');
        }
    }

    public void right_diagonal_test(){
        Board b = new Board(5,5);
        Board b1 = new Board(5,5);
        Board b2 = new Board(5,5);
        Board b3 = new Board(5,5);
        Board b4 = new Board(6,6);
        GameLogic gl = new GameLogic(b,5);
        GameLogic gl1 = new GameLogic(b1,4);
        GameLogic gl2 = new GameLogic(b2,3);
        GameLogic gl3 = new GameLogic(b3,4);
        GameLogic gl4 = new GameLogic(b4,3);

        //this is for five in a row
        b.set_cell(0,4,'O');
        b.set_cell(1,3,'O');
        b.set_cell(2,2,'O');
        b.set_cell(3,1,'O');
        b.set_cell(4,0,'O');
        b.print_board();
        if (DEBUG_RIGHT_DIAGONAL){
            System.out.println(gl.win_right_diagonal('O'));
            assert gl.win_right_diagonal('O');
        }

        //this is for the three in a row
        b2.set_cell(0,2,'X');
        b2.set_cell(1,1,'X');
        b2.set_cell(2,0,'X');
        b2.print_board();
        if(DEBUG_RIGHT_DIAGONAL){
            System.out.println(gl2.win_right_diagonal('X'));
            assert gl2.win_right_diagonal('X');
        }

        //this is for the four in a row
        b1.set_cell(0,3,'X');
        b1.set_cell(1,2,'X');
        b1.set_cell(2,1,'X');
        b1.set_cell(3,0,'X');
        b1.print_board();
        if (DEBUG_RIGHT_DIAGONAL){
            System.out.println(gl1.win_right_diagonal('X'));
            assert gl1.win_right_diagonal('X');
        }

        b3.set_cell(1,4,'X');
        b3.set_cell(2,3,'X');
        b3.set_cell(3,2,'X');
        b3.set_cell(4,1,'X');
        b3.print_board();
        if(DEBUG_RIGHT_DIAGONAL){
            System.out.println(gl3.win_right_diagonal('X'));
            assert gl3.win_right_diagonal('X');
        }

        b4.set_cell(2,4, 'X');
        b4.set_cell(3,3, 'X');
        b4.set_cell(4,2, 'X');
        b4.print_board();
        if(DEBUG_RIGHT_DIAGONAL){
            System.out.println(gl4.win_right_diagonal('X'));
            assert gl4.win_right_diagonal('X');

        }
    }

    public void did_win_test(){
        Board b = new Board(5,5);
        GameLogic gl = new GameLogic(b,3);
        //this is for five in a row
        b.set_cell(0,4,'O');
        b.set_cell(1,3,'O');
        b.set_cell(2,2,'O');
        b.set_cell(3,1,'O');
        b.set_cell(4,0,'O');
        b.print_board();

        if(DEBUG_WIN){
            System.out.println(gl.did_win('O'));
            assert gl.did_win('O');

        }
    }

    public void game_test(){
        Game g = new Game();
        g.play();
    }
}

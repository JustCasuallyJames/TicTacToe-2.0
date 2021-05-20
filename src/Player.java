/**
 * The player class is supposed to represent a player object that has a name and a player piece
 */
public class Player {
    private String _name;
    private char _piece;

    //constructor

    /**
     * Player constructor constructs a player with a given name and character piece
     * @param name - the name of the player
     * @param piece - the character piece that the player has
     */
    public Player(String name, char piece){
        this._name = name;
        this._piece = piece;
    }

    //Accessors (getters)

    /**
     * get_name is the accessor for the private name variable
     * @return the name of the player
     */
    public String get_name() {
        return _name;
    }

    /**
     * get_piece is the accessor for the private piece variable
     * @return  the character piece of the player
     */
    public char get_piece() {
        return _piece;
    }

    //Mutators(setter)
    /**
     *  set_name is the mutator for the private name variable
     * @param name - the name that you want to change the player to
     */
    public void set_name(String name) {
        this._name = name;
    }

    /**
     * set_piece is the mutator for the private piece variable
     * @param piece - the player piece that you want changed
     */
    public void set_piece(char piece) {
        this._piece = piece;
    }
}

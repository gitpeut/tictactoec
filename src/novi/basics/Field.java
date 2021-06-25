package novi.basics;

public class Field {
    private boolean empty;
    private Player owner;
    private char printToken;

    public Field() {
        empty       = true;
        printToken  = ' ';
        owner       = null;
    }

    public void zero( char token){
        empty       = true;
        owner       = null;
        printToken  = token;
    }

    public boolean occupy(Player player) {

        boolean return_value = false;
        if (empty) {
            empty = false;
            owner = player;
            printToken = player.getToken();

            return_value = true;
        }
        return return_value;
    }

    public boolean isEmpty() {
        return empty;
    }

    public char getToken() {
        return printToken;
    }

    public boolean setToken(char token) {
        if (empty) {
            printToken = token;
            return true;
        }
        return false;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }

}
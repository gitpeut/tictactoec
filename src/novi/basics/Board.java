package novi.basics;

import java.util.Scanner;

public class Board {
    Field[][]    board = new Field[3][3];
    Player      winner = null;

    public Board() {
        init();
    }

    private void init(){
        int number = 0;
        for (int v = 0; v < 3; ++v) {
            for (int h = 0; h < 3; ++h) {
                board[v][h] = new Field();
            }
        }
    }

    public boolean isFull() {
        for (int v = 0; v < 3; ++v) {
            for (int h = 0; h < 3; ++h) {
                if (board[v][h].isEmpty()) return false;
            }
        }
        return true;
    }

    public void print() {
        for (int v = 0; v < 3; ++v) {
            for (int h = 0; h < 3; ++h) {
                if (h > 0) System.out.print("|");

                char value = board[h][v].getToken();
                if (value == 0) value = ' ';

                System.out.print("  " + value + "  ");
            }
            System.out.println();
            if (v < 2) System.out.println("----+-----+-----");
        }
    }

    public void zero() {
        winner = null;

        int number = 1;
        for (int v = 0; v < 3; ++v) {
            for (int h = 0; h < 3; ++h) {
                board[h][v].zero( (char) ('0' + number++) );
            }
        }
    }

    public boolean checkWinner() {
        if (isFull()) {
            winner = null;
            return true;
        }

        //check horizontals
        for (int v = 0; v < 3; ++v) {
            if (board[v][0].getOwner() == board[v][1].getOwner() &&
                    board[v][0].getOwner() == board[v][2].getOwner() &&
                    board[v][0].getOwner() != null) {

                winner = board[v][0].getOwner();
                return true;
            }
        }
        // check verticals
        for (int h = 0; h < 3; ++h) {
            if (board[0][h].getOwner() == board[1][h].getOwner() &&
                    board[0][h].getOwner() == board[2][h].getOwner() &&
                    board[0][h].getOwner() != null) {
                winner = board[0][h].getOwner();
                return true;
            }
        }
        // check diagonals
        if (board[0][0].getOwner() == board[1][1].getOwner() &&
                board[0][0].getOwner() == board[2][2].getOwner() &&
                board[1][1].getOwner() != null) {
            winner = board[0][0].getOwner();
            return true;
        }

        if (board[2][0].getOwner() == board[1][1].getOwner() &&
                board[2][0].getOwner() == board[0][2].getOwner() &&
                board[1][1].getOwner() != null) {
            winner = board[2][0].getOwner();
            return true;
        }
        return false;
    }


    public void generateMove( Player player) {
        boolean genValid = false;

        while (!genValid) {
            int x = (int) (Math.random() * (2.5));
            int y = (int) (Math.random() * (2.5));

            if (board[x][y].isEmpty() ) {
                genValid = true;
                board[x][y].occupy( player );
            }
        }

    }

    public void askMove( Player player){
        Scanner input = new Scanner( System.in );
        int number;
        boolean inputValid = false;

        while( !inputValid ) {
            System.out.print("Give number (1-9) of field to place your " + player.getToken() + "  ");
            number = input.nextInt();
            if ( number < 1 || number > 9 ) {
                System.out.println("Invalid input");
            }else{
                inputValid = true;
            }

            if ( inputValid ) {
                int x = (number-1) % 3 ;
                int y = (number-1) / 3 ;

                if (!board[x][y].isEmpty()) {
                    System.out.println("Field number " + number + "already taken");
                    inputValid = false;
                } else {
                    board[x][y].occupy(player);
                }
            }
        }
    }

}
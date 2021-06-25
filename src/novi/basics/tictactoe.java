package novi.basics;

import java.util.Scanner;

public class tictactoe {

    private Board    board;
    private Player[] player;
    int     playerIndex = 0;

    public tictactoe(){
        System.out.println("Tic, Tac and Toe");
        board   = new Board();
        player  = new Player[2];

        initPlayers();

        player[0].askName();

        if ( askComputerOpponent() ){
            player[1].setName( "computer");
            player[1].setComputer(true);
        }else{
            player[1].askName();
        }
    }

    private void initPlayers(){
        for ( int i =0 ; i < player.length; ++i) {
            player[i] = new Player();
        }
    }


    public void printScore(){
        for ( int i =0 ; i < player.length; ++i) {
            System.out.println(player[i].getName() + " score " + player[i].getScore());
        }
    }

    public void round(){

        if ( !player[0].askX() ){
            player[0].setToken('O');
            player[1].setToken('X');
            playerIndex = 1;
        }else{
            player[0].setToken('X');
            player[1].setToken('O');
            playerIndex = 0;
        }

        board.zero();
        board.print();

        while ( !board.checkWinner() ) {

            System.out.println( player[playerIndex].getName() + "'s turn: ");

            if ( player[playerIndex].getComputer()  ){
                board.generateMove(  player[playerIndex] );
            }else{
                board.askMove( player[playerIndex]);
            }
            board.print();

            playerIndex++;
            if ( playerIndex >= player.length) playerIndex = 0;

        }

        if ( board.winner != null ){
            board.winner.setScore(1);
            System.out.println("The winner is " + board.winner.getName() );
        }else{
            System.out.println("It's a draw, nobody won");
        }

        printScore();
    }

    public void play(){
        boolean gameOver = false;

        while ( !gameOver ){

            round();

            gameOver = !askAgain();
            if ( !gameOver ) board.zero();
        }

        System.out.println("Bye!");


    }


    public boolean askComputerOpponent() {
        Scanner input = new Scanner(System.in);
        boolean computer = false;

        System.out.print("Play against computer ([y]/n)  ");
        String answer = input.nextLine();

        if ( answer.equals("") ){
            computer = true;
        }else {
            switch (answer.charAt(0)) {
                case 'j':
                case 'y':
                case 'J':
                case 'Y':
                    computer = true;
                    break;
                default:
                    computer = false;
                    break;
            }
        }

        return computer;
    }

    public boolean askAgain(){
        Scanner input = new Scanner( System.in );
        boolean again = false;

        System.out.print("Play again ? (y/[N]) ");

        String answer = input.nextLine();
        if ( answer.equals("")) return false;

        switch( answer.charAt(0) ){
            case 'j':
            case 'y':
            case 'J':
            case 'Y':
                again = true;
                break;
            default:
                break;
        }

        return again;
    }



}

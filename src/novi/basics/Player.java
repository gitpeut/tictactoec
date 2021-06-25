package novi.basics;

import java.util.Scanner;

public class Player {

    private char    token;
    private int     score;
    private boolean computer = false;
    private String  name;

    public Player(){
        score = 0;
    }

    public String   getName(){
        return name;
    }
    public int      getScore(){
        return score;
    }
    public char     getToken(){
        return token;
    }
    public boolean  getComputer(){
        return computer;
    }

    public void setName( String name){
        this.name = name;
    }
    public void setScore( int score ){
        this.score += score ;
    }
    public boolean setToken( char token){
        this.token = token;
        return true;
    }
    public void  setComputer( boolean computer){
        this.computer = computer;
    }



    public boolean askX() {
        Scanner input = new Scanner(System.in);
        boolean wantX = false;

        System.out.print("X starts. Do you want X ([Y]/n)  ");
        String answer = input.nextLine();

        if ( answer.equals("") ){
            wantX = true;
        }else {
            switch (answer.charAt(0)) {
                case 'j':
                case 'y':
                case 'J':
                case 'Y':
                    wantX = true;
                    break;
                default:
                    wantX = false;
                    break;
            }
        }

        if( wantX ){
            token = 'X';
        }else{
            token = 'O';
        }
        return wantX;

    }

    public void askName() {
        Scanner input = new Scanner(System.in);
        boolean wantX = false;

        System.out.print("what is your name [you] ");
        String answer = input.nextLine();

        if ( answer.equals("") ){
            name = "you";
        }else {
            name = answer;
        }
    }


}

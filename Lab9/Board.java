package Lab9;

import java.util.Scanner;


public class Board {

    String[][] board = new String[10][10];

    public Board(){

        for (int row = 0; row <10; row++){
            for (int col = 0; col<10; col++){
                board[row][col] = ".";
            }
        }

    }


    public String toString(){
        String output = "";
        for (int row = 0; row <10; row++){
            for (int col = 0; col<10; col++){
                output += board[row][col] + " ";
            }
            output += "\n";
        }
        return output;
    }

    public void fogofWar(String Type, int length, boolean isVertical,int row, int col){
        if (isVertical)
            for (int currentRow = row; currentRow < row + length; currentRow++){
                board[currentRow][col] = Type;
            }
        else {
            for (int currentCol = col; currentCol < col + length; currentCol++){
                board[row][currentCol] = Type;
            }
        }
    }



    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Board game = new Board();
        System.out.println(game);

        // game.fogofWar("S", 3, true, 2, 4);
        // System.out.print(game);
        for(int shipNum = 0; shipNum<5; shipNum++){
            // have your prompts and scanner for type, length, vertical/hroizontal, row, col
            System.out.print("Enter ship letter: ");
            String type = in.nextLine();
            System.out.print("Enter ship length: ");
            int length = in.nextInt();
            System.out.print("Enter true if vertical or false if horizontal: ");
            Boolean isVertical = in.nextBoolean();
            System.out.print("Enter ship starting row: ");
            int row = in.nextInt();
            System.out.print("Enter ship starting col: ");
            int col = in.nextInt();
            in.nextLine();
            game.fogofWar(type, length, isVertical, row, col);
            // call fogofWar(type, length, isVertical, row, col);
            System.out.print(game);
        }


    }
    
}

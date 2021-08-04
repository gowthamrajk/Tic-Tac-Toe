package com.gowthamrajk.tictactoe;

import java.util.*;

public class Main 
{
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    static String playerOption = "";
    static char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '}};
                                 
    public static void main(String[] args) 
    {
    	Scanner scan = new Scanner(System.in);
        System.out.println("\n Welcome to Gowthamraj K's Tic-Tac-Toe 👋\n Lets have some fun right now !!!\n");
        System.out.println(" This is your default game board 👇\n");
        printGameBoard(gameBoard);
        System.out.println();
        System.out.println(" Choose Your Player Option\n 1) Player-X \n 2) Player-O\n");
        int option = scan.nextInt();
        if(option == 1)
        	playerOption = "X";
        else
        	playerOption = "O";
        
        while(true) 
        {
        	if(playerOption.equalsIgnoreCase("X"))
                System.out.println(" Enter your X-placement as an integer from 1-9: \n");
        	else if(playerOption.equalsIgnoreCase("O"))
        		System.out.println(" Enter your O-placement as an integer from 1-9: \n");
        	
            int playerPos = scan.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
                System.out.println(" Position taken! Enter another integer from 1-9: \n");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, "player", playerOption);
            String result = checkWinner();

            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(cpuPositions.contains(cpuPos) || playerPositions.contains(cpuPos)) {
                System.out.println(" Position taken! Enter another integer 1-9: \n");
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu", playerOption);

            printGameBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) 
    {
        for(char[] row : gameBoard) 
        {
            for(char c : row) 
            {
                System.out.print(" "+c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user, String playerOption) 
    {

        char symbol = ' ';
        if(user.equals("player")) 
        {
        	if(playerOption.equalsIgnoreCase("X"))
                symbol  = 'X';
        	else if(playerOption.equalsIgnoreCase("O"))
        		symbol = 'O';
            playerPositions.add(pos);
        }
        else 
        {
        	if(playerOption.equalsIgnoreCase("X"))
                symbol  = 'O';
        	else if(playerOption.equalsIgnoreCase("O"))
        		symbol = 'X';
            cpuPositions.add(pos);
        }

        switch(pos) 
        {
            case 1:
                gameBoard[0][0] = symbol;
                break;

            case 2:
                gameBoard[0][2] = symbol;
                break;

            case 3:
                gameBoard[0][4] = symbol;
                break;

            case 4:
                gameBoard[2][0] = symbol;
                break;

            case 5:
                gameBoard[2][2] = symbol;
                break;

            case 6:
                gameBoard[2][4] = symbol;
                break;

            case 7:
                gameBoard[4][0] = symbol;
                break;

            case 8:
                gameBoard[4][2] = symbol;
                break;

            case 9:
                gameBoard[4][4] = symbol;
                break;

            default:
                break;
        }
    }

    public static String checkWinner() 
    {
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List lastColumn = Arrays.asList(3, 6, 9);

        List diagonalRight = Arrays.asList(1, 5, 9);
        List diagonalLeft = Arrays.asList(3, 5, 7);

        List<List> winningPositions = new ArrayList<List>();
        winningPositions.add(topRow);
        winningPositions.add(middleRow);
        winningPositions.add(bottomRow);

        winningPositions.add(leftColumn);
        winningPositions.add(middleColumn);
        winningPositions.add(lastColumn);

        winningPositions.add(diagonalRight);
        winningPositions.add(diagonalLeft);

        for(List l : winningPositions) 
        {
            if(playerPositions.containsAll(l))
            {
                printGameBoard(gameBoard);
                System.out.println();
                return " 😃 Congratulations Player-"+playerOption+", you won Tic-Tac-Toe !!!";
            }
            else if(cpuPositions.containsAll(l)) 
            {
                return " Sorry you lost 👎 Computer Wins the Game!";
            }
            else if(playerPositions.size() + cpuPositions.size() == 9) 
            {
                return " Its a Tie Game !";
            }
        }
        return "";
    }
}

// This is a Tic Tac Toe game against a bot.

import java.util.Random;
import java.util.Scanner;

public class TTT {

    private static final Scanner input = new Scanner(System.in);
    public static Board board = new Board();
    private static int realPlayer;
    private static int cpu;

    public static void main(String[] args) {

        playGame();
    }

    private static int getFirstPlayer() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.print("Choose X or O: ");
        String choice = input.next();
        if(choice.equalsIgnoreCase("x")) {
            realPlayer = 1;
            cpu = 0;
        } else if(choice.equalsIgnoreCase("o")) {
            realPlayer = 0;
            cpu = 1;
        } else {
            throw new IllegalArgumentException();
        }
        System.out.println("I'll flip a coin to see who goes first.");
        System.out.print("Choose heads (h) or tails (t): ");
        String coinSide = input.next();
        Random r = new Random();
        int flip = r.nextInt(2);
        if((flip == 0 && coinSide.equalsIgnoreCase("t")) || (flip == 1 && coinSide.equalsIgnoreCase("h"))) {
            System.out.println("You won the coin toss, you go first!");
            return realPlayer;
        } else {
            System.out.println("I won the coin toss, so I'll go first!");
            return cpu;
        }
    }

    private static void playGame() {
        int firstPlayer = getFirstPlayer();
        board.printCurrentBoard();
        System.out.println();
        while (!board.isFull) {
            if(firstPlayer == realPlayer) {
                playerTurn();
                if (board.checkWinner(realPlayer)) {
                    System.out.println("You won!");
                    return;
                }
            } else {
                cpuTurn();
                if (board.checkWinner(cpu)) {
                    System.out.println("Cpu won!");
                    return;
                }
            }
            firstPlayer = Math.abs(firstPlayer - 1);
        }
        System.out.println("Cat's Game!");
    }

    private static void playerTurn() {
        System.out.print("Enter a row (1 - 3) and a column (1 - 3): ");
        int row = input.nextInt();
        int column = input.nextInt();
        System.out.println();
        while (board.board[row - 1][column - 1] != board.EMPTY) {
            System.out.print("Enter a row (1 - 3) and a column (1 - 3): ");
            row = input.nextInt();
            column = input.nextInt();
            System.out.println();
        }
        Move m = new Move(row - 1, column - 1);
        board.applyMove(m, realPlayer);
        board.printCurrentBoard();
        System.out.println();
    }

    private static void cpuTurn() {
        System.out.println("CPU turn");
        Move cpuMove = GetMove.minimax(board, cpu);
        board.applyMove(cpuMove, cpu);
        board.printCurrentBoard();
        System.out.println();
    }
}

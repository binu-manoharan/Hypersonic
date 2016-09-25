package template;

import org.binu.hypersonic.BoardHelper;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        int myId = in.nextInt();
        in.nextLine();

        BoardHelper boardHelper = new BoardHelper();
        // game loop
        while (true) {
            String[] boardString = new String[height];
            for (int i = 0; i < height; i++) {
                boardString[i] = in.nextLine();
            }

            int entities = in.nextInt();
            for (int i = 0; i < entities; i++) {
                int entityType = in.nextInt();
                int owner = in.nextInt();
                int x = in.nextInt();
                int y = in.nextInt();
                int param1 = in.nextInt();
                int param2 = in.nextInt();
            }
            in.nextLine();

            //data conversions
            final char[][] board = boardHelper.convertBoard(boardString);

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println("BOMB 6 5");
        }
    }
}

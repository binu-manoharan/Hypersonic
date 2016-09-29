package template;

import org.binu.hypersonic.board.Board;
import org.binu.hypersonic.board.BoardHelper;
import org.binu.hypersonic.Coordinates;
import org.binu.hypersonic.MadBomber;
import org.binu.hypersonic.entity.*;
import org.binu.hypersonic.move.BomberMove;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

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
        EntityHelper entityHelper = new EntityHelper();
        // game loop
        while (true) {
            String[] boardString = new String[height];
            for (int i = 0; i < height; i++) {
                boardString[i] = in.nextLine();
            }

            int entities = in.nextInt();
            final ArrayList<Bomber> bombers = new ArrayList<>();
            final ArrayList<Bomb> bombs = new ArrayList<>();
            final ArrayList<Item> items = new ArrayList<>();
            Bomber myBomber = null;
            for (int i = 0; i < entities; i++) {
                int entityType = in.nextInt();
                int owner = in.nextInt();
                int x = in.nextInt();
                int y = in.nextInt();
                int param1 = in.nextInt();
                int param2 = in.nextInt();

                final Entity entity = entityHelper.createEntity(entityType, owner, new Coordinates(x, y), param1, param2);
                if (entity.getEntityType() == 0) {
                    //My bomber has the same id as myId
                    if (owner == myId) {
                        myBomber = (Bomber) entity;
                    }
                    bombers.add((Bomber) entity);
                } else if (entity.getEntityType() == 1) {
                    bombs.add((Bomb) entity);
                } else if (entity.getEntityType() == 2) {
                    items.add((Item) entity);
                } else {
                    assert false : "Uh oh, we have a Unknown entity here!";
                }
            }
            in.nextLine();
            assert myBomber != null;

            //data conversions
            final Board board = boardHelper.convertBoard(boardString);
            board.addBombs(bombs);
            board.addItems(items);
            MadBomber madBomber = new MadBomber(myBomber, board, bombers, bombs, items);
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            final BomberMove move = madBomber.calculateNextMove();
            System.out.println(move.render());
        }
    }
}

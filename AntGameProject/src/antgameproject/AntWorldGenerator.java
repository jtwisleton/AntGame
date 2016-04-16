package antgameproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author JTwisleton
 */
public class AntWorldGenerator {

    static private int redAnthillX;
    static private int redAnthillY;
    static private int blackAnthillX;
    static private int blackAnthillY;

    public static void main(String[] args) {
        Board b = generateWorld(5);
        b.printBoardToASCII();
    }

    /*
     Takes an int that determines average rock size, generates tournament ready 
     world, saves to a file and returns Board.
     */
    public static Board generateWorld(int avgRockSize) {
        BoardTile[][] b = placeBordersAndGrass();
        b = placeAnthills(b);
        
        return new Board(b,"testworld");
    }

    public static BoardTile[][] placeBordersAndGrass() {
        BoardTile[][] board = new BoardTile[150][150];

        /*
         Fill the outside rows/columns with rocks
         */
        for (int i = 0; i < 150; i++) {
            board[i][0] = new BoardTile(0, Terrain.ROCK);
            board[i][149] = new BoardTile(0, Terrain.ROCK);
            board[0][i] = new BoardTile(0, Terrain.ROCK);
            board[149][i] = new BoardTile(0, Terrain.ROCK);
        }

        /*
         Fill the entire interior with grass.        
         */
        for (int i = 1; i < 149; i++) {
            for (int j = 1; j < 149; j++) {
                board[i][j] = new BoardTile(0, Terrain.GRASS);
            }
        }

        return board;
    }

    public static BoardTile[][] placeAnthills(BoardTile[][] emptyBoard) {
        /*
         Generate random numbers for the centre of the ant hills, keep generating
         until anthills don't overlap.
         */
        Random r = new Random();
        Boolean finished = false;
        int redBaseX = 0;
        int redBaseY = 0;
        int blackBaseX = 0;
        int blackBaseY = 0;
        while (!finished) {
            redBaseX = r.nextInt(129) + 10;
            redBaseY = r.nextInt(129) + 10;
            blackBaseX = r.nextInt(129) + 10;
            blackBaseY = r.nextInt(129) + 10;
            if (Math.abs(redBaseX - blackBaseX) > 13 && Math.abs(redBaseY - blackBaseY) > 13) {
                finished = true;
            }
        }

        /*
         Derive whether to offset odd or even rows depending on anthill Y positions.
         */
        int redEvenOffset = 0;
        int redOddOffset = 0;
        int blackEvenOffset = 0;
        int blackOddOffset = 0;

        if (redBaseY % 2 == 0) {
            redEvenOffset = 1;
        } else {
            redOddOffset = 1;
        }

        if (blackBaseY % 2 == 0) {
            blackEvenOffset = 1;
        } else {
            blackOddOffset = 1;
        }

        /*
         Build left/right side of anthills.
         */
        int x = 0;
        for (int i = -6; i <= 0; i++) {
            for (int j = i; j < 3; j++) {
                if (i % 2 == 0) {
                    emptyBoard[redBaseY - x][redBaseX - j + redEvenOffset] = new BoardTile(0, Terrain.REDBASE);
                    emptyBoard[redBaseY + x][redBaseX - j + redEvenOffset] = new BoardTile(0, Terrain.REDBASE);
                    emptyBoard[redBaseY - x][redBaseX + j + redEvenOffset - 6] = new BoardTile(0, Terrain.REDBASE);
                    emptyBoard[redBaseY + x][redBaseX + j + redEvenOffset - 6] = new BoardTile(0, Terrain.REDBASE);

                    emptyBoard[blackBaseY - x][blackBaseX - j + blackEvenOffset] = new BoardTile(0, Terrain.BLACKBASE);
                    emptyBoard[blackBaseY + x][blackBaseX - j + blackEvenOffset] = new BoardTile(0, Terrain.BLACKBASE);
                    emptyBoard[blackBaseY - x][blackBaseX + j + blackEvenOffset - 6] = new BoardTile(0, Terrain.BLACKBASE);
                    emptyBoard[blackBaseY + x][blackBaseX + j + blackEvenOffset - 6] = new BoardTile(0, Terrain.BLACKBASE);
                } else {
                    emptyBoard[redBaseY - x][redBaseX - j + redOddOffset] = new BoardTile(0, Terrain.REDBASE);
                    emptyBoard[redBaseY + x][redBaseX - j + redOddOffset] = new BoardTile(0, Terrain.REDBASE);
                    emptyBoard[redBaseY - x][redBaseX + j + redOddOffset - 6] = new BoardTile(0, Terrain.REDBASE);
                    emptyBoard[redBaseY + x][redBaseX + j + redOddOffset - 6] = new BoardTile(0, Terrain.REDBASE);

                    emptyBoard[blackBaseY - x][blackBaseX - j + blackOddOffset] = new BoardTile(0, Terrain.BLACKBASE);
                    emptyBoard[blackBaseY + x][blackBaseX - j + blackOddOffset] = new BoardTile(0, Terrain.BLACKBASE);
                    emptyBoard[blackBaseY - x][blackBaseX + j + blackOddOffset - 6] = new BoardTile(0, Terrain.BLACKBASE);
                    emptyBoard[blackBaseY + x][blackBaseX + j + blackOddOffset - 6] = new BoardTile(0, Terrain.BLACKBASE);
                }
            }
            x--;
        }

        /*
         Build centre column of anthills
         */
        for (int i = -6; i < 7; i++) {
            emptyBoard[redBaseY - i][redBaseX - 3] = new BoardTile(0, Terrain.REDBASE);
            emptyBoard[redBaseY - i][redBaseX - 2] = new BoardTile(0, Terrain.REDBASE);
            emptyBoard[blackBaseY - i][blackBaseX - 3] = new BoardTile(0, Terrain.BLACKBASE);
            emptyBoard[blackBaseY - i][blackBaseX - 2] = new BoardTile(0, Terrain.BLACKBASE);

        }

        redAnthillX = redBaseX;
        redAnthillY = redBaseY;
        blackAnthillX = blackBaseX;
        blackAnthillY = blackBaseY;
        BoardTile[][] boardWithAnthills = emptyBoard;
        return boardWithAnthills;
    }

    public static BoardTile[][] placeFood(BoardTile[][] boardWithAnthills) {

        List<Integer> foodXcoords = new ArrayList<>();
        List<Integer> foodYcoords = new ArrayList<>();

        /*
         
         */
        /*
         Iterate 11 times to create 11 food blobs.
         */
        Random r = new Random();
        for (int i = 0; i < 12; i++) {

            /*
             Pick random X and Y coordinates for food pile to create. Keep
             selecting new random X and Y coordinates until certain criteria
             are reached.
             */
            Boolean finished = false;
            while (!finished) {
                int currentFoodX = r.nextInt(129) + 10;
                int currentFoodY = r.nextInt(129) + 10;

                /*
                 Check that the selected food pile coordinates wont conflict
                 with the anthills.
                 */
                if (Math.abs(redAnthillX - currentFoodX) > 15
                        && Math.abs(redAnthillY - currentFoodY) > 15
                        && Math.abs(blackAnthillX - currentFoodX) > 15
                        & Math.abs(blackAnthillY - currentFoodY) > 15) {
                    
                    /*
                     Check that the current food pile wont conflict with the previous
                     food piles.
                     */
                    boolean foodConflicts = false;

                    for (int foodXCoord : foodXcoords) {
                        if (Math.abs(foodXCoord - currentFoodX) < 6) {
                            foodConflicts = true;
                        }
                    }

//                    for (int foodYCoord : foodYcoords) {
//                        if (Math.abs(foodYCoord - currentFoodY) < 6) {
//                            foodConflicts = true;
//                        }
//                    }

                    /*
                     Add food pile coords to arraylist, add food pile.
                     */
                    if (!foodConflicts) {
                        foodXcoords.add(currentFoodX);
                        foodYcoords.add(currentFoodY);
                        finished = true;
                    }
                }
            }
        }

        return null;

    }

}

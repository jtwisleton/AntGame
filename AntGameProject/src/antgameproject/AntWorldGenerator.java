package antgameproject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Loads in a world from a file, creating a new Board from it.
 *
 * @author Team18
 */
public class AntWorldGenerator {

    private int blackAnthillX;
    private int blackAnthillY;
    private int redAnthillX;
    private int redAnthillY;
    private final int avgRockSize;

    public static void main(String[] args) {
        AntWorldGenerator awg = new AntWorldGenerator(10);
    }

    /*
     avgRockSize is the average size of generated rocks.
     */
    public AntWorldGenerator(int avgRockSize) {
        this.avgRockSize = avgRockSize;
    }

    /*
     Runs the placeBordersAndGrass, placeAnthills, placeFood, placeRocks,
     creareGaps methods - writes generated world to file wit toFile method, and
     returns new Board.    
     */
    public Board generateWorld() throws IOException {
        BoardTile[][] b = placeBordersAndGrass();
        b = placeAnthills(b);
        b = placeFood(b);
        b = placeRocks(b);
        b = createGaps(b);
        int time = (int) System.currentTimeMillis();
        String filename = "generatedWorlds/generatedWorld" + time + ".world";
        toFile(b, filename);
        return new Board(b, filename);

    }

    /**
     * Creates a board with borders and grass.
     *
     * @return board with borders and grass.
     */
    public BoardTile[][] placeBordersAndGrass() {
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

    /**
     * places Anthills on an empty BoardTile array.
     * 
     * @param emptyBoard empty BoardTile array (with grass and borders).
     * @return BoardTile array with anthills. 
     */
    public BoardTile[][] placeAnthills(BoardTile[][] emptyBoard) {

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
            redBaseX = r.nextInt(129) + 12;
            redBaseY = r.nextInt(129) + 12;
            blackBaseX = r.nextInt(129) + 12;
            blackBaseY = r.nextInt(129) + 12;
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

        /*
         Set anthill location class variables, return Board.
         */
        redAnthillX = redBaseX;
        redAnthillY = redBaseY;
        blackAnthillX = blackBaseX;
        blackAnthillY = blackBaseY;
        BoardTile[][] boardWithAnthills = emptyBoard;
        return boardWithAnthills;
    }

    /**
     * Takes a BoardTile array with anthills, place Food blocks onto it
     * @param anthills a BoardTile array with borders, grass and anthills.
     * @return a BoardTile array with borders, grass anthills and food.
     */
    public BoardTile[][] placeFood(BoardTile[][] anthills) {

        /*
         Create ArrayLists to put x and y coordinates of food piles into.
         */
        ArrayList<Integer> xcoords = new ArrayList<>();
        ArrayList<Integer> ycoords = new ArrayList<>();
        Random r = new Random();

        /*
         Iterate 11 times to make 11 food blobs top left coordinates.
         */
        for (int i = 0; i < 12; i++) {

            /*
             Iterate until valid x and y coordinates for current food are found.
             */
            boolean finished = false;
            while (!finished) {

                /*                
                 Generate a random number for top left of food blob (as food 
                 blobs are 5x5, top left of blob must be between 2 and 144 so as 
                 to be within the grid).
                 */
                int currentxcoord = r.nextInt(140) + 3;
                int currentycoord = r.nextInt(140) + 3;

                /*
                 Check the created food coordinates don't conflict with the anthills.
                 */
                if ((Math.abs(currentxcoord - redAnthillX) > 11)
                        && (Math.abs(currentxcoord - blackAnthillX) > 11)) {
                    if ((Math.abs(currentycoord - redAnthillY) > 11)
                            && (Math.abs(currentycoord - blackAnthillY) > 11)) {

                        /*
                         Check the current x coordinate doesn't conflict with
                         any of the food piles in the xcoords ArrayList.
                         */
                        boolean conflicts = false;
                        for (int xcoord : xcoords) {
                            if (Math.abs(currentxcoord - xcoord) < 6) {
                                conflicts = true;
                            }
                        }

                        /*
                         Check the current y coordinate doesn't conflict with
                         any of the food piles in the ycoords ArrayList.
                         */
                        for (int ycoord : ycoords) {
                            if (Math.abs(currentycoord - ycoord) < 6) {
                                conflicts = true;
                            }
                        }

                        /*
                         If there are no conflicts between established x and y 
                         coordinates and those generated in this loop iteration,
                         add these coordinates to the established list and exit
                         the loop.
                         */
                        if (conflicts == false) {
                            finished = true;
                            xcoords.add(currentxcoord);
                            ycoords.add(currentycoord);
                        }
                    }
                }
            }
        }

        /*
         now that x and y coordinates have been created for all 11 food piles,
         place the food onto the world.
         */
        for (int i = 0; i < 12; i++) {
            Boolean firstCoordinateIsEven = false;
            if (ycoords.get(i) % 2 == 0) {
                firstCoordinateIsEven = true;
            }
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (firstCoordinateIsEven) {
                        if ((ycoords.get(i) + j) % 2 == 0) {
                            anthills[(ycoords.get(i) + j)][xcoords.get(i) + k + 1] = new BoardTile(5, Terrain.GRASS);
                        } else {
                            anthills[(ycoords.get(i) + j)][xcoords.get(i) + k] = new BoardTile(5, Terrain.GRASS);
                        }
                    } else {
                        if ((ycoords.get(i) + j) % 2 != 0) {
                            anthills[(ycoords.get(i) + j)][xcoords.get(i) + k - 1] = new BoardTile(5, Terrain.GRASS);
                        } else {
                            anthills[(ycoords.get(i) + j)][xcoords.get(i) + k] = new BoardTile(5, Terrain.GRASS);
                        }
                    }
                }
            }
        }

        return anthills;
    }

    public BoardTile[][] placeRocksOld(BoardTile[][] anthillsFood) {

        ArrayList<Pos> rockPositions = new ArrayList<>();
        Random r = new Random();

        /*
         Iterate 14 times to create 14 rocks.
         */
        ArrayList<Pos> currentRockPositions = new ArrayList<>();
        for (int i = 0; i < 15; i++) {

            /*
             Generate random numbers within world range for the current rocks
             initial x and y coordinates - check that it isn't adjacent to any
             other rocks or on a food tile, keep generating until this is the case.
             */
            int currentRockX = 0;
            int currentRockY = 0;
            boolean finished = false;
            while (!finished) {
                currentRockX = r.nextInt(129) + 2;
                currentRockY = r.nextInt(129) + 2;
                if (anthillsFood[currentRockY][currentRockX].getCellTerrain() == Terrain.GRASS
                        && anthillsFood[currentRockY][currentRockX].getFoodInTile() == 0) {
                    if (!rockPositions.contains(new Pos(currentRockX, currentRockY))
                            && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY))
                            && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY))
                            && !rockPositions.contains(new Pos(currentRockX, currentRockY + 1))
                            && !rockPositions.contains(new Pos(currentRockX, currentRockY - 1))
                            && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY - 1))
                            && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY + 1))
                            && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY - 1))
                            && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY + 1))) {

                        /*
                         Add generated initial x and y coordinates for current rock
                         to currentRockPositions.
                         */
                        currentRockPositions.add(new Pos(currentRockX, currentRockY));
                        finished = true;
                    }
                }
            }

            /*
             Iterate from 0 to avgRockSize.
             */
            for (int j = 0; j < avgRockSize; j++) {

                /*
                 Generate a random number from 0 to 7 to decide which direction
                 to place next rock piece.                    
                 */
                int direction = r.nextInt(8);

                int savedCurrentRockX = currentRockX;
                int savedCurrentRockY = currentRockY;
                switch (direction) {
                    case 0:
                        currentRockX++;
                    case 1:
                        currentRockX--;
                    case 2:
                        currentRockY++;
                    case 3:
                        currentRockY--;
                    case 4:
                        currentRockX++;
                        currentRockY++;
                    case 5:
                        currentRockX--;
                        currentRockY--;
                    case 6:
                        currentRockX++;
                        currentRockY--;
                    case 7:
                        currentRockX--;
                        currentRockY++;
                }

                /*
                 If the random next piece of the current rock is not adjacent
                 to any existing rocks, or outside of the world range, or on a 
                 base or food tile, add it to the currentRockPositions ArrayList. 
                 If it is, revert back to the previous currentRockX and 
                 currentRockY and reduce increment (try again).
                 */
                if (!rockPositions.contains(new Pos(currentRockX, currentRockY))
                        && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY))
                        && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY))
                        && !rockPositions.contains(new Pos(currentRockX, currentRockY + 1))
                        && !rockPositions.contains(new Pos(currentRockX, currentRockY - 1))
                        && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY - 1))
                        && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY + 1))
                        && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY - 1))
                        && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY + 1))
                        && (currentRockX < 150) && (currentRockX > 0) && (currentRockY < 150) && (currentRockY > 0)
                        && anthillsFood[currentRockX][currentRockY].getCellTerrain() != Terrain.BLACKBASE
                        && anthillsFood[currentRockX][currentRockY].getCellTerrain() != Terrain.REDBASE
                        && anthillsFood[currentRockX][currentRockY].getFoodInTile() == 0) {
                    currentRockPositions.add(new Pos(currentRockX, currentRockY));
                } else {
                    j--;
                    currentRockX = savedCurrentRockX;
                    currentRockY = savedCurrentRockY;
                }
            }

            /*
             At the end of the loop, add all current rock positions to overall
             rock positions ArrayList.
             */
            rockPositions.addAll(currentRockPositions);
            currentRockPositions.clear();
        }

        /*
         Iterate over rockPosition ArrayList, adding rocks to world.
         */
        for (Pos rockPosition : rockPositions) {
            int currentPosX = rockPosition.getPosX();
            int currentPosY = rockPosition.getPosY();
            anthillsFood[currentPosX][currentPosY] = new BoardTile(0, Terrain.ROCK);
        }

        return anthillsFood;
    }

    public BoardTile[][] placeRocks(BoardTile[][] anthillsFood) {

        ArrayList<Pos> rockPositions = new ArrayList<>();
        Random r = new Random();

        /*
         Iterate 14 times to create 14 rocks.
         */
        ArrayList<Pos> currentRockPositions = new ArrayList<>();
        for (int i = 0; i < 14; i++) {

            /*
             Generate random numbers within world range for the current rocks
             initial x and y coordinates - check that it isn't adjacent to any
             other rocks or on a food tile, keep generating until this is the case.
             */
            int currentRockX = 0;
            int currentRockY = 0;
            boolean finished = false;
            while (!finished) {
                currentRockX = r.nextInt(147) + 2;
                currentRockY = r.nextInt(147) + 2;
                if (anthillsFood[currentRockY][currentRockX].getCellTerrain() == Terrain.GRASS
                        && anthillsFood[currentRockY][currentRockX].getFoodInTile() == 0) {
                    if (!rockPositions.contains(new Pos(currentRockX, currentRockY))
                            && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY))
                            && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY))
                            && !rockPositions.contains(new Pos(currentRockX, currentRockY + 1))
                            && !rockPositions.contains(new Pos(currentRockX, currentRockY - 1))
                            && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY - 1))
                            && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY + 1))
                            && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY - 1))
                            && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY + 1))) {

                        /*
                         Add generated initial x and y coordinates for current rock
                         to currentRockPositions.
                         */
                        currentRockPositions.add(new Pos(currentRockX, currentRockY));
                        finished = true;
                    }
                }
            }

            /*
             Iterate from 0 to avgRockSize.
             */
            for (int j = 0; j < avgRockSize; j++) {

                /*
                 Generate a random number from 0 to 3 to decide which direction
                 to place next rock piece.                    
                 */
                int direction = r.nextInt(4);

                int savedCurrentRockX = currentRockX;
                int savedCurrentRockY = currentRockY;
                switch (direction) {
                    case 0:
                        currentRockX++;
                    case 1:
                        currentRockX--;
                    case 2:
                        currentRockY++;
                    case 3:
                        currentRockY--;
                }

                /*
                 If the random next piece of the current rock is not adjacent
                 to any existing rocks, or outside of the world range, or on a 
                 base or food tile, add it to the currentRockPositions ArrayList. 
                 If it is, revert back to the previous currentRockX and 
                 currentRockY and reduce increment (try again).
                 */
                if (!rockPositions.contains(new Pos(currentRockX, currentRockY))
                        && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY))
                        && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY))
                        && !rockPositions.contains(new Pos(currentRockX, currentRockY + 1))
                        && !rockPositions.contains(new Pos(currentRockX, currentRockY - 1))
                        && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY - 1))
                        && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY + 1))
                        && !rockPositions.contains(new Pos(currentRockX + 1, currentRockY - 1))
                        && !rockPositions.contains(new Pos(currentRockX - 1, currentRockY + 1))
                        && (currentRockX < 150) && (currentRockX > 0) && (currentRockY < 150) && (currentRockY > 0)
                        && anthillsFood[currentRockX][currentRockY].getCellTerrain() != Terrain.BLACKBASE
                        && anthillsFood[currentRockX][currentRockY].getCellTerrain() != Terrain.REDBASE
                        && anthillsFood[currentRockX][currentRockY].getFoodInTile() == 0) {
                    currentRockPositions.add(new Pos(currentRockX, currentRockY));
                } else {
                    currentRockX = savedCurrentRockX;
                    currentRockY = savedCurrentRockY;
                }
            }

            /*
             At the end of the loop, add all current rock positions to overall
             rock positions ArrayList.
             */
            rockPositions.addAll(currentRockPositions);
        }

        /*
         Iterate over rockPosition ArrayList, adding rocks to world.
         */
        for (Pos rockPosition : rockPositions) {
            int currentPosX = rockPosition.getPosX();
            int currentPosY = rockPosition.getPosY();
            anthillsFood[currentPosX][currentPosY] = new BoardTile(0, Terrain.ROCK);
        }

        return anthillsFood;
    }

    public BoardTile[][] createGaps(BoardTile[][] anthillsFoodRocks) {
        for (int i = 1; i < anthillsFoodRocks.length; i++) {
            for (int j = 1; j < anthillsFoodRocks[i].length; j++) {

                /*
                 If a piece of terrain adjacent to a base is ROCK, make it GRASS.
                 */
                if (anthillsFoodRocks[i][j].getCellTerrain() == Terrain.BLACKBASE
                        || anthillsFoodRocks[i][j].getCellTerrain() == Terrain.REDBASE) {

                    if (anthillsFoodRocks[i][j + 1].getCellTerrain() == Terrain.ROCK) {
                        anthillsFoodRocks[i][j + 1] = new BoardTile(0, Terrain.GRASS);
                    }

                    if (anthillsFoodRocks[i][j - 1].getCellTerrain() == Terrain.ROCK) {
                        anthillsFoodRocks[i][j - 1] = new BoardTile(0, Terrain.GRASS);
                    }

                    if (anthillsFoodRocks[i + 1][j].getCellTerrain() == Terrain.ROCK) {
                        anthillsFoodRocks[i + 1][j] = new BoardTile(0, Terrain.GRASS);
                    }

                    if (anthillsFoodRocks[i - 1][j].getCellTerrain() == Terrain.ROCK) {
                        anthillsFoodRocks[i - 1][j] = new BoardTile(0, Terrain.GRASS);
                    }

                    if (anthillsFoodRocks[i - 1][j - 1].getCellTerrain() == Terrain.ROCK) {
                        anthillsFoodRocks[i - 1][j - 1] = new BoardTile(0, Terrain.GRASS);
                    }

                    if (anthillsFoodRocks[i + 1][j + 1].getCellTerrain() == Terrain.ROCK) {
                        anthillsFoodRocks[i + 1][j + 1] = new BoardTile(0, Terrain.GRASS);
                    }

                    if (anthillsFoodRocks[i + 1][j - 1].getCellTerrain() == Terrain.ROCK) {
                        anthillsFoodRocks[i + 1][j - 1] = new BoardTile(0, Terrain.GRASS);
                    }

                    if (anthillsFoodRocks[i - 1][j + 1].getCellTerrain() == Terrain.ROCK) {
                        anthillsFoodRocks[i - 1][j + 1] = new BoardTile(0, Terrain.GRASS);
                    }

                }
            }
        }

        return anthillsFoodRocks;
    }

    public void toFile(BoardTile[][] b, String filename) throws FileNotFoundException, IOException {
        FileWriter fw = new FileWriter(filename);
        PrintWriter writer = new PrintWriter(fw);
        writer.println(b.length);
        writer.println(b[0].length);
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (b[i][j].getCellTerrain() == Terrain.ROCK) {
                    writer.print("#");
                } else if (b[i][j].getCellTerrain() == Terrain.GRASS) {
                    writer.print(".");
                } else if (b[i][j].getCellTerrain() == Terrain.BLACKBASE) {
                    writer.print("-");
                } else if (b[i][j].getCellTerrain() == Terrain.REDBASE) {
                    writer.print("+");
                }
            }
            writer.print('\n');
        }

        writer.close();
        fw.close();

    }
}

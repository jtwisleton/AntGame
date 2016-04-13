package antgameproject;

import java.util.Random;

/**
 *
 * @author JTwisleton
 */
public class AntWorldGenerator {

    public static void main(String[] args) {
        Board b = generateWorld(5);
        b.printBoardToASCII();
    }

    /*
     Takes an int that determines average rock size, generates tournament ready 
     world, saves to a file and returns Board.
     */
    public static Board generateWorld(int avgRockSize) {
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
                    board[redBaseY - x][redBaseX - j + redEvenOffset] = new BoardTile(0, Terrain.REDBASE);
                    board[redBaseY + x][redBaseX - j + redEvenOffset] = new BoardTile(0, Terrain.REDBASE);
                    board[redBaseY - x][redBaseX + j + redEvenOffset - 6] = new BoardTile(0, Terrain.REDBASE);
                    board[redBaseY + x][redBaseX + j + redEvenOffset - 6] = new BoardTile(0, Terrain.REDBASE);

                    board[blackBaseY - x][blackBaseX - j + blackEvenOffset] = new BoardTile(0, Terrain.BLACKBASE);
                    board[blackBaseY + x][blackBaseX - j + blackEvenOffset] = new BoardTile(0, Terrain.BLACKBASE);
                    board[blackBaseY - x][blackBaseX + j + blackEvenOffset - 6] = new BoardTile(0, Terrain.BLACKBASE);
                    board[blackBaseY + x][blackBaseX + j + blackEvenOffset - 6] = new BoardTile(0, Terrain.BLACKBASE);
                } else {
                    board[redBaseY - x][redBaseX - j + redOddOffset] = new BoardTile(0, Terrain.REDBASE);
                    board[redBaseY + x][redBaseX - j + redOddOffset] = new BoardTile(0, Terrain.REDBASE);
                    board[redBaseY - x][redBaseX + j + redOddOffset - 6] = new BoardTile(0, Terrain.REDBASE);
                    board[redBaseY + x][redBaseX + j + redOddOffset - 6] = new BoardTile(0, Terrain.REDBASE);

                    board[blackBaseY - x][blackBaseX - j + blackOddOffset] = new BoardTile(0, Terrain.BLACKBASE);
                    board[blackBaseY + x][blackBaseX - j + blackOddOffset] = new BoardTile(0, Terrain.BLACKBASE);
                    board[blackBaseY - x][blackBaseX + j + blackOddOffset - 6] = new BoardTile(0, Terrain.BLACKBASE);
                    board[blackBaseY + x][blackBaseX + j + blackOddOffset - 6] = new BoardTile(0, Terrain.BLACKBASE);
                }
            }
            x--;
        }

        /*
         Build centre column of anthills
         */
        for (int i = -6; i < 7; i++) {
            board[redBaseY - i][redBaseX - 3] = new BoardTile(0, Terrain.REDBASE);
            board[redBaseY - i][redBaseX - 2] = new BoardTile(0, Terrain.REDBASE);
            board[blackBaseY - i][blackBaseX - 3] = new BoardTile(0, Terrain.BLACKBASE);
            board[blackBaseY - i][blackBaseX - 2] = new BoardTile(0, Terrain.BLACKBASE);
            
        }

        return new Board(board);
    }
}

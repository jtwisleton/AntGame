package instructions;

import antgameproject.Pos;

/**
 * DirectionalInstruction is an abstract class that has methods shared by
 * instructions that involve directions.
 *
 * @author Team18
 */
public abstract class DirectionalInstruction {

    /**
     * Returns the position of the adjacent cell in an offset hexagonal board in
     * the given direction.
     *
     * @param cellPosition the cell to find the adjacent cell to.
     * @param adjacentDirection the direction to find the adjacent cell.
     * @return returns the cell adjacent to the given cell in the given
     * direction.
     */
    protected Pos getAdjacentCell(Pos cellPosition, int adjacentDirection) {
        if (adjacentDirection == 0) {
            return new Pos(cellPosition.getPosX() + 1, cellPosition.getPosY());
        } else if (adjacentDirection == 3) {
            return new Pos(cellPosition.getPosX() - 1, cellPosition.getPosY());
        } else if (adjacentDirection == 1) {
            if (cellPosition.getPosY() % 2 == 0) {
                return new Pos(cellPosition.getPosX(), cellPosition.getPosY() + 1);
            } else {
                return new Pos(cellPosition.getPosX() + 1, cellPosition.getPosY() + 1);
            }
        } else if (adjacentDirection == 2) {
            if (cellPosition.getPosY() % 2 == 0) {
                return new Pos(cellPosition.getPosX() - 1, cellPosition.getPosY() + 1);
            } else {
                return new Pos(cellPosition.getPosX(), cellPosition.getPosY() + 1);
            }
        } else if (adjacentDirection == 4) {
            if (cellPosition.getPosY() % 2 == 0) {
                return new Pos(cellPosition.getPosX() - 1, cellPosition.getPosY() - 1);
            } else {
                return new Pos(cellPosition.getPosX(), cellPosition.getPosY() - 1);
            }
        } else {
            if (cellPosition.getPosY() % 2 == 0) {
                return new Pos(cellPosition.getPosX(), cellPosition.getPosY() - 1);
            } else {
                return new Pos(cellPosition.getPosX() + 1, cellPosition.getPosY() - 1);
            }
        }
    }

    /**
     * Returns the new facing direction after a turn has taken place.
     *
     * @param directionToTurn the direction to turn.
     * @param currentFacingDirection the current facing direction before the
     * turn.
     * @return the new facing direction after performing the given turn on the
     * given facing direction.
     */
    protected int turn(TurnDirection directionToTurn, int currentFacingDirection) {
        if (directionToTurn == TurnDirection.RIGHT) {
            return (currentFacingDirection + 1) % 6;
        } else {
            if ((currentFacingDirection - 1) < 0) {
                return 5;
            } else {
                return (currentFacingDirection - 1) % 6;
            }
        }
    }

}

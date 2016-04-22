package antgameproject;

import instructions.Instruction;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jamestwisleton
 */
public class AntBrain {

    private List<Instruction> instructions = new ArrayList<>();
    private String name;
    private int gamesPlayedIn;
    private int gamesWon;
    private int gamesDrawn;
    private int gamesLost;
    private int totalFoodInBase;
    private int totalFoodInEnemyBase;
    private int points;

    public AntBrain(List<Instruction> instructions, String name) {
        this.instructions = instructions;
        this.name = name;
        gamesPlayedIn = 0;
        gamesWon = 0;
        gamesDrawn = 0;
        gamesLost = 0;
        totalFoodInBase = 0;
        totalFoodInEnemyBase = 0;
        points = 0;
    }

    public Instruction getInstruction(int state) {
        return instructions.get(state);
    }
    
    @Override
    public String toString(){
        return name;
    }

    public int getGamesPlayedIn() {
        return gamesPlayedIn;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getTotalFoodInBase() {
        return totalFoodInBase;
    }

    public int getTotalFoodInEnemyBase() {
        return totalFoodInEnemyBase;
    }

    public void incrementGamesPlayedIn() {
        gamesPlayedIn++;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }

    public void incrementGamesDrawn() {
        gamesDrawn++;
    }

    public void incrementGamesLost() {
        gamesLost++;
    }

    public void setTotalFoodInBase(int totalFoodInBase) {
        this.totalFoodInBase = totalFoodInBase;
    }

    public void setTotalFoodInEnemyBase(int totalFoodInEnemyBase) {
        this.totalFoodInEnemyBase = totalFoodInEnemyBase;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
}

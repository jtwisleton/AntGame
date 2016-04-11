package antgameproject;

import instructions.Instruction;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jamestwisleton
 */
public class AntBrain {

    private List<Instruction> instructions = new ArrayList<>();

    public AntBrain(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Instruction getInstruction(int state) {
        return instructions.get(state);
    }
}

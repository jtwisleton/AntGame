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

    public AntBrain(List<Instruction> instructions, String name) {
        this.instructions = instructions;
        this.name = name;
    }

    public Instruction getInstruction(int state) {
        return instructions.get(state);
    }
    
    @Override
    public String toString(){
        return name;
    }
}

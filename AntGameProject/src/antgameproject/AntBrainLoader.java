package antgameproject;

import conditions.Marker;
import instructions.TurnDirection;
import conditions.FriendWithFood;
import conditions.Rock;
import conditions.Home;
import conditions.Foe;
import conditions.Food;
import conditions.FoeHome;
import conditions.FoeWithFood;
import conditions.Friend;
import conditions.FoeMarker;
import instructions.SenseDirection;
import conditions.Condition;
import instructions.Instruction;
import instructions.PickUpFood;
import instructions.Flip;
import instructions.DropFood;
import instructions.Sense;
import instructions.Turn;
import instructions.Unmark;
import instructions.Mark;
import instructions.Move;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author James Twisleton
 */
public class AntBrainLoader {

    public static class AntBrainLoaderException extends Exception {

        public AntBrainLoaderException(String message) {
            super(message);
        }
    }

    public static void main(String args[]) throws FileNotFoundException, IOException, AntBrainLoaderException {

        /*
         Just a quick test to see that AntBrainLoader's loadBrain method performs
         as expected.
         */
        String fn = "src//antgameproject//testBrain.txt";
        AntBrain ab = loadBrain(fn, "Test Brain");
        for (int i = 0; i < 16; i++) {
            System.out.println(ab.getInstruction(i).toString());
        }

    }

    public static AntBrain loadBrain(String fileName, String name) throws FileNotFoundException, IOException, AntBrainLoaderException {

        /*
         Read the brain from a file into a String.
         */
        String brainString = "";
        int lineCount=0;
        List<String> instructionStrings = new ArrayList<>();
        List<Instruction> instructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                brainString += line;
                lineCount++;
            }
        }

        /*
         Declare pattern that describe the entire brain.
         */
        Pattern brainPattern = Pattern.compile("(Sense\\s+(Ahead|Here|LeftAhead|RightAhead)\\s+\\d+\\s+\\d+\\s+(FoeMarker|FoeWithFood|FoeHome|Foe|Food|FriendWithFood|Friend|Home|Marker\\s+\\d+|Rock))|((Mark|Unmark|PickUp)\\s+\\d+\\s+\\d+)|(Move\\s+\\d+\\s+\\d+)|(Turn\\s+(Left|Right)\\s+\\d+)|(Drop\\s+\\d+)|(Flip\\s+\\d+\\s+\\d+\\s+\\d+)");
        Matcher brainPatternMatcher = brainPattern.matcher(brainString);

        /*
         Iterate over the brain string, removing brain instruction matches and adding
         them to an arrayList of Strings. 
         */
        Boolean finished = false;
        while (!finished) {
            if (brainPatternMatcher.find()) {
                instructionStrings.add(brainPatternMatcher.group());
                brainString = brainString.replaceFirst(brainPattern.pattern(), "");
            } else {
                finished = true;
            }
            if ("".equals(brainString)) {
                finished = true;
            }
        }

        /*
         Remove whitespace from string. If the remaining string is not
         "", return that brain is invalid.
         */
        brainString = brainString.replaceAll("\\s", "");
        if (!"".equals(brainString)) {
            throw new AntBrainLoaderException("Invalid brain!");
        } else {

            /*
             If the brain is valid, parse the instructions and add to an instruction
             list.
             */
            Pattern sensePattern = Pattern.compile("Sense\\s+(Ahead|Here|LeftAhead|RightAhead)\\s+\\d+\\s+\\d+\\s+(FoeMarker|FoeWithFood|FoeHome|Foe|Food|FriendWithFood|Friend|Home|Rock)");
            Pattern senseMarkerPattern = Pattern.compile("Sense\\s+(Ahead|Here|LeftAhead|RightAhead)\\s+\\d+\\s+\\d+\\s+Marker\\s+\\d+");
            Pattern markPattern = Pattern.compile("(Mark|Unmark|PickUp)\\s+\\d+\\s+\\d+");
            Pattern movePattern = Pattern.compile("Move\\s+\\d+\\s+\\d+");
            Pattern turnPattern = Pattern.compile("Turn\\s+(Left|Right)\\s+\\d+");
            Pattern dropPattern = Pattern.compile("Drop\\s+\\d+");
            Pattern flipPattern = Pattern.compile("Flip\\s+\\d+\\s+\\d+\\s+\\d+");

            /*
             Iterate over the instruction strings, matching them to particular
             instructions. Then create Instruction objects using the parameters
             extracted from the instruction strings, and add these objects to a
             list.
             */
            String[] splitInstruction;
            for (String instructionString : instructionStrings) {

                /*
                 if the instructionString selected by the iterator is a Sense
                 instruction, create a new Sense instruction with the parsed
                 parameters and add it to instructions.
                 */
                if (instructionString.matches(sensePattern.pattern()) || instructionString.matches(senseMarkerPattern.pattern())) {
                    splitInstruction = instructionString.split("\\s+");
                    String direction = splitInstruction[1].trim();
                    int nextStateIfConditionTrue = Integer.parseInt(splitInstruction[2].trim());
                    int nextStateIfConditionFalse = Integer.parseInt(splitInstruction[3].trim());
                    int markerNo = -1;
                    if(instructionString.matches(senseMarkerPattern.pattern())){
                        markerNo = Integer.parseInt(splitInstruction[5].trim());
                    }
                    
                    if(nextStateIfConditionTrue>lineCount||nextStateIfConditionFalse>lineCount||markerNo>5){
                        throw new AntBrainLoaderException("State in loaded instruction beyond limits of brain.");
                    }
                    
                    String condition = splitInstruction[4].trim();
                    Condition c = null;
                    SenseDirection d = null;

                    switch (direction) {
                        case "Ahead":
                            d = SenseDirection.AHEAD;
                            break;
                        case "Here":
                            d = SenseDirection.HERE;
                            break;
                        case "LeftAhead":
                            d = SenseDirection.LEFTAHEAD;
                            break;
                        case "RightAhead":
                            d = SenseDirection.RIGHTAHEAD;
                            break;
                    }

                    switch (condition) {
                        case "FoeMarker":
                            c = new FoeMarker();
                            break;
                        case "FoeWithFood":
                            c = new FoeWithFood();
                            break;
                        case "FoeHome":
                            c = new FoeHome();
                            break;
                        case "Foe":
                            c = new Foe();
                            break;
                        case "Food":
                            c = new Food();
                            break;
                        case "FriendWithFood":
                            c = new FriendWithFood();
                            break;
                        case "Friend":
                            c = new Friend();
                            break;
                        case "Home":
                            c = new Home();
                            break;
                        case "Marker":
                            c = new Marker(markerNo);
                            break;
                        case "Rock":
                            c = new Rock();
                            break;
                    } 
                    instructions.add(new Sense(d, c, nextStateIfConditionTrue, nextStateIfConditionFalse));

                } /*
                 if the instructionString selected by the iterator is a Mark
                 instruction, create a new Mark instruction with the parsed
                 parameters and add it to instructions.
                 */ else if (instructionString.matches(markPattern.pattern())) {
                    splitInstruction = instructionString.split("\\s+");
                    String markUnmarkorPickup = splitInstruction[0].trim();
                    int markToSet = Integer.parseInt(splitInstruction[1].trim());
                    int nextState = Integer.parseInt(splitInstruction[2].trim());
                    Instruction i = null;

                    switch (markUnmarkorPickup) {
                        case "Mark":
                            i = new Mark(markToSet, nextState);
                            break;
                        case "Unmark":
                            i = new Unmark(markToSet, nextState);
                            break;
                        case "PickUp":
                            i = new PickUpFood(markToSet, nextState);
                            break;
                    }

                    instructions.add(i);

                } /*
                 if the instructionString selected by the iterator is a Move
                 instruction, create a new Move instruction with the parsed
                 parameters and add it to instructions.
                 */ else if (instructionString.matches(movePattern.pattern())) {
                    splitInstruction = instructionString.split("\\s+");
                    int nextStateIfAheadIsClear = Integer.parseInt(splitInstruction[1].trim());
                    int nextStateIfAheadIsBlocked = Integer.parseInt(splitInstruction[2].trim());

                    instructions.add(new Move(nextStateIfAheadIsClear, nextStateIfAheadIsBlocked));

                } /*
                 if the instructionString selected by the iterator is a Turn
                 instruction, create a new Turn instruction with the parsed
                 parameters and add it to instructions.
                 */ else if (instructionString.matches(turnPattern.pattern())) {
                    splitInstruction = instructionString.split("\\s+");
                    String turnDirection = splitInstruction[1].trim();
                    int nextState = Integer.parseInt(splitInstruction[2].trim());
                    TurnDirection d = null;

                    switch (turnDirection) {
                        case "Left":
                            d = TurnDirection.LEFT;
                            break;
                        case "Right":
                            d = TurnDirection.RIGHT;
                            break;
                    }

                    instructions.add(new Turn(d, nextState));

                } /*
                 if the instructionString selected by the iterator is a Drop
                 instruction, create a new Drop instruction with the parsed
                 parameters and add it to instructions.
                 */ else if (instructionString.matches(dropPattern.pattern())) {
                    splitInstruction = instructionString.split("\\s+");
                    int nextState = Integer.parseInt(splitInstruction[1].trim());

                    instructions.add(new DropFood(nextState));

                } /*
                 if the instructionString selected by the iterator is a Flip
                 instruction, create a new Flip instruction with the parsed
                 parameters and add it to instructions.
                 */ else if (instructionString.matches(flipPattern.pattern())) {
                    splitInstruction = instructionString.split("\\s+");
                    int n = Integer.parseInt(splitInstruction[1].trim());
                    int nextStateIfZero = Integer.parseInt(splitInstruction[2].trim());
                    int nextStateElse = Integer.parseInt(splitInstruction[3].trim());

                    instructions.add(new Flip(n, nextStateIfZero, nextStateElse));
                }
            }

            /*
             Once the instructions list has been populated with parsed instructions,
             create a new AntBrain object with instructions as its parameter, and
             return this AntBrain.
             */
            return new AntBrain(instructions, name);
        }
    }
}

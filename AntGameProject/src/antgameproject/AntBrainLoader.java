package antgameproject;

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

    
    public static void main (String args[]) throws FileNotFoundException, IOException {
       
        /*
         Read the brain from a file into a String.
         */
        String fn = "src//antgameproject//testBrain.txt";
        String brainString = "";
        List<String> instructionStrings = new ArrayList<>();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fn)));
        
        try {
            String line;
            while ((line = br.readLine()) != null) {
                brainString += line;
            }
        } finally {
            br.close();
        }

        /*
         Declare pattern that describe the entire brain.
         */        
        Pattern brainPattern = Pattern.compile("(Sense\\s+(Ahead|Here|LeftAhead|RightAhead)\\s+\\d+\\s+\\d+\\s+(FoeMarker|FoeWithFood|FoeHome|Foe|Food|FriendWithFood|Friend|Home|Marker|Rock))|((Mark|Unmark|Pickup)\\s+\\d+\\s+\\d+)|(Move\\s+\\d+\\s+\\d+)|(Turn\\s+(Left|Right)\\s+\\d+)|(Drop\\s+\\d+)|(Flip\\s+\\d+\\s+\\d+\\s+\\d+)");
        Matcher brainPatternMatcher = brainPattern.matcher(brainString);

        /*
         Iterate over the string, "eating off" brain instruction matches and adding
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
         "", return that brain is invalid. Else return that brain is valid.
         */
        brainString = brainString.replaceAll("\\s", "");
        if (!"".equals(brainString)) {
            System.out.println("Invalid brain!");
            //return null;
        } else {            
            System.out.println("Valid brain!");
            Pattern sensePattern = Pattern.compile("Sense\\s+(Ahead|Here|LeftAhead|RightAhead)\\s+\\d+\\s+\\d+\\s+(FoeMarker|FoeWithFood|FoeHome|Foe|Food|FriendWithFood|Friend|Home|Marker|Rock)");
            Pattern markPattern = Pattern.compile("(Mark|Unmark|Pickup)\\s+\\d+\\s+\\d+");
            Pattern movePattern = Pattern.compile("Move\\\\s+\\\\d+\\\\s+\\\\d+");
            Pattern turnPattern = Pattern.compile("Turn\\\\s+(Left|Right)\\\\s+\\\\d+");
            Pattern dropPattern = Pattern.compile("Drop\\\\s+\\\\d+");
            Pattern flipPattern = Pattern.compile("Flip\\\\s+\\\\d+\\\\s+\\\\d+\\\\s+\\\\d+");
            
            for (String instructionString : instructionStrings) {
                if (instructionString.matches(sensePattern.pattern())){
                    String[] splitInstruction = instructionString.split("\\s+");                    
                    
                }else if(instructionString.matches(markPattern.pattern())){
                    String[] splitInstruction = instructionString.split("\\s+");                    
                }else if(instructionString.matches(movePattern.pattern())){
                    String[] splitInstruction = instructionString.split("\\s+");                    
                }else if(instructionString.matches(turnPattern.pattern())){
                    String[] splitInstruction = instructionString.split("\\s+");                    
                }else if(instructionString.matches(dropPattern.pattern())){
                    String[] splitInstruction = instructionString.split("\\s+");                    
                }else if(instructionString.matches(flipPattern.pattern())){
                    String[] splitInstruction = instructionString.split("\\s+");                    
                }
            }
            
        }
    
    }
}

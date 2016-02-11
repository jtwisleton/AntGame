package antgameproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author James Twisleton
 */
public class AntBrainChecker {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*
         Read the brain from a file into a String.
         */
        String fn = "src//antgameproject//testBrain.txt";
        String brainString = "";
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fn)));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                brainString += line;
                brainString += '\n';
                System.out.println(brainString);
            }
        } finally {
            br.close();
        }

        /*
         Declare pattern that describe the various brain instructions.
         */
        Pattern brainPattern = Pattern.compile("(Sense\\s+(Ahead|Here|LeftAhead|RightAhead)\\s+\\d+\\s+\\d+\\s+(FoeMarker|FoeWithFood|FoeHome|Foe|Food|FriendWithFood|Friend|Home|Marker|Rock))|((Mark|Unmark|Pickup)\\s+\\d+\\s+\\d+)|(Move\\s+\\d+\\s+\\d+)|(Turn\\s+(Left|Right)\\s+\\d+)|(Drop\\s+\\d+)|(Flip\\s+\\d+\\s+\\d+\\s+\\d+)");
        Matcher brainPatternMatcher = brainPattern.matcher(brainString);

        /*
         Iterate over the string, "eating off" brain instruction matches. 
         */
        Boolean finished = false;
        while (!finished) {
            if (brainPatternMatcher.find()) {
                String brainPatternMatchedString = brainPatternMatcher.group();
                brainString = brainString.replaceFirst(brainPattern.pattern(), "");
            } else {
                finished = true;
            }

            if ("".equals(brainString)) {

                finished = true;
            }
        }

        /*
         Remove whitespace and new lines from string. If the remaining string is not
         "", return that brain is invalid. Else return that brain is valid.
         */
        brainString = brainString.replaceAll("\\n|\\s", "");
        if (!"".equals(brainString)) {
            System.out.println("final brainString: " + brainString);
            System.out.println("Invalid brain!");
        } else {
            System.out.println("Valid brain!");
        }

    }
}

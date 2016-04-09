package antgameproject;

import antgameproject.AntBrainLoader.AntBrainLoaderException;
import antgameproject.AntWorldChecker.AntWorldCheckerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joseph Kimberley
 */
public class AntGameTournament {
 
    private List<Board> antWorlds;
    private List<AntBrain> antBrains;
    private List<Pair<AntBrain, AntBrain>> pairs;
    private HashMap<AntBrain, Integer> scores;
    private Game currentGame;
    
    public static class Pair<X, Y> {
        public final AntBrain one;
        public final AntBrain two;
        
        public Pair(AntBrain one, AntBrain two) {
            this.one = one;
            this.two = two;
        }
    }
    
    public AntGameTournament(List<String> antBrainFiles, List<String> antWorldFiles) {
        antBrains = loadAntBrains(antBrainFiles);
        antWorlds = loadAntWorlds(antWorldFiles);
        pairs = new ArrayList<>();
        
        scores = new HashMap<>();
        for (AntBrain brain : antBrains) {
            scores.put(brain, 0);
        }
    }
    
    public AntGameTournament(List<String> antBrainFiles, int numAntWorlds) {
        antBrains = loadAntBrains(antBrainFiles);
        for (int i = 0; i < numAntWorlds; i++) {
            // Generate ant world
            // Check ant world and add to array
        }
    }
    
    public void runTournament() {
        for (int i = 0; i < antBrains.size() - 1; i++) {
            for (int j = i + 1; j < antBrains.size(); j++) {
                pairs.add(new Pair(antBrains.get(i), antBrains.get(j)));
            }
        }
        
        for (Board antWorld : antWorlds) {
            for (Pair pair : pairs) {
                currentGame = new Game(pair.one, pair.two, antWorld);
                currentGame.runGame();
                // get score
                
                currentGame = new Game(pair.two, pair.one, antWorld);
                currentGame.runGame();
            }
        } 
    }
    
    private List<AntBrain> loadAntBrains(List<String> antBrainFiles) {
        List<AntBrain> brains = new ArrayList<>();
        
        for (String antBrainFile : antBrainFiles) {
            try {
                brains.add(AntBrainLoader.loadBrain(antBrainFile));
            }catch (IOException | AntBrainLoaderException e) {
                System.out.println(e);
            }
        }
        
        return brains;
    }
    
    private List<Board> loadAntWorlds(List<String> antWorldFiles) {
        List<Board> worlds = new ArrayList<>();
        
        for (String antWorldFile : antWorldFiles) {
            try {
                AntWorldChecker.loadWorld(antWorldFile);
            }catch (IOException | AntWorldCheckerException e) {
                System.out.println(e);
            }
        }
        
        return worlds;
    }
}

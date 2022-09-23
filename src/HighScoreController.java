import java.io.*;
import java.util.*;

public class HighScoreController {
    private static final List<HighScore> highScores = new ArrayList<>();
    private static final int SIZE = 9;

    static{
        read();
        if(highScores.size() < SIZE){
            for(int i=SIZE-highScores.size(); i>0; i--) {
                highScores.add(new HighScore("---", 0));
            }
        }
    }

    private static void read(){
        try(DataInputStream myFile = new DataInputStream(new BufferedInputStream(new FileInputStream("resources/high_scores.dat")))){
            boolean eof = false;
            while(!eof){
                try {
                    String name = myFile.readUTF();
                    int score = myFile.readInt();
                    highScores.add(new HighScore(name, score));
                } catch (EOFException ignored){
                    eof = true;
                }
            }
        } catch (Exception ignored){
        }
    }

    private static void write(){
        try(DataOutputStream myFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("resources/high_scores.dat")))){
            int count = 1;
            for(HighScore highScore: highScores){
                myFile.writeUTF(highScore.getName());
                myFile.writeInt(highScore.getScore());
                if(count == SIZE) break;
                count++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<HighScore> getHighScores(){
        return new ArrayList<>(highScores);
    }

    public static void addScore(String name, int score){
        highScores.add(new HighScore(name, score));
        Collections.sort(highScores);
        highScores.remove(0); // Remove the lowest score
        Collections.reverse(highScores);
        write();
    }

    public static boolean isNewHighScore(int newScore){
        for(HighScore highScore: highScores){
            if(newScore > highScore.getScore()) return true;
        }
        return false;
    }
}

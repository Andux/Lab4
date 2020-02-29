import java.util.ArrayList;

public class Highscore implements Comparable<Highscore>{
    private String name;
    private int score;
    private ArrayList<String> guessedWords;

    public Highscore(String name, int score, ArrayList<String> guessedWords) {
        this.name = name;
        this.score = score;
        this.guessedWords  = guessedWords;
    }
    public int getScore() {
        return score;
    }
    @Override
    public int compareTo(Highscore otherScore) {
        return Integer.compare(score, otherScore.getScore());
    }
}

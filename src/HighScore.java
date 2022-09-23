public class HighScore implements Comparable<HighScore>{
    private final String name;
    private final int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name +  "  " + score;
    }

    @Override
    public int compareTo(HighScore that) {
        return Integer.compare(this.score, that.getScore());
    }
}

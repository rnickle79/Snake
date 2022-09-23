import java.awt.*;

public class Game {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int UNIT_SIZE = 25;
    public static final int TOTAL_GAME_UNITS = (WIDTH/UNIT_SIZE) * (HEIGHT/UNIT_SIZE);
    public static State GameState;

    private final Snake snake;
    private final Apple apple;
    private static int score;

    public Game() {
        snake = new Snake();
        apple = new Apple();
        GameState = State.RUNNING;
    }

    public void update(){
        snake.update();
        apple.update();
        checkApple();
    }

    public void reset() {
        snake.reset();
        score = 0;
        apple.spawn();
        GameState = State.RUNNING;
    }

    public static void resetScore(){
        score = 0;
    }

    public void quit() {
        System.exit(0);
    }

    public void render(Graphics g){
        snake.render(g);
        apple.render(g);
    }

    public void checkApple(){
        if(snake.getBounds().intersects(apple.getBounds())){
            snake.setBodySize(snake.getBodySize() + 1);
            score++;
            apple.spawn();
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public static int getScore() {
        return score;
    }
}

import java.awt.*;
import java.util.Random;

public class Apple {
    private int x,y;
    private final Random random;

    public Apple() {
        random = new Random();
        spawn();
    }

    public void update() {
    }

    public void spawn(){
        // Spawn this apple at random location
        x = random.nextInt((Game.WIDTH/Game.UNIT_SIZE)) * Game.UNIT_SIZE;
        y = random.nextInt((Game.HEIGHT/Game.UNIT_SIZE)) * Game.UNIT_SIZE;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x,y,Game.UNIT_SIZE, Game.UNIT_SIZE);
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,Game.UNIT_SIZE, Game.UNIT_SIZE);
    }
}

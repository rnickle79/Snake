import java.awt.*;

public class Snake {

    private int[] x;
    private int[] y;
    private int bodySize;
    private char direction;

    public Snake() {
        reset();
    }

    public void reset() {
        x = new int[Game.TOTAL_GAME_UNITS];
        y = new int[Game.TOTAL_GAME_UNITS];
        direction = 'R';
        bodySize = 6;
        x[0] = 0;
        y[0] = 0;
    }

    private void move(){
        // Move body
        for(int i=bodySize; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        // Move head
        switch(direction){
            case 'U':  y[0] -= Game.UNIT_SIZE; break;
            case 'D':  y[0] += Game.UNIT_SIZE; break;
            case 'L':  x[0] -= Game.UNIT_SIZE; break;
            case 'R':  x[0] += Game.UNIT_SIZE; break;
        }
    }
    private void die(){
        if(HighScoreController.isNewHighScore(Game.getScore())){
            Game.GameState = State.HIGH_SCORE;
        } else {
            Game.GameState = State.GAME_OVER;
        }
    }

    private void checkCollision(){
        // Check for collision with self
        for(int i=bodySize; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                die();
                break;
            }
        }
        // Check for collision with edge of window
        if(x[0] < 0) die();
        if(x[0] > (Game.WIDTH - Game.UNIT_SIZE)+1) die();
        if(y[0] < 0) die();
        if(y[0] > (Game.HEIGHT - Game.UNIT_SIZE)+1) die();
    }

    public void update() {
        move();
        checkCollision();
    }

    public void render(Graphics g) {
        for(int i=0; i<bodySize; i++){
            // Body color
            g.setColor(new Color(49, 120, 3));
            // Head color
            if(i==0) g.setColor(Color.green);
            g.fillRect(x[i],y[i],Game.UNIT_SIZE,Game.UNIT_SIZE);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x[0], y[0], Game.UNIT_SIZE, Game.UNIT_SIZE);
    }

    private boolean causesCollision(int[] nextCor){
        return nextCor[0] == x[1] && nextCor[1] == y[1];
    }

    private int[] getNextCor(char dir){
        int[] cor = new int[2];
        switch(dir){
            case 'U': cor[0] = x[0]; cor[1] = y[0] - Game.UNIT_SIZE; break;
            case 'D': cor[0] = x[0]; cor[1] = y[0] + Game.UNIT_SIZE; break;
            case 'L': cor[0] = x[0] - Game.UNIT_SIZE; cor[1] = y[0] ; break;
            case 'R': cor[0] = x[0] + Game.UNIT_SIZE; cor[1] = y[0] ; break;
        }
        return cor;
    }

    private char getOppositeDir(char dir){
        switch(dir){
            case 'U': return 'D';
            case 'L': return 'R';
            case 'D': return 'U';
            case 'R': return 'L';
        }
        return 'X';
    }

    public void setBodySize(int bodySize) {
        this.bodySize = bodySize;
    }

    public int getBodySize() {
        return bodySize;
    }

    public void setDirection(char direction){
        if(this.direction != getOppositeDir(direction)) {
            if(!causesCollision(getNextCor(direction))) {
                this.direction = direction;
            }
        }
    }
}

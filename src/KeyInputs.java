import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
    private final Game game;

    public KeyInputs(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W: game.getSnake().setDirection('U'); break;
            case KeyEvent.VK_A: game.getSnake().setDirection('L'); break;
            case KeyEvent.VK_S: game.getSnake().setDirection('D'); break;
            case KeyEvent.VK_D: game.getSnake().setDirection('R'); break;
            case KeyEvent.VK_SPACE:
                if(Game.GameState == State.RUNNING){
                    Game.GameState =State.PAUSE;
                } else if (Game.GameState == State.PAUSE){
                    Game.GameState = State.RUNNING;
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

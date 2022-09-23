import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseInputs extends MouseAdapter {

    public MouseInputs() {
    }

    private boolean mouseOver(int mX, int mY, int x, int y, int width, int height){
        return (mX > x && mX < x + width) && (mY > y && mY < y + height);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();
        if(Game.GameState == State.GAME_OVER) {
            //Play Again button
            if (mouseOver(mX, mY,0,Game.HEIGHT-70,200,64)) {
                Game.GameState = State.RESET;
            //Quit button
            } else if (mouseOver(mX, mY,Game.WIDTH-201,Game.HEIGHT-70,200,64)) {
                Game.GameState = State.QUIT;
            }
        }

    }
}

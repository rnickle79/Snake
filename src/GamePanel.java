import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class GamePanel extends JPanel implements ActionListener {
    private final Game game;
    private final Menu menu;
    private Timer timer;

    public GamePanel(Game game) {
        this.game = game;
        this.menu = new Menu();
        initPanel();
        startGameLoop();
    }

    private void initPanel(){
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        addKeyListener(new KeyInputs(game));
        addMouseListener(new MouseInputs());
        setFocusable(true);
        requestFocusInWindow();
    }

    public void startGameLoop(){
        timer = new Timer(75, this);
        timer.start();
    }

    public void stopTimer(){
        timer.stop();
    }

    public void restartTimer(){
        timer.restart();
    }

    public void renderBackground(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,Game.WIDTH, Game.HEIGHT);
    }

/*
    public void renderGrid(Graphics g){
        g.setColor(Color.white);
        for(int i=0; i<Game.WIDTH/Game.UNIT_SIZE; i++){
            g.drawLine(i*Game.UNIT_SIZE,0,i*Game.UNIT_SIZE,Game.HEIGHT);
            g.drawLine(0, i*Game.UNIT_SIZE, Game.WIDTH,i*Game.UNIT_SIZE);
        }
    }
*/

    public void renderScore(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        DecimalFormat df = new DecimalFormat("000");
        String text = "SCORE: " + df.format(Game.getScore());
        g.drawString(text, Game.WIDTH - metrics.stringWidth(text),20);
    }

/*------------------- Game Loop ------------------------------------------------
    * Timer triggers actionPerformed()
    * repaint() calls paintComponent()
    * */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderBackground(g);
        //renderGrid(g);
        renderScore(g);

        switch(Game.GameState){
            case RUNNING: game.render(g); break;
            case GAME_OVER: menu.gameOver(g); break;
            case HIGH_SCORE: menu.highScore(this,g); break;
            case PAUSE: menu.pause(g); game.render(g); break;
            case RESET: game.reset(); break;
            case QUIT: game.quit(); break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Game.GameState == State.RUNNING) {
            game.update();
        }
        repaint();
    }
}

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class Menu extends JComponent{

    public Menu() {
    }

    public void pause(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        String text = "P A U S E";
        g.drawString(text, (Game.WIDTH - metrics.stringWidth(text)) / 2, Game.HEIGHT / 2);
    }

    public void gameOver(Graphics g){
        // Display Game Over text
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        String text = "GAME OVER";
        g.drawString(text, (Game.WIDTH - metrics.stringWidth(text)) / 2, 80);

        // Display High Scores text
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        metrics = getFontMetrics(g.getFont());
        text = "High Scores";
        g.drawString(text, (Game.WIDTH - metrics.stringWidth(text)) / 2, 140);

        // Display each high score
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        DecimalFormat df = new DecimalFormat("000");
        int highScoreY = 180;
        int slot = 1;
        for(HighScore highScore: HighScoreController.getHighScores()){
            g.drawString(slot + ". " + highScore.getName(), 220, highScoreY);
            g.drawString(df.format(highScore.getScore()), 350, highScoreY);
            highScoreY += 40;
            slot++;
        }

        //Play Again Button
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        g.drawRect(0,Game.HEIGHT-70,200,64);
        g.drawString("Play Again", 20, Game.HEIGHT-30);

        //Quit Button
        g.drawRect(Game.WIDTH-201,Game.HEIGHT-70,200,64);
        g.drawString("Quit", 470, Game.HEIGHT-30);
    }

    public void highScore(GamePanel panel, Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        String text = "New High Score!!!";
        g.drawString(text, (Game.WIDTH - metrics.stringWidth(text))/2, Game.HEIGHT/2-100);
        text = "Enter your initials: ";
        g.drawString(text, (Game.WIDTH - metrics.stringWidth(text))/2, Game.HEIGHT/2);
        JTextField textField = new JTextField("");
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new UppercaseFilter());
        textField.setBounds(Game.WIDTH/2-50,Game.HEIGHT/2+60,70,40);
        textField.setFont(new Font("Consolas", Font.BOLD,30));
        textField.setBackground(Color.lightGray);
        textField.setForeground(Color.black);
        final int[] keyCount = {0};
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER){
                    String name = textField.getText().toUpperCase();
                    HighScoreController.addScore(name,Game.getScore());
                    panel.remove(textField);
                    Game.resetScore();
                    Game.GameState = State.GAME_OVER;
                    panel.restartTimer();
                }

                if(Character.isAlphabetic(e.getKeyChar())){
                    if(keyCount[0] < 3) {
                        keyCount[0]++;
                        textField.setEditable(true);
                    } else {
                        textField.setEditable(false);
                    }
                } else {
                    textField.setEditable(false);
                }

                if(key == KeyEvent.VK_BACK_SPACE){
                    textField.setEditable(true);
                    keyCount[0]--;
                    if(keyCount[0] < 0) keyCount[0] = 0;
                }
            }
        });
        panel.add(textField);
        panel.stopTimer();
        textField.setVisible(true);
        textField.setFocusable(true);
        textField.requestFocusInWindow();
    }
}

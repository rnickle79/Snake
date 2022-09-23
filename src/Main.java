public class Main {

    public static void main(String[] args) {
        new Thread(new BackGroundMusic()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new GameFrame(new GamePanel(new Game()));
            }
        }).start();

    }
}

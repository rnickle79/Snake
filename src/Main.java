public class Main {

    public static void main(String[] args) {
        new Thread(new BackGroundMusic()).start();

        new Thread(() -> new GameFrame(new GamePanel(new Game()))).start();

    }
}

import java.util.Scanner;

public class GameStarter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("IP Address: ");

        String ip = in.next();
        GameFrame gf = new GameFrame();

        gf.connectToServer(ip);
        gf.setUpGameFrame();
        in.close();
    }
}

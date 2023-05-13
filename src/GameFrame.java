import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;

public class GameFrame implements KeyListener {

    private final JLabel labelReady;
    JFrame gameFrame;
    JButton button;
    JLabel label_1;
    Socket s;
    int playerID;
    private int clicks = 0;
    GameCanvas gameCanvas;
    ReadFromServer rfsRunnable;
    WriteToServer wtsRunnable;
    Player player;

    public GameFrame() {
        this.gameFrame = new JFrame();
        this.gameCanvas = new GameCanvas();

        this.button = new JButton("Start");
        this.labelReady = new JLabel("Are you ready?");
        this.playerID = 0;

        player = new Player(playerID);

    }

    public void setUpGameFrame() {
        gameFrame.setTitle("Type of War | Player #" + playerID);
        gameFrame.setSize(1900, 1080);

        gameFrame.add(button);
        gameFrame.add(labelReady);

        gameFrame.setLocationRelativeTo(null);

        gameFrame.add(gameCanvas);
        gameFrame.setResizable(false);

        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*KeyListener for Spacebar. When clicked, it adds to the variable clicks.
    GameFrame sends clicks to the player. Player sends to GameServer. GameServer calculates
    for the tugging speed.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            System.out.println("KeyPressed");

            clicks++;
            gameCanvas.repaint();

            player.Speed(clicks);
            }
        }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void connectToServer(String ip) {
        try {
            s = new Socket(ip, 2000);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            DataInputStream in = new DataInputStream(s.getInputStream());
            System.out.println("Connected to server " + ip);

            playerID = in.readInt();
            System.out.println("You are Player #" + playerID + ".");

            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);

            if (playerID == 1) System.out.print("Waiting for Player #2 to connect."); // for Player 1 only
        } catch (Exception e) {
            System.out.println("Unable to connect to server.");
        }
    }

    private class ReadFromServer implements Runnable {
        private DataInputStream in;

        private ReadFromServer(DataInputStream d) {
            in = d;
            System.out.println("RFS Runnable created");
        }

        @Override
        public void run() {
            try {
                while(true){

                }
                // Add a while loop that continuously receives the rope speed
                // Add to an ArrayList
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    private class WriteToServer implements Runnable {
        private DataOutputStream out;

        private WriteToServer(DataOutputStream d) {
            out = d;
            System.out.println("WTS Runnable created");
        }

        @Override
        public void run() {
            try{
                while(true){
                    out.writeDouble(1); //PLACE HOLDER
                    out.flush();
                    try{
                        Thread.sleep(25);
                    }catch(InterruptedException e){
                        System.out.println("Interrupted Exception from WTS Run");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

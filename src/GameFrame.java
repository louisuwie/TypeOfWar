import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.*;
import java.net.*;

public class GameFrame {
    
    JFrame jf;
    JButton jb;
    JLabel jl;
    Socket s;
    int playerID;
    GameCanvas gc;
    ReadFromServer rfsRunnable;
    WriteToServer wtsRunnable;

    public GameFrame() {
        this.jf = new JFrame();
        this.gc = new GameCanvas(); 
        gc.addKeyBindings();
        jf.add(gc);
        this.jb = new JButton();
        this.jl = new JLabel();
        this.playerID = 0;
    }

    public void setUpGameFrame() {
        jf.setTitle("Type of War | Player #" + playerID);
        jf.setSize(500, 500);
        jb.setText("Start");
        jl.setText("Are you ready?");
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            try {
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }
}

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameFrame {
    
    JFrame jf;
    JButton jb;
    JLabel jl;
    Socket s;
    GameCanvas gc;
    
    ReadFromServer rfsRunnable;
    WriteToServer wtsRunnable;
    
    int playerID;
    Player player;

    ArrayList<Integer> ropeSpeeds;
    int ropeSpeed;

    public GameFrame() {
        this.jf = new JFrame();

        this.gc = new GameCanvas(); 
        gc.addKeyBindings();
        gc.startClickTimer();
        gc.startRepaintTimer();
        jf.add(gc);

        this.jb = new JButton();
        this.jl = new JLabel();
        this.playerID = 0;
        this.player = null;
        this.ropeSpeeds = new ArrayList<>();
        
        this.ropeSpeed = 0;
    }

    public void setUpGameFrame() {
        jf.setTitle("Type of War | Player #" + playerID);
        jf.setSize(960, 540);
        jb.setText("Start");
        jl.setText("Are you ready?");
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
    }

    public void connectToServer(String ip) {
        try {
            s = new Socket(ip, 2000);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            DataInputStream in = new DataInputStream(s.getInputStream());
            System.out.println("Connected to server " + ip);
            playerID = in.readInt();
            System.out.println("You are Player #" + playerID + ".");
            // Instantiates a new Player class for each GameFrame that is connected to the GameServer
            player = new Player(playerID);
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
                while (true) {
                    ropeSpeed = in.readInt();
                    System.out.println("The current speed is " + ropeSpeed);
                }
            } catch (Exception e) {
                System.out.println("RFS Run failed.");
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
                while (true) {
                    int clicks = gc.getClicks();
                    player.calculateSpeed(clicks);
                    out.writeInt(player.getSpeed());
                    out.flush();
                    System.out.println("Clicks: " + clicks);
                    System.out.println("Player Speed: " + player.getSpeed());
                }
            } catch (Exception e) {
                System.out.println("WTS Runnable failed.");
            }
        }
    }

    // Refreshes the velocity that is passsed to gc every 1 second
    public void setUpFrameTimers() {
        Timer timer = new Timer(10000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gc.passVelocity(ropeSpeed);
            }
            
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void startThreads() {
        Thread rr = new Thread(rfsRunnable);
        Thread wr = new Thread(wtsRunnable);
        rr.start();
        wr.start();
    }
}

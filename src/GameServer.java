import java.io.*;
import java.net.*;

import java.awt.event.*;
import javax.swing.Timer;

public class GameServer {

    ServerSocket ss;
    Socket s1, s2;
    int maxPlayers, numPlayers;

    private ReadFromClient p1ReadRunnable, p2ReadRunnable;
    private WriteToClient p1WriteRunnable, p2WriteRunnable;

    private int p1Speed, p2Speed;
    private int ropeSpeed;

    public GameServer() {

        numPlayers = 0;
        maxPlayers = 2;

        try {
            ss = new ServerSocket(2000);
            System.out.println("Initialized server.");
        } catch (Exception e) {
            System.out.println("Failed to initialize server.");
        }
    }

    public void acceptConnections() {
        try {
            while (numPlayers < maxPlayers) {
                Socket s = ss.accept();
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                DataInputStream in = new DataInputStream(s.getInputStream()); // Not sure if this is needed.
                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + " has connected.");
                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if (numPlayers == 1) {
                    s1 = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                } else if (numPlayers == 2) {
                    s2 = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;

                    // At this point, there are 2 players connected already.
                    p1WriteRunnable.sendStartMessage();
                    p2WriteRunnable.sendStartMessage();

                    // Start the threads
                    Thread read1 = new Thread(p1ReadRunnable);
                    Thread read2 = new Thread(p2ReadRunnable);
                    read1.start();
                    read2.start();
                    Thread write1 = new Thread(p1WriteRunnable);
                    Thread write2 = new Thread(p2WriteRunnable);
                    write1.start();
                    write2.start();
                }
            }
            System.out.println("No longer accepting players.");
        } catch (Exception e) {
            System.out.println("Failed to accept connections.");
        }
    }

    // Inner Classes
    private class ReadFromClient implements Runnable {

        private int playerID;
        private DataInputStream in;

        public ReadFromClient(int p, DataInputStream d) {
            playerID = p;
            in = d;
            System.out.println("RFC" + playerID + " Runnable created.");
        }
        // This run() method continuously reads the clicks sent from GameCanvas and assigns it to speed variables foe ach of the players.
        @Override
        public void run() {

            try {
                while (true) {
                    if (playerID == 1) {
                        p1Speed = in.readInt();
                    } else if (playerID == 2) {
                        p2Speed = in.readInt();
                    }
                }
            } catch (Exception e) {
                System.out.println("RFC run() failed.");
            }
        }
    }

    private class WriteToClient implements Runnable {

        private int playerID;
        private DataOutputStream out;
        public WriteToClient(int p, DataOutputStream d) {
            playerID = p;
            out = d;
            System.out.println("WTC" + playerID + " Runnable created.");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    out.writeInt(ropeSpeed);
                    out.flush();
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                    }
                }
            } catch (Exception e) {
                System.out.println("WTC Run failed.");
            }
        }

        public void sendStartMessage() {
            try {
               out.writeUTF("We now have 2 players."); 
            } catch (IOException e) {
                System.out.println("IO Exception from sendStartMessage");
            }
        }
    }
    public void startServerTimer() {
        Timer speedCalculator = new Timer(1, new ActionListener() {
    
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calculate and set new rope speed based on current speeds sa Read
                ropeSpeed = p1Speed + p2Speed; 
            }
            
        });
        speedCalculator.setRepeats(true);
        speedCalculator.start();
    }

    // Method for starting threads

    public static void main(String args[]) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
        gs.startServerTimer();
    }

}
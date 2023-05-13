import java.io.*;
import java.net.*;
import java.util.*;

import java.awt.*;
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

    private ArrayList<Integer> p1InitialSpeeds, p2InitialSpeeds;
    private ArrayList<Integer> ropeSpeeds;


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
        private int speed;

        public ReadFromClient(int p, DataInputStream d) {
            playerID = p;
            in = d;
            System.out.println("RFC" + playerID + " Runnable created.");
        }
        // This run() method continuously reads the clicks sent from GameCanvas and assigns it to speed variables foe ach of the players.
        @Override
        public void run() {
            while (true) {
                try {
                    speed = in.readInt();
                    if (playerID == 1) {
                        p1Speed = speed;
                    } else if (playerID == 2) {
                        p2Speed = speed;
                    }
                    System.out.println("This code is reachable 3.");
                } catch (IOException e) {
                    System.out.println("RFC run() failed.");
                }
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
                    System.out.println("This code is reachable 4.");
                }
            } catch (Exception e) {
                System.out.println("WTC Run failed.");
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
    public void startThreads() {
        // TODO Put code that sir says at the end of his video
        Thread p1rrThread = new Thread(p1ReadRunnable);
        Thread p2rrThread = new Thread(p2ReadRunnable);
        Thread p1wrThread = new Thread(p1WriteRunnable);
        Thread p2wrThread = new Thread(p2WriteRunnable);

        p1rrThread.start();
        p2rrThread.start();
        p1wrThread.start();
        p2wrThread.start();
    }

    public static void main(String args[]) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
        gs.startServerTimer();
        gs.startThreads();
    }

}
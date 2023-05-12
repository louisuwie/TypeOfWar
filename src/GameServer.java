import java.io.*;
import java.net.*;

public class GameServer {

    ServerSocket ss;
    Socket s1, s2;
    int maxPlayers, numPlayers;

    private ReadFromClient p1ReadRunnable, p2ReadRunnable;
    private WriteToClient p1WriteRunnable, p2WriteRunnable;
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
        private DataInputStream dataIn;

        public ReadFromClient(int p, DataInputStream d) {
            playerID = p;
            dataIn = d;
            System.out.println("RFC" + playerID + " Runnable created.");
        }
        @Override
        public void run() {

        }
    }

    private class WriteToClient implements Runnable {

        private int playerID;
        private DataOutputStream dataOut;
        public WriteToClient(int p, DataOutputStream d) {
            playerID = p;
            dataOut = d;
            System.out.println("WTC" + playerID + " Runnable created.");
        }

        @Override
        public void run() {

        }
    }

    public static void main(String args[]) {
        GameServer gs = new GameServer();
        gs.acceptConnections();

    }

}
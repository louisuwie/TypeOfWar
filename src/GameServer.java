import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameServer {

    ServerSocket ss;
    Socket s1, s2;
    int maxPlayers, numPlayers, speed;

    private ReadFromClient p1ReadRunnable, p2ReadRunnable;
    private WriteToClient p1WriteRunnable, p2WriteRunnable;

    private Double p1Speed, p2Speed;

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
        private DataInputStream dataIn;

        public ReadFromClient(int p, DataInputStream d) {
            playerID = p;
            dataIn = d;

            //p1Speed = Player.getSpeed(); SOMETHING WRONG WITH THIS.

            System.out.println("RFC" + playerID + " Runnable created.");
        }
        @Override
        public void run() {
            try{
                while(true){
                    if(playerID == 1){
                        p1Speed = dataIn.readDouble();
                    } else if(playerID == 2){
                        p2Speed = dataIn.readDouble();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
            try{
                while(true){
                    if(playerID == 1){
                        dataOut.writeDouble(p2Speed);
                        dataOut.flush();
                    } else if(playerID == 2){
                        dataOut.writeDouble(p1Speed);
                        dataOut.flush();
                    }
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

    public void speedCalulator(int clicks){

    }

    public static void main(String args[]) {
        GameServer gs = new GameServer();
        gs.acceptConnections();

    }

}
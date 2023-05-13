/**
 @author Louis G. Binwag III (200747) & Maria Charmane Rose E. Naciongayo (214152)
 @version April 25, 2023
 **/

/*
	I have not discussed the Java language code in my program
	with anyone other than my instructor or the teaching assistants
	assigned to this course.

	I have not used Java language code obtained from another student,
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program
	was obtained from another source, such as a textbook or website,
	that has been clearly noted with a proper citation in the comments
	of my program.
*/

/*
    GameCanvas.java handles the graphics-side of the program.
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.io.*;
import java.net.*;

// TODO Implement ArrayLists to keep track of the speeds to avoid lag
// TODO Make the Start JButton show both GameCanvases
// TODO Center the RopeAssembly image; Scale them properly because they are a little warped
// TODO Add something to the middle for collision / Implement something that will end the game
// TODO Interval Word Input

public class GameFrame {
    
    JFrame jf;
    JButton jb;
    JLabel jl;
    Socket s;
    GameCanvas gc;
    StartScreen startScreen;
    
    ReadFromServer rfsRunnable;
    WriteToServer wtsRunnable;
    
    int playerID;
    Player player;

    int ropeSpeed;

    JPanel bg;

    public GameFrame() {
        this.jf = new JFrame();
        this.bg = new StartScreen();

        bg.setLayout(new FlowLayout());

        this.gc = new GameCanvas();

        this.jb = new JButton("Start");
        

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jb.setVisible(false);
                bg.setVisible(false);
                gc.addKeyBindings();
                gc.startClickTimer();
                gc.startRepaintTimer();
                jf.add(gc);
            }
            
        });

        bg.setLocation(440, 5);
        bg.add(jb);
        jf.add(bg);

        this.playerID = 0;
        this.player = null;
        this.ropeSpeed = 0;
    }

    public void setUpGameFrame() {
        jf.setTitle("Type of War | Player #" + playerID);
        jf.setSize(960, 540);
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
            rfsRunnable.waitForStartMessage();

            Thread read = new Thread(rfsRunnable);
            Thread write = new Thread(wtsRunnable);

            read.start();
            write.start();

            if(RopeAssembly.getWinner() == 1) {
                EndScreen endScreen = new EndScreen();
                System.out.println("Player 1 wins!");
                read.sleep(10000);
                write.sleep(10000);
                endScreen.setVisible(true);

            } else if(RopeAssembly.getWinner() == 2) {
                EndScreen endScreen = new EndScreen();
                System.out.println("Player 2 wins!");
                read.sleep(10000);
                write.sleep(10000);
                endScreen.setVisible(true);

            } else {
                System.out.println("No winner yet.");
            }

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
                    if (player != null) ropeSpeed = in.readInt();
                    System.out.println("Rope Speed: " + ropeSpeed); //DEBUG
                }
            } catch (IOException e) {
                System.out.println("RFS Run failed.");
            }
        }

        public void waitForStartMessage() {
             try {
                String startMsg = in.readUTF();
                System.out.println("Message from server: " + startMsg); 
             } catch (IOException e) {
                System.out.println("IO Exception from waitForStartMessage");
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
                    if (player != null) {
                        int clicks = gc.getClicks();
                        player.calculateSpeed(clicks);

                        out.writeInt(player.getSpeed());
                        out.flush();
                    }
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                    }
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
}

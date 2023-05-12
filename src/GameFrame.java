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
GameFrame.java handles all the features that happen within frame.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameFrame {

    JFrame gameUI;
    JLabel title;
    JButton start;
    GameCanvas gc;

    public GameFrame(){

        /* INITIALISING OF VARIABLES */
        gameUI = new JFrame("Type of War");
        title = new JLabel("Are you ready to Type for War?");
        start = new JButton("Start?");
        gc = new GameCanvas();

        /* SPECIFIC DETAILING OF THE VARIABLES */
        gameUI.setSize(1000,600);
        gameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameUI.setLayout(new FlowLayout());

        /* ADDING FEATURES INTO FRAME */
        gameUI.add(title);
        gameUI.add(start);
        gameUI.add(gc);
        gc.setVisible(false); // Hide the game stuff muna until the player STARTS
        
        /* SET VISIBLE */
        gameUI.setLocationRelativeTo(null);
        gameUI.setVisible(true);
        gameUI.setResizable(false);
        
        /* FRAME FEATURES */
        
        // if Start button is clicked, it makes the button and title invisible, then displays the GameCanvas.
        start.addActionListener(e -> {
            start.setVisible(false);
            title.setVisible(false);
            gc.setVisible(true);
            gc.addKeyBindings();

        });
    }

    public GameCanvas getCanvas() {
        return gc;
    }
}



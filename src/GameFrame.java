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

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class GameFrame {

    public GameFrame(){

        /* INITIALISING OF VARIABLES */
        JFrame gameUI = new JFrame("T.o.W");
        JLabel title = new JLabel("Are you ready to Type for War?");
        JButton start = new JButton("Start?");

        /* SPECIFIC DETAILING OF THE VARIABLES */
        gameUI.setSize(1920,1080);
        gameUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameUI.setLayout(new FlowLayout());

        /* ADDING FEATURES INTO FRAME */
        gameUI.add(title);
        gameUI.add(start);

        /* SET VISIBLE */
        gameUI.setVisible(true);
        gameUI.setResizable(false);

        /* FRAME FEATURES */

        //if Start button is clicked, it makes the button invisible and the title invisible
        start.addActionListener(e -> {
            start.setVisible(false);
            title.setVisible(false);
        });
        }

        //TODO Add game graphics. Rope, Player Icons, Background, etc. Must be taken from GameCanvas, right?
    }


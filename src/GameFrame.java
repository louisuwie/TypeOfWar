import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class GameFrame {

    public GameFrame(){

        /*
        INITIALISING OF VARIABLES
         */
        JFrame gameUI = new JFrame("Type Of War");

        JLabel title = new JLabel("Are you ready, to Type for War?");

        JButton start = new JButton("Start?");

        /*
        ADDING DETAILS TO VARIABLES
         */
        gameUI.setSize(1920,1080);
        gameUI.setDefaultCloseOperation(EXIT_ON_CLOSE);


        /*
        SET VISIBLE
         */
        gameUI.setVisible(true);
    }
}

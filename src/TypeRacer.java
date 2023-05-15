import javax.swing.*;

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

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class TypeRacer extends JFrame {

    private JTextField textField;
    private JLabel typeThis;
    boolean isCorrect = false;

    public void initialize() {
        // create a JFrame
        setTitle("TypeRacer");
        setLayout(null);
        setSize(300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);

        //Make the JFrame have a background image
        setContentPane(new JLabel(new ImageIcon("E:\\_GitHub\\TEST\\src\\typeRacer_BACKGROUND.jpg")));

        //Imports the list of words from the file and selects a random word.
        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("typeRacer_LIST.txt")); //Reads the txt file.
            String line; //Takes in the current word.

            while ((line = reader.readLine()) != null) {
                words.add(line); //Current word added to the arraylist of words.
            }
            reader.close(); //Closes the reader once all words have been imported.
        } catch (Exception e) {
            e.printStackTrace();
        }
        String randomWord = words.get((int) (Math.random() * words.size())); //Selects a random word.

        // create a JTextField and Textfield
        typeThis = new JLabel("Type this: " + randomWord);
        textField = new JTextField();
        textField.setSize(100,20);

        //Manual Positioning of Button and Textfield
        typeThis.setBounds(10,10,300,20);
        textField.setBounds(10,40,100,20);

        //Adding to the JFrame
        add(textField);
        add(typeThis);

        System.out.println("Random word: " + randomWord);

        // add a DocumentListener to the JTextField to check its length
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                // check if the JTextField's word is equal to the target randomWord
                for (int i = 0; i < textField.getText().length(); i++) {
                    if (textField.getText().charAt(i) == randomWord.charAt(i)) {
                        if (textField.getText().equals(randomWord)) {
                            isCorrect = true;
                            System.out.println("Correct!");
                            dispose();
                        }

                    } else {
                        isCorrect = false;
                        System.out.println("Incorrect!");
                    }
                }
            }

            @Override

            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        // make the JFrame visible
        setVisible(true);
    }
}

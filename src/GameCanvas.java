import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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

public class GameCanvas extends JComponent{

        // Graphics-side code here, maybe import all the graphics-related files such as img, sound, gif, etc.
		public GameCanvas() {
			setPreferredSize(new Dimension(1000,1000)); // Numbers are placeholders!
			setFocusable(true);
		}


		// Call this to start the key bindings
		public void addKeyBindings() {
			ActionMap am = getActionMap();
			InputMap im = getInputMap();

			// Define the Abstract Actions
			AbstractAction sc = new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					// TODO Code that is executed upon pressing the spacebar;
				}
			};

			// Creating the Action
			am.put("Spacebar Press", sc);
			im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "Spacebar Press");
        }
}

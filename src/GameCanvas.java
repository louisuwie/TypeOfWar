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
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JComponent{

	ImageIcon background;
	JLabel label;
	JPanel jp = new JPanel();
	GridLayout gl = new GridLayout(1, 2);

	// Graphics-side code here, maybe import all the graphics-related files such as img, sound, gif, etc.
	public GameCanvas() {
		setPreferredSize(new Dimension(1000, 600));
		setFocusable(true);
		background = new ImageIcon("ToW_Background.jpeg"); // Placeholder! Will fix the file path rin soon... 
		label = new JLabel(background);
		setLayout(new BorderLayout()); // Para easier for us to layout stuff in the future!
		add(label, BorderLayout.CENTER);
	}

	@Override
    protected void paintComponent(Graphics g2d) {
		// Just some tester graphics!
		g2d.setColor(Color.PINK);
        g2d.fillRect(500,500, 100, 100);
    }

	public void addKeyBindings() {
		ActionMap am = getActionMap();
		InputMap im = getInputMap();

		// Define the Abstract Actions
		AbstractAction sc = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Keybindings work");
			}
		};

		// Creating the Action
		am.put("Spacebar Press", sc);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "Spacebar Press");
	}
}

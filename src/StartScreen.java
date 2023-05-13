


import java.awt.*;
import javax.swing.*;

public class StartScreen extends JPanel{
    
    JButton startButton;
    Image openingScreen;
    boolean isStart;

    public StartScreen() {
        this.openingScreen = new ImageIcon("DesignAssets/Opening.png").getImage();
        setPreferredSize(new Dimension(960, 540));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // Cast into a g2d Object
        g2d.drawImage(openingScreen, 0, 0,960, 540, null);
    }

    public boolean isStart() {
        return isStart;
    }

    public void addButton() {
        add(startButton);
    }

}

import java.awt.*;

import javax.swing.ImageIcon;

public class RopeAssembly {
    

    Image gr, p1w, p2w, ss;
    Image current;
    int x, w, h;
    static int y = 270;

    // Fields are not final. This is where we import the Rope Assembly Graphics

    public RopeAssembly() {
        
        gr = new ImageIcon("DesignAssets/GetReady.PNG").getImage();
        current = gr;

        p1w = new ImageIcon("DesignAssets/P1Win.PNG").getImage();
        p2w = new ImageIcon("DesignAssets/P2Win.PNG").getImage();
        ss = new ImageIcon("DesignAssets/SameSpeed.png").getImage();

        x = 200;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(current, x, y, 600, 200, null);
    }

    public void tug(int v) {
        x += v;
    }

    public void resetSprite(int vel) {
        if (vel == 0) {
            current = ss;
        } else if (vel < 0) {
            current = p1w;
        } else if (vel > 0) {
            current = p2w;
        }
    }


}

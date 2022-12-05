import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(Image img) {
        this.img = img;
        int width = 600;
        int height = 340;
        setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
        setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
        setLayout(null);
    }

    public void paintComponent(Graphics g)  {
        g.drawImage(img, 3, 0, null);
    }
}
package screensaver;


import javax.swing.*;
import java.awt.*;
import java.util.Random;
@org.springframework.stereotype.Component

public abstract class ColorFrame extends JFrame {

    public ColorFrame() throws HeadlessException {
        setSize(200,200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public void showOnRandomPlace(){
        Random random = new Random();
        setLocation(random.nextInt(1000),random.nextInt(900));
        getContentPane().setBackground(getColor());
        repaint();
    }

    protected abstract Color getColor();
}

package GUI;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JPanel {
    int height;
    int width;
    public UIPanel(int width, int height){
        this.width = width;
        this.height = height;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBounds(0,0, width, height);
        setBackground(new Color(187, 187, 187));
    }
}

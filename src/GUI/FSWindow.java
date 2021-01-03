package GUI;

import javax.swing.*;
import java.awt.*;

public class FSWindow extends JFrame {
    public FSWindow(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = env.getMaximumWindowBounds();
        System.out.println("Height = " + bounds.height  + ", Width = " +bounds.width);
        setSize(bounds.width, bounds.height);
        //====Agregare aqui los paneles====
        UIPanel UIp = new UIPanel(bounds.width, 200);
        //=================================
        add(UIp, BorderLayout.CENTER);
        this.setVisible(true);


    }
}

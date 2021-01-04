package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Compiler.*;

public class FSWindow extends JFrame implements ActionListener {
    Rectangle bounds;
    JPanel container;
    private UIPanel uip;
    private FSPanel fsp;
    private JScrollPane jsp;
    public FSWindow(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        bounds = env.getMaximumWindowBounds();
        //System.out.println("Height = " + bounds.height  + ", Width = " +bounds.width);
        setSize(bounds.width, bounds.height);

        /*Container panel*/
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        /*Search Panel*/
        uip = new UIPanel();
        uip.setSize(bounds.width, 100);
        uip.setBackground(new Color(222, 222, 222));
        JButton genfc = new JButton("Generar Diagrama de Flujo");
        genfc.setActionCommand("genfc");
        genfc.addActionListener(this);
        uip.add(genfc);

        /*FlowChartPanel*/

        fsp = new FSPanel();
        //fsp.setPreferredSize(new Dimension(bounds.width, bounds.height));
        fsp.setBackground(new Color(255, 255, 255));
        jsp = new JScrollPane(fsp);
        jsp.setPreferredSize(new Dimension(bounds.width, bounds.height - 100));
        /*Add panels*/
        container.add(uip);
        container.add(jsp);
        add(container);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if("genfc".equals(e.getActionCommand())){
            PseudoCompiler pc = new PseudoCompiler();
            fsp.setPreferredSize(new Dimension(bounds.width, pc.getSymbols().size() * 105));
            fsp.setSymbols(pc.getSymbols());
            //uip.repaint();
            fsp.revalidate();
            fsp.repaint();

            container.validate();
            //fsp.paintComponent(getGraphics());
        }
    }
}

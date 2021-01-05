package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    private String filename;
    private static final String workingDirectory = System.getProperty("user.dir");
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

        JButton chosebtn = new JButton("buscar archivo");
        chosebtn.setActionCommand("chosefile");
        chosebtn.addActionListener(this);
        uip.add(chosebtn);

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
        if("genfc".equals(e.getActionCommand()) && !filename.isEmpty()){
            PseudoCompiler pc = new PseudoCompiler(filename);
            fsp.setPreferredSize(new Dimension(bounds.width, pc.getSymbols().size() * 105));
            fsp.setSymbols(pc.getSymbols());
            fsp.revalidate();
            fsp.repaint();
            container.validate();
        }else if("chosefile".equals(e.getActionCommand())){
            JFileChooser chooser = new JFileChooser(workingDirectory);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    ".txt and .alg text files", "txt", "alg"
            );
            int returval = chooser.showOpenDialog(this);
            if(returval == JFileChooser.APPROVE_OPTION){
                filename = chooser.getSelectedFile().getName();
            }
        }
    }
}

package FlowchartSymbol;

import java.awt.*;

public class Start extends Symbol{
    public Start(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
    }
    @Override
    public void DrawSymbol(Graphics g) {
        g.drawOval(x , y, width, height);
        g.drawString(instruccion, x + (width / 3), y + (height / 3));
    }
}

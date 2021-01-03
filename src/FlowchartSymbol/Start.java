package FlowchartSymbol;

import java.awt.*;

public class Start extends Symbol{
    public Start(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
    }
    @Override
    void DrawSymbol(Graphics g) {
        g.drawOval(x, y, width, height);
        g.drawString(instruccion, x + 20, y + (height / 3));
    }
}

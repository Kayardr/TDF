package FlowchartSymbol;

import java.awt.*;

public class Process extends Symbol{

    public Process(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
    }
    @Override
    void DrawSymbol(Graphics g) {
        g.drawRect(x, y, width, height);
        //g.drawString(parametros, x + 20, y + (height / 3));
    }
}

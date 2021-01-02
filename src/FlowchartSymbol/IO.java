package FlowchartSymbol;

import java.awt.*;

public class IO extends Symbol{
    public IO(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
    }
    @Override
    void DrawSymbol(Graphics g) {
        g.drawRect(x, y, width, height);
        //g.drawString(parametros, x + 20, y + (height / 3));
    }
}

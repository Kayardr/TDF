package FlowchartSymbol;

import java.awt.*;

public class IO extends Symbol{
    public IO(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
    }
    @Override
    void DrawSymbol(Graphics g) {
        int[] xs = {x + 20, x + width + 20 , x + width, x};
        int[] ys = {y, y, y + height, y + height};
        g.drawPolygon(xs, ys, 4);
        g.drawString(parametros, x + 20, y + (height / 3));
    }
}

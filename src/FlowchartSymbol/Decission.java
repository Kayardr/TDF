package FlowchartSymbol;

import java.awt.*;

public class Decission extends Symbol{
    public Decission(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
    }
    @Override
    void DrawSymbol(Graphics g) {
        int[] xs = {x + (width / 2), x + width , x + (width / 2), x};
        int[] ys = {y, y + (height / 2), y + height, y + (height / 2)};
        g.drawPolygon(xs, ys, 4);
        g.drawString(parametros, x + (width / 3), y + (height / 2));
    }
}

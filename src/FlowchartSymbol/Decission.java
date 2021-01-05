package FlowchartSymbol;

import java.awt.*;

public class Decission extends Symbol{

    public Decission(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
    }

    public Decission(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso, int nesteds) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
        this.nesteds = nesteds;
    }
    @Override
    public void DrawSymbol(Graphics g) {
        int[] xs = {x + (width / 2), x + width , x + (width / 2), x};
        int[] ys = {y, y + (height / 2), y + height, y + (height / 2)};
        int steps = saltofalso - linea;
        g.drawPolygon(xs, ys, 4);
        g.drawString(parametros, x + (width / 4), y + (height / 2));

        Arrow.draw(x + (width /2), y + height, x + (width /2), y + height + 50, 0, g);
        g.drawLine(x + width, y + (height / 2), x + width + 50 +(50*nesteds), y + (height/2));
        g.drawLine(x + width + 50 +(50*nesteds), y + (height/2), x + width + 50 +(50*nesteds), y + (steps * 100) + 15);
        Arrow.draw(x + width + 50 +(50*nesteds), y + (steps * 100) + 15, x + width, y + (steps * 100) + 15, 1, g);
    }
}

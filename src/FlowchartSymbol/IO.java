package FlowchartSymbol;

import java.awt.*;

public class IO extends Symbol{
    public IO(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
    }
    @Override
    public void DrawSymbol(Graphics g) {
        int[] xs = {x + 20, x + width + 20 , x + width, x};
        int[] ys = {y, y, y + height, y + height};
        g.drawPolygon(xs, ys, 4);
        g.drawString(parametros, x + (width / 4), y + (height / 3));
        if(saltoverdad < linea){
            //Dibujar flecha hacia arriba
        }else{
            Arrow.draw(x + (width /2), y + height, x + (width /2), y + height + 50, 0, g);
        }
    }
}

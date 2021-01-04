package FlowchartSymbol;

import java.awt.*;

public class Process extends Symbol{

    public Process(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        super(linea, instruccion, parametros, saltoverdad, saltofalso);
    }
    @Override
    public void DrawSymbol(Graphics g) {
        g.drawRect(x, y, width, height);
        g.drawString(parametros, x + (width / 4), y + (height / 3));
        if(saltoverdad < linea){
            //Dibujar flecha hacia arriba
        }else{
            Arrow.draw(x + (width /2), y + height, x + (width /2), y + height + 50, 0, g);
        }
    }
}

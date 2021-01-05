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
        int steps = linea - saltofalso;
        if(saltoverdad < linea){
            //Dibujar flecha hacia arriba
            g.drawLine(x, y + (height / 2), x - 50 - (50*nesteds), y + (height/2));
            g.drawLine(x - 50 - (50*nesteds), y + (height/2), x - 50 - (50*nesteds), y - (steps * 100) - 15);
            Arrow.draw(x - 50 - (50*nesteds), y - (steps * 100) - 15, x + (height / 2) , y - (steps * 100) - 15, 3, g);
        }else{
            Arrow.draw(x + (width /2), y + height, x + (width /2), y + height + 50, 0, g);
        }
    }
}

package FlowchartSymbol;

import java.awt.*;

public class Arrow {

    public static void draw(int x1, int y1, int x2, int y2, int orientation, Graphics g){
        g.drawLine(x1, y1, x2, y2);
        switch (orientation){
            case 0: g.drawLine(x2, y2, x2 - 5, y2 - 5);
                g.drawLine(x2, y2, x2 + 5, y2 - 5);
                break;
            case 1: g.drawLine(x2, y2, x2 + 5, y2 -5);
                g.drawLine(x2, y2, x2 + 5, y2 + 5);
                break;
            case 2:g.drawLine(x2, y2, x2 - 5, y2 + 5);
                g.drawLine(x2, y2, x2 + 5, y2 + 5);
                break;
            case 3:g.drawLine(x2, y2, x2 - 5, y2 - 5);
                g.drawLine(x2, y2, x2 - 5, y2 + 5);
                break;
        }
    }
}

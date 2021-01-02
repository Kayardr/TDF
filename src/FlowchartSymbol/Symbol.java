package FlowchartSymbol;

import java.awt.*;

public abstract class Symbol {
    int linea;                  //Indice de posicion de simbolo
    String instruccion;
    String parametros;        //Parametros que seran escritos en el simbolo (tal vez sea pertinente modificarlo para
                                // poner simplemente la instruccion como una cadena simple y no como un arreglo de instrucciones
    int saltoverdad;            //Esto ayuda a indicar el destino de la linea de flujo en caso de que la instruccion sea verdadera
    int saltofalso;             //Lo mismo que la anterior pero en caso de que sea falso
    int x, y, width, height;    //Parametros dedicados a posicionar el simbolo en el panel
    public Symbol(int linea, String instruccion, String parametros, int saltoverdad, int saltofalso) {
        this.linea = linea;
        this.instruccion = instruccion;
        this.parametros = parametros;
        this.saltoverdad = saltoverdad;
        this.saltofalso = saltofalso;

        y = (linea * 100) + 50;     //Calcula la coordenada y para dibujar el simbolo
    }

    abstract void DrawSymbol(Graphics g);   //Metodo abstracto que permite dibujar diferentes simbolos
}

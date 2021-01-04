package GUI;

import FlowchartSymbol.Symbol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FSPanel extends JPanel {
    ArrayList<Symbol> symbols;
    FSPanel(){
        this.symbols = new ArrayList<Symbol>();
    }

    FSPanel(ArrayList<Symbol> symbols){
        this.symbols = symbols;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(symbols.size() > 0){
            for (Symbol s: symbols){
                s.DrawSymbol(g);
            }

        }

    }

    public void setSymbols(ArrayList<Symbol> symbols){
        this.symbols = symbols;
    }
}

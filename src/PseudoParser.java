import java.util.ArrayList;
import java.util.Arrays;

public class PseudoParser {
    ArrayList<PseudoLexer.Token> tokens;
    int  tokenIndex = 0;
    ArrayList<Tuplo> tuplos;
    boolean comparacion;
    PseudoParser(){
        tuplos = new ArrayList<>();
    }
    public boolean parse(ArrayList<PseudoLexer.Token> tokens){
        this.tokens = tokens;
        System.out.println("\n\n ********** Reglas de produccion ********** \n\n");
        return programa();
    }

    private boolean token(String name){
        if(tokens.get(tokenIndex).type.name().equals(name)){
            System.out.println(tokens.get(tokenIndex).type.name() + " "
                +tokens.get(tokenIndex).data);
            tokenIndex++;
            return true;
        }
        return false;
    }

    private boolean programa(){
        //System.out.println("<Programa> --> inicio-programa <Enunciados> fin-programa");
        if(token("INICIOPROGRAMA"))
            if(enunciados())
                if(token("FINPROGRAMA")){
                    if(tokenIndex == tokens.size()){
                        tuplos.remove(tuplos.size()-1);
                        tuplos.get(tuplos.size()-1).instruccion = "fin";
                        tuplos.get(tuplos.size()-1).parametros = new String[] {""};
                        tuplos.get(tuplos.size()-1).saltoverdad = -1;
                        tuplos.get(tuplos.size()-1).saltofalso = -1;
                        return true;
                    }

                }

        return false;
    }

    private boolean enunciados(){
        //System.out.println("<Enunciados> --> <Enunciados> <Enunciados> | <Enunciados>");
        int tokenIndexAux = tokenIndex;
        if(enunciado()){
            if (enunciados()){
                return true;
            }
        }
        if(comparacion == true){
            comparacion = false;
            return true;
        }
        tokenIndex = tokenIndexAux;
        if(enunciado())
            return true;
        return false;
    }

    private boolean enunciado(){
        //System.out.println("<Enunciado> --> <Asignación> | <Leer> | <Escribir> | <Si> | <Mientras>");

        if (enunciadoAsignacion()){
            System.out.println("<Enunciado> --> <Asignacion>");
            return true;
        }
        if (enunciadoLeer()){
            System.out.println("<Enunciado> --> <Leer>");
            return true;
        }
        if (enunciadoEscribir()){
            System.out.println("<Enunciado> --> <Escribir>");
            return true;
        }
        if (enunciadoSi()){
            System.out.println("<Enunciado> --> <Si>");
            return true;
        }
        if (enunciadoMientras()){
            System.out.println("<Enunciado> --> <Mientras>");
            return true;
        }
        if (enunciadoRepite()){
            System.out.println("<Enunciado> --> <Repite>");
            return true;
        }
        return false;
    }

    private boolean enunciadoAsignacion(){
        //System.out.println("<Asignacion> --> <Variable> = <Expesion>");
        int beginIndex = tokenIndex;
        if(token("VARIABLE"))
            if(token("IGUAL"))
                if(expresion()){
                    String parameters[] = new String[tokenIndex-beginIndex-1];
                    parameters[0] = tokens.get(beginIndex).data;
                    int i = beginIndex + 2;
                    while (i < tokenIndex){
                        parameters[i-beginIndex-1]=tokens.get(i).data;
                        i++;
                    }
                    Tuplo t = new Tuplo(tuplos.size(), "asignacion", parameters, tuplos.size()+1, tuplos.size()+1);
                    tuplos.add(t);
                    return true;
                }
        return false;
    }
    private boolean expresion(){
        //System.out.println("<Expresion> --> <Operacion> | <Valor>");
        if(operacion())
            return true;
        if(valor())
            return  true;
        return false;
    }

    private boolean valor(){
        //System.out.println("<Valor> --> <Variable> | <Nuero>");
        if(token("VARIABLE"))
            return true;
        if(token("NUMERO"))
            return true;
        return false;
    }

    private boolean operacion(){
        //System.out.println("<Operacion> --> <Valor> <Operador-Aritmetico> <Valor>");
        int tokenIndexAux = tokenIndex;
        if(valor())
            if (token("OPARITMETICO"))
                if(valor())
                    return true;

        tokenIndex = tokenIndexAux;
        return false;
    }

    private boolean enunciadoLeer(){
        //System.out.println("<Leer> --> <Cadena>, <Variable>");
        if(token("LEER"))
            if(token("CADENA"))
                if(token("COMA"))
                    if(token("VARIABLE")){
                        String parameters[] = new String[2];
                        parameters[0] = tokens.get(tokenIndex-3).data;
                        parameters[1] = tokens.get(tokenIndex-1).data;
                        Tuplo t = new Tuplo(tuplos.size(), "leer", parameters, tuplos.size()+1, tuplos.size()+1);
                        tuplos.add(t);
                        return true;
                    }

        return false;
    }

    private boolean enunciadoEscribir(){
        //System.out.println("<Escribir> --> escribir <Cadena> | escribir <Cadena>, <Variable>");
        int tokenIndexAux = tokenIndex;

        if(token("ESCRIBIR"))
            if (token("CADENA"))
                if(token("COMA"))
                    if(token("VARIABLE")){
                        String parameters[] = new String[2];
                        parameters[0] = tokens.get(tokenIndex-3).data;
                        parameters[1] = tokens.get(tokenIndex-1).data;

                        Tuplo t = new Tuplo(tuplos.size(), "escribir", parameters, tuplos.size()+1, tuplos.size()+1);
                        tuplos.add(t);
                        return true;
                    }
        tokenIndex = tokenIndexAux;
        if(token("ESCRIBIR"))
            if(token("CADENA")){
                String parameters[] = new String[1];
                parameters[0] = tokens.get(tokenIndex-1).data;
                Tuplo t = new Tuplo(tuplos.size(), "escribir", parameters, tuplos.size()+1, tuplos.size()+1);
                tuplos.add(t);
                return true;
            }

        return false;
    }

    private boolean enunciadoSi(){
        //System.out.println("<SI> --> si <Comparacion> entonces <Enunciados> fin-si");
        if(token("SI")){
            int tuploAux = tuplos.size();
            if(comparacion()){
                comparacion = true;


                if(token("ENTONCES")){
                    if(enunciados()){
                        int tokenIndexAux = tokenIndex;
                        if(token("FINSI")){
                            tuplos.get(tuploAux).saltofalso = tuplos.size() + (tokenIndex - tokenIndexAux) - 1;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean comparacion(){
        //System.out.println("<Comparacion> --> (<Valor> <Operador-relacional> <Valor>)");

        if(token("PARENTESISIZQ"))
            if(valor())
                if(token("OPERACIONAL"))
                    if(valor())
                        if(token("PARENTESISDER")){
                            String parameters[] = new String[3];
                            parameters[0] = tokens.get(tokenIndex-4).data;
                            parameters[1] = tokens.get(tokenIndex-3).data;
                            parameters[2] = tokens.get(tokenIndex-2).data;
                            Tuplo t = new Tuplo(tuplos.size(), "comparacion", parameters, tuplos.size()+1, tuplos.size()+1);
                            tuplos.add(t);
                            return true;
                        }

        return false;
    }

    private boolean enunciadoMientras(){
        //System.out.println("<Mientras> --> mientras <Comparacion> <Enunciados> fin-mientras");

        if(token("MIENTRAS")){
            int tuploAux = tuplos.size();
            if(comparacion()){
                comparacion = true;
                if (enunciados()){
                    int tokenIndexAux = tokenIndex;
                    if(token("FINMIENTRAS")) {
                        tuplos.get(tuploAux).saltofalso = tuplos.size() + (tokenIndex - tokenIndexAux) - 1;
                        tuplos.get(tuplos.size() - 1).saltoverdad = tuploAux;
                        tuplos.get(tuplos.size() - 1).saltofalso = tuploAux;
                        return true;
                    }
                }

            }

        }

        return false;
    }

    private boolean enunciadoRepite(){
        if(token("REPITE"))
            if(token("PARENTESISIZQ"))
                if(valor())
                    if(token("OPERACIONAL"))
                        if(valor())
                            if(token("COMA"))
                                if(valor())
                                    if(token("PARENTESISDER"))
                                        if(enunciados())
                                            if(token("FINREPITE"))
                                                return true;
        return false;
    }

    public class Tuplo{
        int linea;
        String instruccion;
        String[] parametros;
        int saltoverdad;
        int saltofalso;

        public Tuplo(int linea, String instruccion, String[] parameters, int saltoverdad, int saltofalso) {
            this.linea = linea;
            this.instruccion = instruccion;
            this.parametros = parameters;
            this.saltoverdad = saltoverdad;
            this.saltofalso = saltofalso;
        }

        public void PrintTupla(){
            System.out.printf(
                    "(" + (linea + 1) + "," + instruccion + ',' + Arrays.toString(parametros) + ',' + (saltoverdad + 1) + ',' + (saltofalso + 1) + ')' + '\n');
        }
    }
}

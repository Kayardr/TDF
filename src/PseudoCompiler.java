import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PseudoCompiler {
    PseudoLexer pLexer;
    PseudoParser pParser;

    public PseudoCompiler(){
        pLexer = new PseudoLexer();
        String input = "";

        try{
            FileReader reader = new FileReader("prom.alg");
            int character;

            while ((character = reader.read()) != -1){
                input += (char) character;
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        ArrayList<PseudoLexer.Token> tokens = pLexer.getTokens(input);

        pParser = new PseudoParser();
        System.out.println("\nSintaxis correcta: " + pParser.parse(tokens));


        System.out.println(pParser.symbols.size());
        for (int i = 0; i < pParser.symbols.size(); i++){
            pParser.symbols.get(i).PrintInstruction();
        }

    }
}

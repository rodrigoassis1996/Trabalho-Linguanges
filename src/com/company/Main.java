package com.company;

/**
 * @author Rodrigo Assis
 * Classe principal
 */

public class Main {

    public static void main(String[] args) {
        String nomeArquivo = "./source.txt";
        if(args.length == 1) {
            nomeArquivo = args[0];
        }

        // Abre o arquivo
        IteradorArquivo iteradorArquivo =
                new IteradorArquivo(nomeArquivo);
        Automato automato = new Automato(iteradorArquivo);

        TokenLexema tl = null;
        while (iteradorArquivo.isPossuiProximoToken()) {
            tl = automato.recuperarProximoTokenLexema();
            System.out.println(tl);
        }
    }
}

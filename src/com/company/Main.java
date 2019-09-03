package com.company;

import com.company.regras.S;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Assis
 * Classe principal
 */

public class Main {

    public static void main(String[] args) {
        List<TokenLexema> lista = new ArrayList<>();
        String nomeArquivo = "./source.txt";
        if(args.length == 1) {
            nomeArquivo = args[0];
        }

        // Abre o arquivo
        IteradorArquivo iteradorArquivo =
                new IteradorArquivo(nomeArquivo);
        Automato automato = new Automato(iteradorArquivo);

        //Recupera todos os tokens
        while (iteradorArquivo.isPossuiProximoToken()) {
            TokenLexema tl = automato.recuperarProximoTokenLexema();
            if(!TipoToken.DESCARTAR.equals(tl.getTipoToken())) {
                lista.add(tl);
            }
        }

        S s = new S();
        s.reconher(lista, 0);
    }
}

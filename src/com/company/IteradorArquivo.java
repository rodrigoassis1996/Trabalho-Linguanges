package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Assis realiza iteração no arquivo
 */
public class IteradorArquivo {

    private int indiceAtual;
    private List<Character> list;

    /**
     * Construtor
     *
     * @param nomeArquivo
     *            {@link String}
     */

    public IteradorArquivo(String nomeArquivo) {
        // O índice atual está 'anterior' ao início do arquivo
        this.indiceAtual = -1;

        try {
            Charset encoding = Charset.defaultCharset();
            File f = new File(nomeArquivo);
            InputStream in = new FileInputStream(f);
            Reader reader = new InputStreamReader(in, encoding);
            list = new ArrayList<>();
            Reader buffer = new BufferedReader(reader);
            int r = 0;
            // -1 significa que chegou ao final do arquivo
            while ((r = reader.read()) != -1) {
                char c = (char)r;
                list.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    /**
     * Retorna o próximo caractere (ou null caso tenha
     * acabado).
     * @return {@link Character} ou <code>null</code>.
     */
    public Character proximoCaracter() {
        if(isPossuiProximoToken()) {
            this.indiceAtual ++;
            char c = list.get(indiceAtual);
            //System.out.println(this.indiceAtual + " " + c);
            return c;
        }
        return null;
    }

    /**
     * @return <code>true</code> se houver
     * próximo token a ser lido
     */
    public boolean isPossuiProximoToken() {
        return (indiceAtual + 1) < list.size();
    }

    /**
     * Volta o autômato 1 posição.
     */
    public void voltar1Posicao() {
        this.indiceAtual --;
        // System.out.println(this.indiceAtual);
    }

}

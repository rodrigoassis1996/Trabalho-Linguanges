package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rodrigo Assis
 * Representa um estado do autômato
 */

public class Estado {

    private Map<Character, Estado> transicao;
    private boolean marcado;
    private int identificador;

    /**
     * Construtor padrão
     */
    public Estado(boolean marcado, int identificador) {
        this.marcado = marcado;
        this.identificador = identificador;
        this.transicao = new HashMap<>();
    }

    /**
     * Método responsável por adicionar uma transição.
     * @param c {@link Character}
     * @param estado {@link Estado}
     */
    public void adicionarTransicao(Character c, Estado estado) {
        transicao.put(c, estado);
    }


    /**
     * Método responsável por obter o próximo estado a partir
     * do caractere apresentado.
     * @param c {@link Character}
     * @return {@link Estado} ou <code>null</code> caso não seja
     * encontrado transição correspondente.
     */
    public Estado obterProximoEstado(Character c) {
        return transicao.get(c);
    }


    @Override
    public String toString() {
        return "S" + identificador;
    }

    /**
     * @return the marcado
     */
    public boolean isMarcado() {
        return marcado;
    }

}

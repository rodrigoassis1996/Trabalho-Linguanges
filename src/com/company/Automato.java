package com.company;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Rodrigo Assis
 * Representa um autômato.
 */
public class Automato {

    private Estado s0;
    private IteradorArquivo iteradorArquivo;
    private Map<Estado, TipoToken> tabelaSimbolos;

    /**
     * Construtor.
     */
    public Automato(IteradorArquivo iteradorArquivo) {
        this.iteradorArquivo = iteradorArquivo;
        this.tabelaSimbolos = new HashMap<>();

        // estado inicial
        s0 = new Estado(false, 0);

        s0.adicionarTransicao(' ', s0);
        s0.adicionarTransicao('\t', s0);
        s0.adicionarTransicao('\n', s0);
        s0.adicionarTransicao('\r', s0);

        // criando as transições para reconhecer a
        // palavra program
        criarTransicoesPalavra01();

        // criando as transições para reconhecer a
        // palavra begin
        criarTransicoesPalavra02();
    }

    public TokenLexema recuperarProximoTokenLexema() {
        TokenLexema tokenLexema = null;
        String lexema = "";
        //Coloca o estado atual como inicial
        Estado estadoAtual = s0;

        // Realiza a leitura de arquivo enquanto (nessa ordem!!!):
        // 01) enquando houver estado válido
        // 02) não estiver em um estado marcado
        // 03) não acabar o arquivo
        Character c = ' ';
        while (estadoAtual != null &&
                !estadoAtual.isMarcado() &&
                (c = iteradorArquivo.proximoCaracter()) != null){

            // tenta ir para o próximo estado
            estadoAtual = estadoAtual.obterProximoEstado(c);
            //System.out.println(estadoAtual);

            if(!s0.equals(estadoAtual)) {
                // adiciona o caractere ao lexema atual
                // apenas se o estado atual for diferente
                // do estado inicial e
                // diferente de estado marcado
                lexema = lexema + c;

            }
        }

        // caso o estado atual seja nulo
        // significa que parou em uma palavra
        // não reconhecida
        if(estadoAtual == null) {
            tokenLexema = new TokenLexema(TipoToken.NAO_RECONHECIDA, lexema);
        }else {
            // Caso o estado atual seja DIFERENTE
            // de nulo há 3 possibilidades:
            // 1: parou em um estado marcado
            // 2: parou no estado inicial
            // 3: parou em um estado NÃO final


            if(estadoAtual.isMarcado()) {
                // Caso 01
                // Caso esteja em um estado marcado
                // temos que recuperar qual o
                // o token daquele lexema e pedir para o iterador
                // voltar 1 casa

                // Vamos pedir para a tabela de símbolos
                // o tipo do token atual
                TipoToken tipoToken = tabelaSimbolos.get(estadoAtual);
                tokenLexema = new TokenLexema(tipoToken, lexema);

            }else if(s0.equals(estadoAtual)){
                // Caso 02
                // Caso seja o estado inicial, pode desconsiderar
                tokenLexema = new TokenLexema(TipoToken.DESCARTAR, "");
            } else {
                // Caso 03
                // Caso NÃO seja um estado marcado nem inicial
                tokenLexema = new TokenLexema(TipoToken.NAO_RECONHECIDA, lexema);
            }
        }

        return tokenLexema;
    }

    private void criarTransicoesPalavra01() {
        Estado s1 = criarEstadoTransicaoUnica( 1, s0, 'p', false);
        Estado s2 = criarEstadoTransicaoUnica( 2, s1, 'r', false);
        Estado s3 = criarEstadoTransicaoUnica( 3, s2, 'o', false);
        Estado s4 = criarEstadoTransicaoUnica( 4, s3, 'g', false);
        Estado s5 = criarEstadoTransicaoUnica( 5, s4, 'r', false);
        Estado s6 = criarEstadoTransicaoUnica( 6, s5, 'a', false);
        Estado s7 = criarEstadoTransicaoUnica( 7, s6, 'm', false);

        Estado s8 = new Estado(true, 8);
        s7.adicionarTransicao(' ', s8);
        s7.adicionarTransicao('\t', s8);
        s7.adicionarTransicao('\r', s8);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s8, TipoToken.PROGRAM);
    }

    private void criarTransicoesPalavra02() {
        Estado s9  = criarEstadoTransicaoUnica( 9, s0, 'b', false);
        Estado s10 = criarEstadoTransicaoUnica(10, s9, 'e', false);
        Estado s11 = criarEstadoTransicaoUnica(11,s10, 'g', false);
        Estado s12 = criarEstadoTransicaoUnica(12,s11, 'i', false);
        Estado s13 = criarEstadoTransicaoUnica(13,s12, 'n', false);

        Estado s8 = new Estado(true, 8);
        s13.adicionarTransicao(' ', s8);
        s13.adicionarTransicao('\t',s8);
        s13.adicionarTransicao('\r',s8);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s8, TipoToken.BEGIN);
    }

    private Estado criarEstadoTransicaoUnica(int identificador,
                                             Estado estadoAnterior, char caracterTransicao,
                                             boolean marcado) {
        Estado s = new Estado(marcado, identificador);
        estadoAnterior.adicionarTransicao(caracterTransicao, s);
        return s;
    }
}

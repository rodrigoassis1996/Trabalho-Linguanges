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

        // criando as transições para reconhecer a
        // palavra end
        criarTransicoesPalavra03();

        // criando as transições para reconhecer a
        // palavra if
        criarTransicoesPalavra04();

        // criando as transições para reconhecer a
        // palavra elseif
        criarTransicoesPalavra05();
    }

    public TokenLexema recuperarProximoTokenLexema() {
        TokenLexema tokenLexema = null;
        String lexema = "";
        //Coloca o estado atual como inicial
        Estado estadoAtual = s0;

        // O arquivo acabou
        if(!iteradorArquivo.isPossuiProximoToken()){
            return null;
        }

        // Realiza a leitura de arquivo enquanto (nessa ordem!!!):
        // 01) enquando houver estado válido
        // 02) năo estiver em um estado marcado
        // 03) năo acabar o arquivo
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
        // năo reconhecida
        if(estadoAtual == null) {
            tokenLexema = new TokenLexema(TipoToken.NAO_RECONHECIDA, lexema);
        }else {
            // Caso o estado atual seja DIFERENTE
            // de nulo há 3 possibilidades:
            // 1: parou em um estado marcado
            // 2: parou no estado inicial
            // 3: parou em um estado NĂO final


            if(estadoAtual.isMarcado()) {
                // Caso 01
                // Caso esteja em um estado marcado
                // temos que recuperar qual é
                // o token daquele lexema e pedir para o iterador
                // voltar 1 casa

                // Vamos pedir para a tabela de símbolos
                // o tipo do token atual
                TipoToken tipoToken = tabelaSimbolos.get(estadoAtual);
                tokenLexema = new TokenLexema(tipoToken, lexema.trim());

            }else if(s0.equals(estadoAtual)){
                // Caso 02
                // Caso seja o estado inicial, pode desconsiderar
                tokenLexema = new TokenLexema(TipoToken.DESCARTAR, "");
            } else {
                // Caso 03
                // Caso NĂO seja um estado marcado nem inicial
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

        Estado s14 = new Estado(true, 14);
        s13.adicionarTransicao(' ', s14);
        s13.adicionarTransicao('\t',s14);
        s13.adicionarTransicao('\r',s14);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s14, TipoToken.BEGIN);
    }

    private void criarTransicoesPalavra03() {
        Estado s15  = criarEstadoTransicaoUnica( 15, s0, 'e', false);
        Estado s16 = criarEstadoTransicaoUnica(16, s15, 'n', false);
        Estado s17 = criarEstadoTransicaoUnica(17,s16, 'd', false);

        Estado s18 = new Estado(true, 18);
        s17.adicionarTransicao(' ', s18);
        s17.adicionarTransicao('\t',s18);
        s17.adicionarTransicao('\r',s18);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s18, TipoToken.END);
    }

    private void criarTransicoesPalavra04() {
        Estado s19  = criarEstadoTransicaoUnica( 19, s0, 'i', false);
        Estado s20 = criarEstadoTransicaoUnica(16, s19, 'f', false);

        Estado s21 = new Estado(true, 21);
        s20.adicionarTransicao(' ', s21);
        s20.adicionarTransicao('\t',s21);
        s20.adicionarTransicao('\r',s21);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s21, TipoToken.IF);
    }

    private void criarTransicoesPalavra05() {
        Estado s22  = criarEstadoTransicaoUnica( 22, s0, 'e', false);
        Estado s23 = criarEstadoTransicaoUnica(23, s22, 'l', false);
        Estado s24 = criarEstadoTransicaoUnica(24, s23, 's', false);
        Estado s25 = criarEstadoTransicaoUnica(25, s24, 'e', false);
        Estado s26 = criarEstadoTransicaoUnica(26, s25, 'i', false);
        Estado s27 = criarEstadoTransicaoUnica(27, s26, 'f', false);

        Estado s28 = new Estado(true, 28);
        s27.adicionarTransicao(' ', s28);
        s27.adicionarTransicao('\t',s28);
        s27.adicionarTransicao('\r',s28);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s28, TipoToken.ELSEIF);
    }

    private Estado criarEstadoTransicaoUnica(int identificador,
                                             Estado estadoAnterior, char caracterTransicao,
                                             boolean marcado) {
        Estado s = new Estado(marcado, identificador);
        estadoAnterior.adicionarTransicao(caracterTransicao, s);
        return s;
    }
}

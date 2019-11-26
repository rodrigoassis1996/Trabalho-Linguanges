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
        // palavra partida | pmorto
        criarTransicoesPalavra01();

        // criando as transições para reconhecer a
        // palavra engatar | embreagem
        criarTransicoesPalavra02();

        // criando as transições para reconhecer a
        // palavra freio
        criarTransicoesPalavra03();

        // criando as transições para reconhecer a
        // palavra acelerador
        criarTransicoesPalavra04();

        // criando as transições para reconhecer a
        // palavra desligar
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

        if(TipoToken.NAO_RECONHECIDA.equals(tokenLexema.getTipoToken())){
            c = ' ';
            String lex = tokenLexema.getLexema();
            while ((c = iteradorArquivo.proximoCaracter()) != null &&
                    c != ' ' && c != '\n' && c != '\t' && c != '\r'){
                lex += c;

            }

        }

        return tokenLexema;
    }

    // partida | pmorto
    private void criarTransicoesPalavra01() {
        Estado s1 = criarEstadoTransicaoUnica( 1, s0, 'p', false);
        Estado s2 = criarEstadoTransicaoUnica( 2, s1, 'a', false);
        Estado s3 = criarEstadoTransicaoUnica( 3, s2, 'r', false);
        Estado s4 = criarEstadoTransicaoUnica( 4, s3, 't', false);
        Estado s5 = criarEstadoTransicaoUnica( 5, s4, 'i', false);
        Estado s6 = criarEstadoTransicaoUnica( 6, s5, 'd', false);
        Estado s7 = criarEstadoTransicaoUnica( 7, s6, 'a', false);

        Estado s8 = new Estado(true, 8);
        s7.adicionarTransicao(' ', s8);
        s7.adicionarTransicao('\t', s8);
        s7.adicionarTransicao('\r', s8);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s8, TipoToken.PARTIDA);

        Estado s52 = criarEstadoTransicaoUnica( 52, s1, 'm', false);
        Estado s53 = criarEstadoTransicaoUnica( 53, s52, 'o', false);
        Estado s54 = criarEstadoTransicaoUnica( 54, s53, 'r', false);
        Estado s55 = criarEstadoTransicaoUnica( 55, s54, 't', false);
        Estado s56 = criarEstadoTransicaoUnica( 56, s55, 'o', false);

        Estado s57 = new Estado(true, 57);
        s56.adicionarTransicao(' ', s57);
        s56.adicionarTransicao('\t', s57);
        s56.adicionarTransicao('\r', s57);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s57, TipoToken.PMORTO);
    }

    // engatar | embreagem
    private void criarTransicoesPalavra02() {
        Estado s9  = criarEstadoTransicaoUnica( 9, s0, 'e', false);
        Estado s10 = criarEstadoTransicaoUnica(10, s9, 'n', false);
        Estado s11 = criarEstadoTransicaoUnica(11,s10, 'g', false);
        Estado s12 = criarEstadoTransicaoUnica(12,s11, 'a', false);
        Estado s13 = criarEstadoTransicaoUnica(13,s12, 't', false);
        Estado s14 = criarEstadoTransicaoUnica(14,s13, 'a', false);
        Estado s15 = criarEstadoTransicaoUnica(15,s14, 'r', false);

        Estado s16 = new Estado(true, 16);
        s15.adicionarTransicao(' ', s16);
        s15.adicionarTransicao('\t',s16);
        s15.adicionarTransicao('\r',s16);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s16, TipoToken.ENGATAR);

        Estado s17 = criarEstadoTransicaoUnica(17, s9, 'm', false);
        Estado s18 = criarEstadoTransicaoUnica(18, s17, 'b', false);
        Estado s19 = criarEstadoTransicaoUnica(19, s18, 'r', false);
        Estado s20 = criarEstadoTransicaoUnica(20, s19, 'e', false);
        Estado s21 = criarEstadoTransicaoUnica(21, s20, 'a', false);
        Estado s22 = criarEstadoTransicaoUnica(22, s21, 'g', false);
        Estado s23 = criarEstadoTransicaoUnica(23, s22, 'e', false);
        Estado s24 = criarEstadoTransicaoUnica(24, s23, 'm', false);

        Estado s25 = new Estado(true, 25);
        s24.adicionarTransicao(' ', s25);
        s24.adicionarTransicao('\t',s25);
        s24.adicionarTransicao('\r',s25);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s25, TipoToken.EMBREAGEM);

    }

    // freio
    private void criarTransicoesPalavra03() {
        Estado s26  = criarEstadoTransicaoUnica( 26, s0, 'f', false);
        Estado s27 = criarEstadoTransicaoUnica(27, s26, 'r', false);
        Estado s28 = criarEstadoTransicaoUnica(28, s27, 'e', false);
        Estado s29 = criarEstadoTransicaoUnica(29, s28, 'i', false);
        Estado s30 = criarEstadoTransicaoUnica(30, s29, 'o', false);

        Estado s31 = new Estado(true, 31);
        s30.adicionarTransicao(' ', s31);
        s30.adicionarTransicao('\t',s31);
        s30.adicionarTransicao('\r',s31);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s31, TipoToken.FREIO);
    }

    // acelerador
    private void criarTransicoesPalavra04() {
        Estado s32  = criarEstadoTransicaoUnica( 32, s0, 'a', false);
        Estado s33 = criarEstadoTransicaoUnica(33, s32, 'c', false);
        Estado s34 = criarEstadoTransicaoUnica(34, s33, 'e', false);
        Estado s35 = criarEstadoTransicaoUnica(35, s34, 'l', false);
        Estado s36 = criarEstadoTransicaoUnica(36, s35, 'e', false);
        Estado s37 = criarEstadoTransicaoUnica(37, s36, 'r', false);
        Estado s38 = criarEstadoTransicaoUnica(38, s37, 'a', false);
        Estado s39 = criarEstadoTransicaoUnica(39, s38, 'd', false);
        Estado s40 = criarEstadoTransicaoUnica(40, s39, 'o', false);
        Estado s41 = criarEstadoTransicaoUnica(41, s40, 'r', false);

        Estado s42 = new Estado(true, 42);
        s41.adicionarTransicao(' ', s42);
        s41.adicionarTransicao('\t',s42);
        s41.adicionarTransicao('\r',s42);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s42, TipoToken.ACELERADOR);
    }


    // desligar
    private void criarTransicoesPalavra05() {
        Estado s43  = criarEstadoTransicaoUnica( 43, s0, 'd', false);
        Estado s44 = criarEstadoTransicaoUnica(44, s43, 'e', false);
        Estado s45 = criarEstadoTransicaoUnica(45, s44, 's', false);
        Estado s46 = criarEstadoTransicaoUnica(46, s45, 'l', false);
        Estado s47 = criarEstadoTransicaoUnica(47, s46, 'i', false);
        Estado s48 = criarEstadoTransicaoUnica(48, s47, 'g', false);
        Estado s49 = criarEstadoTransicaoUnica(49, s48, 'a', false);
        Estado s50 = criarEstadoTransicaoUnica(50, s49, 'r', false);

        Estado s51 = new Estado(true, 51);
        s50.adicionarTransicao(' ', s51);
        s50.adicionarTransicao('\t',s51);
        s50.adicionarTransicao('\r',s51);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s51, TipoToken.DESLIGAR);
    }

    private Estado criarEstadoTransicaoUnica(int identificador,
                                             Estado estadoAnterior, char caracterTransicao,
                                             boolean marcado) {
        Estado s = new Estado(marcado, identificador);
        estadoAnterior.adicionarTransicao(caracterTransicao, s);
        return s;
    }

}

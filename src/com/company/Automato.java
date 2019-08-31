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
        // palavra else | end | elseif
        criarTransicoesPalavra03();

        // criando as transições para reconhecer a
        // palavra if
        criarTransicoesPalavra04();

        // criando as transições para reconhecer a
        // palavra for
        criarTransicoesPalavra05();

        // criando as transições para reconhecer a
        // palavra do
        criarTransicoesPalavra06();

        // criando as transições para reconhecer a
        // palavra while | Write
        criarTransicoesPalavra07();

        // criando as transições para reconhecer a
        // palavra read
        criarTransicoesPalavra08();

        // criando as transições para reconhecer a
        // palavra (
        criarTransicoesPalavra09();

        // criando as transições para reconhecer a
        // palavra )
        criarTransicoesPalavra10();

        // criando as transições para reconhecer a
        // palavra {
        criarTransicoesPalavra11();

        // criando as transições para reconhecer a
        // palavra }
        criarTransicoesPalavra12();

        // criando as transições para reconhecer a
        // palavra + | ++ | +=
        criarTransicoesPalavra13();

        // criando as transições para reconhecer a
        // palavra - | -- | -=
        criarTransicoesPalavra14();

        // criando as transições para reconhecer a
        // palavra *
        criarTransicoesPalavra15();

        // criando as transições para reconhecer a
        // palavra = | ==
        criarTransicoesPalavra16();

        // criando as transições para reconhecer a
        // palavra !=
        criarTransicoesPalavra17();

        // criando as transições para reconhecer a
        // palavra < |<=
        criarTransicoesPalavra18();

        // criando as transições para reconhecer a
        // palavra > | >=
        criarTransicoesPalavra19();

        // criando as transições para reconhecer a
        // palavra /
        criarTransicoesPalavra20();

        // criando as transições para reconhecer a
        // palavra %
        criarTransicoesPalavra21();

        // criando as transições para reconhecer a
        // palavra ;
        criarTransicoesPalavra22();

        // criando as transições para reconhecer a
        // palavra ||
        criarTransicoesPalavra23();

        // criando as transições para reconhecer a
        // palavra &&
        criarTransicoesPalavra24();
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

    // program
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

    // begin
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

    // else | end | elseif
    private void criarTransicoesPalavra03() {
        Estado s15  = criarEstadoTransicaoUnica( 15, s0, 'e', false);
        Estado s19 = criarEstadoTransicaoUnica(19, s15, 'l', false);
        Estado s20 = criarEstadoTransicaoUnica(20, s19, 's', false);
        Estado s21 = criarEstadoTransicaoUnica(21, s20, 'e', false);

        Estado s22 = new Estado(true, 22);
        s21.adicionarTransicao(' ', s22);
        s21.adicionarTransicao('\t',s22);
        s21.adicionarTransicao('\r',s22);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s22, TipoToken.ELSE);

        Estado s16 = criarEstadoTransicaoUnica(16, s15, 'n', false);
        Estado s17 = criarEstadoTransicaoUnica(17,s16, 'd', false);

        Estado s18 = new Estado(true, 18);
        s17.adicionarTransicao(' ', s18);
        s17.adicionarTransicao('\t',s18);
        s17.adicionarTransicao('\r',s18);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s18, TipoToken.END);

        Estado s23 = criarEstadoTransicaoUnica(23, s21, 'i', false);
        Estado s24 = criarEstadoTransicaoUnica(24, s23, 'f', false);

        Estado s25 = new Estado(true, 25);
        s24.adicionarTransicao(' ', s25);
        s24.adicionarTransicao('\t',s25);
        s24.adicionarTransicao('\r',s25);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s25, TipoToken.ELSEIF);
    }

    // if
    private void criarTransicoesPalavra04() {
        Estado s26  = criarEstadoTransicaoUnica( 26, s0, 'i', false);
        Estado s27 = criarEstadoTransicaoUnica(27, s26, 'f', false);

        Estado s28 = new Estado(true, 28);
        s27.adicionarTransicao(' ', s28);
        s27.adicionarTransicao('\t',s28);
        s27.adicionarTransicao('\r',s28);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s28, TipoToken.IF);
    }


    // for
    private void criarTransicoesPalavra05() {
        Estado s29  = criarEstadoTransicaoUnica( 29, s0, 'f', false);
        Estado s30 = criarEstadoTransicaoUnica(30, s29, 'o', false);
        Estado s31 = criarEstadoTransicaoUnica(31, s30, 'r', false);

        Estado s32 = new Estado(true, 32);
        s31.adicionarTransicao(' ', s32);
        s31.adicionarTransicao('\t',s32);
        s31.adicionarTransicao('\r',s32);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s32, TipoToken.FOR);
    }

    // do
    private void criarTransicoesPalavra06() {
        Estado s33  = criarEstadoTransicaoUnica( 33, s0, 'd', false);
        Estado s34 = criarEstadoTransicaoUnica(34, s33, 'o', false);

        Estado s35 = new Estado(true, 35);
        s34.adicionarTransicao(' ', s35);
        s34.adicionarTransicao('\t',s35);
        s34.adicionarTransicao('\r',s35);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s35, TipoToken.DO);
    }

    // while | write
    private void criarTransicoesPalavra07() {
        Estado s36  = criarEstadoTransicaoUnica( 36, s0, 'w', false);
        Estado s37 = criarEstadoTransicaoUnica(37, s36, 'h', false);
        Estado s38 = criarEstadoTransicaoUnica(38, s37, 'i', false);
        Estado s39 = criarEstadoTransicaoUnica(39, s38, 'l', false);
        Estado s40 = criarEstadoTransicaoUnica(40, s39, 'e', false);

        Estado s41 = new Estado(true, 41);
        s40.adicionarTransicao(' ', s41);
        s40.adicionarTransicao('\t',s41);
        s40.adicionarTransicao('\r',s41);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s41, TipoToken.WHILE);

        Estado s47 = criarEstadoTransicaoUnica(47, s36, 'r', false);
        Estado s48 = criarEstadoTransicaoUnica(48, s47, 'i', false);
        Estado s49 = criarEstadoTransicaoUnica(49, s48, 't', false);
        Estado s50 = criarEstadoTransicaoUnica(50, s49, 'e', false);

        Estado s51 = new Estado(true, 51);
        s50.adicionarTransicao(' ', s51);
        s50.adicionarTransicao('\t',s51);
        s50.adicionarTransicao('\r',s51);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s51, TipoToken.WRITE);
    }

    // read
    private void criarTransicoesPalavra08() {
        Estado s42  = criarEstadoTransicaoUnica( 42, s0, 'r', false);
        Estado s43 = criarEstadoTransicaoUnica(43, s42, 'e', false);
        Estado s44 = criarEstadoTransicaoUnica(44, s43, 'a', false);
        Estado s45 = criarEstadoTransicaoUnica(45, s44, 'd', false);

        Estado s46 = new Estado(true, 46);
        s45.adicionarTransicao(' ', s46);
        s45.adicionarTransicao('\t',s46);
        s45.adicionarTransicao('\r',s46);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s46, TipoToken.READ);
    }

    // (
    private void criarTransicoesPalavra09() {
        Estado s52  = criarEstadoTransicaoUnica( 52, s0, '(', false);

        Estado s53 = new Estado(true, 53);
        s52.adicionarTransicao(' ', s53);
        s52.adicionarTransicao('\t',s53);
        s52.adicionarTransicao('\r',s53);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s53, TipoToken.PARENTESE_ABRE);
    }

    // )
    private void criarTransicoesPalavra10() {
        Estado s54  = criarEstadoTransicaoUnica( 54, s0, ')', false);

        Estado s55 = new Estado(true, 55);
        s54.adicionarTransicao(' ', s55);
        s54.adicionarTransicao('\t',s55);
        s54.adicionarTransicao('\r',s55);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s55, TipoToken.PARENTESE_FECHA);
    }

    // {
    private void criarTransicoesPalavra11() {
        Estado s56  = criarEstadoTransicaoUnica( 56, s0, '{', false);

        Estado s57 = new Estado(true, 57);
        s56.adicionarTransicao(' ', s57);
        s56.adicionarTransicao('\t',s57);
        s56.adicionarTransicao('\r',s57);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s57, TipoToken.CHAVE_ABRE);
    }

    // }
    private void criarTransicoesPalavra12() {
        Estado s58  = criarEstadoTransicaoUnica( 58, s0, '}', false);

        Estado s59 = new Estado(true, 59);
        s58.adicionarTransicao(' ', s59);
        s58.adicionarTransicao('\t',s59);
        s58.adicionarTransicao('\r',s59);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s59, TipoToken.CHAVE_FECHA);
    }

    // +
    private void criarTransicoesPalavra13() {
        Estado s60  = criarEstadoTransicaoUnica( 60, s0, '+', false);

        Estado s61 = new Estado(true, 61);
        s60.adicionarTransicao(' ', s61);
        s60.adicionarTransicao('\t',s61);
        s60.adicionarTransicao('\r',s61);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s61, TipoToken.SOMA);

        Estado s62  = criarEstadoTransicaoUnica( 62, s60, '+', false);

        Estado s63 = new Estado(true, 63);
        s62.adicionarTransicao(' ', s63);
        s62.adicionarTransicao('\t',s63);
        s62.adicionarTransicao('\r',s63);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s63, TipoToken.SOMA_SOMA);

        Estado s64  = criarEstadoTransicaoUnica( 64, s60, '=', false);

        Estado s65 = new Estado(true, 65);
        s64.adicionarTransicao(' ', s65);
        s64.adicionarTransicao('\t',s65);
        s64.adicionarTransicao('\r',s65);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s65, TipoToken.SOMA_IGUAL);
    }

    // -
    private void criarTransicoesPalavra14() {
        Estado s66  = criarEstadoTransicaoUnica( 66, s0, '-', false);

        Estado s67 = new Estado(true, 67);
        s66.adicionarTransicao(' ', s67);
        s66.adicionarTransicao('\t',s67);
        s66.adicionarTransicao('\r',s67);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s67, TipoToken.SUBTRAI);

        Estado s68  = criarEstadoTransicaoUnica( 68, s66, '-', false);

        Estado s69 = new Estado(true, 69);
        s68.adicionarTransicao(' ', s69);
        s68.adicionarTransicao('\t',s69);
        s68.adicionarTransicao('\r',s69);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s69, TipoToken.SUBTRAI_SUBTRAI);

        Estado s70  = criarEstadoTransicaoUnica( 70, s66, '=', false);

        Estado s71 = new Estado(true, 71);
        s70.adicionarTransicao(' ', s71);
        s70.adicionarTransicao('\t',s71);
        s70.adicionarTransicao('\r',s71);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s71, TipoToken.SUBTRAI_IGUAL);
    }

    // *
    private void criarTransicoesPalavra15() {
        Estado s72  = criarEstadoTransicaoUnica( 72, s0, '*', false);

        Estado s73 = new Estado(true, 73);
        s72.adicionarTransicao(' ', s73);
        s72.adicionarTransicao('\t',s73);
        s72.adicionarTransicao('\r',s73);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s73, TipoToken.ASTERISCO);
    }

    // = | ==
    private void criarTransicoesPalavra16() {
        Estado s74  = criarEstadoTransicaoUnica( 74, s0, '=', false);

        Estado s75 = new Estado(true, 75);
        s74.adicionarTransicao(' ', s75);
        s74.adicionarTransicao('\t',s75);
        s74.adicionarTransicao('\r',s75);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s75, TipoToken.IGUAL);

        Estado s76  = criarEstadoTransicaoUnica( 76, s74, '=', false);

        Estado s77 = new Estado(true, 77);
        s76.adicionarTransicao(' ', s77);
        s76.adicionarTransicao('\t',s77);
        s76.adicionarTransicao('\r',s77);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s77, TipoToken.IGUAL_IGUAL);
    }

    // !=
    private void criarTransicoesPalavra17() {
        Estado s78  = criarEstadoTransicaoUnica( 78, s0, '!', false);
        Estado s79  = criarEstadoTransicaoUnica( 79, s78, '=', false);

        Estado s80 = new Estado(true, 80);
        s79.adicionarTransicao(' ', s80);
        s79.adicionarTransicao('\t',s80);
        s79.adicionarTransicao('\r',s80);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s80, TipoToken.EXCLAMACAO_IGUAL);
    }

    // < | <=
    private void criarTransicoesPalavra18() {
        Estado s81  = criarEstadoTransicaoUnica( 81, s0, '<', false);

        Estado s82 = new Estado(true, 82);
        s81.adicionarTransicao(' ', s82);
        s81.adicionarTransicao('\t',s82);
        s81.adicionarTransicao('\r',s82);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s82, TipoToken.MENOR_QUE);

        Estado s83  = criarEstadoTransicaoUnica( 83, s81, '=', false);

        Estado s84 = new Estado(true, 84);
        s83.adicionarTransicao(' ', s84);
        s83.adicionarTransicao('\t',s84);
        s83.adicionarTransicao('\r',s84);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s84, TipoToken.MENOR_IGUAL);
    }

    // > | >=
    private void criarTransicoesPalavra19() {
        Estado s85  = criarEstadoTransicaoUnica( 85, s0, '>', false);

        Estado s86 = new Estado(true, 86);
        s85.adicionarTransicao(' ', s86);
        s85.adicionarTransicao('\t',s86);
        s85.adicionarTransicao('\r',s86);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s86, TipoToken.MAIOR_QUE);

        Estado s87  = criarEstadoTransicaoUnica( 87, s85, '=', false);

        Estado s88 = new Estado(true, 88);
        s87.adicionarTransicao(' ', s88);
        s87.adicionarTransicao('\t',s88);
        s87.adicionarTransicao('\r',s88);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s88, TipoToken.MAIOR_IGUAL);
    }

    // /
    private void criarTransicoesPalavra20() {
        Estado s89  = criarEstadoTransicaoUnica( 89, s0, '/', false);

        Estado s90 = new Estado(true, 90);
        s89.adicionarTransicao(' ', s90);
        s89.adicionarTransicao('\t',s90);
        s89.adicionarTransicao('\r',s90);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s90, TipoToken.BARRA);
    }

    // %
    private void criarTransicoesPalavra21() {
        Estado s91  = criarEstadoTransicaoUnica( 91, s0, '%', false);

        Estado s92 = new Estado(true, 92);
        s91.adicionarTransicao(' ', s92);
        s91.adicionarTransicao('\t',s92);
        s91.adicionarTransicao('\r',s92);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s92, TipoToken.PORCENTAGEM);
    }

    // ;
    private void criarTransicoesPalavra22() {
        Estado s93  = criarEstadoTransicaoUnica( 93, s0, ';', false);

        Estado s94 = new Estado(true, 94);
        s93.adicionarTransicao(' ', s94);
        s93.adicionarTransicao('\t',s94);
        s93.adicionarTransicao('\r',s94);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s94, TipoToken.PONTO_VIRGULA);
    }

    // ||
    private void criarTransicoesPalavra23() {
        Estado s95  = criarEstadoTransicaoUnica( 95, s0, '|', false);
        Estado s96  = criarEstadoTransicaoUnica( 96, s95, '|', false);

        Estado s97 = new Estado(true, 97);
        s96.adicionarTransicao(' ', s97);
        s96.adicionarTransicao('\t',s97);
        s96.adicionarTransicao('\r',s97);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s97, TipoToken.BARRA_BARRA);
    }

    // &&
    private void criarTransicoesPalavra24() {
        Estado s98  = criarEstadoTransicaoUnica( 98, s0, '&', false);
        Estado s99  = criarEstadoTransicaoUnica( 99, s98, '&', false);

        Estado s100 = new Estado(true, 100);
        s99.adicionarTransicao(' ', s100);
        s99.adicionarTransicao('\t',s100);
        s99.adicionarTransicao('\r',s100);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s100, TipoToken.ECOMERCIAL_ECOMERCIAL);
    }

    // Variável
    private void criarTransicoesPalavra25() {
        Estado s101  = criarEstadoTransicaoIntervalo( 101, s0, '?', false);

        Estado s102 = new Estado(true, 100);
        s101.adicionarTransicao(' ', s102);
        s101.adicionarTransicao('\t',s102);
        s101.adicionarTransicao('\r',s102);

        // adiciona o valor na tabela de símbolos
        tabelaSimbolos.put(s102, TipoToken.VARIAVEL);
    }

    private Estado criarEstadoTransicaoUnica(int identificador,
                                             Estado estadoAnterior, char caracterTransicao,
                                             boolean marcado) {
        Estado s = new Estado(marcado, identificador);
        estadoAnterior.adicionarTransicao(caracterTransicao, s);
        return s;
    }

    private Estado criarEstadoTransicaoIntervalo(int identificador,
                                             Estado estadoAnterior, char caracterTransicao,
                                             boolean marcado) {
        Estado s = new Estado(marcado, identificador);
        if (caracterTransicao > (int)'a' && caracterTransicao < (int)'z'){
            estadoAnterior.adicionarTransicao(caracterTransicao, s);
        }
        return s;
    }
}

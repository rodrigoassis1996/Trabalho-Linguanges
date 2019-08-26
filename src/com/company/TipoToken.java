package com.company;

/**
 * @author Rodrigo Assis
 * Representa a enumeração do tipo do token.
 */
public enum TipoToken {
    // Tipo de token para palavra não reconhecida
    NAO_RECONHECIDA,

    // Tipo de token para descartar
    DESCARTAR,

    // Tipo de token para program
    PROGRAM,

    // Tipo de token para begin
    BEGIN,

    // Tipo de token para end
    END,

    // Tipo de token para if
    IF,

    // Tipo de token para elseif
    ELSEIF,

    // Tipo de token para else
    ELSE,

    // Tipo de token para for
    FOR,

    // Tipo de token para while
    WHILE,

    // Tipo de token para do
    DO,

    // Tipo de token para read
    READ,

    // Tipo de token para write
    WRITE,

    // Tipo de token para (
    PARENTESE_ABRE,

    // Tipo de token para )
    PARENTESE_FECHA,

    // Tipo de token para {
    CHAVE_ABRE,

    // Tipo de token para }
    CHAVE_FECHA,

    // Tipo de token para +
    SOMA,

    // Tipo de token para ++
    SOMA_SOMA,

    // Tipo de token para -
    SUBTRAI,

    // Tipo de token para --
    SUBTRAI_SUBTRAI,

    // Tipo de token para *
    ASTERISCO,

    // Tipo de token para =
    IGUAL,

    // Tipo de token para !=
    EXCLAMACAO_IGUAL,

    // Tipo de token para <
    MENOR_QUE,

    // Tipo de token para >
    MAIOR_QUE,

    // Tipo de token para <=
    MENOR_IGUAL,

    // Tipo de token para >=
    MAIOR_IGUAL,

    // Tipo de token para /
    BARRA,

    // Tipo de token para %
    PORCENTAGEM,

    // Tipo de token para ||
    BARRA_BARRA,

    // Tipo de token para &&
    ECOMERCIAL_ECOMERCIAL,

    // Tipo de token para +=
    SOMA_IGUAL,

    // Tipo de token para -=
    SUBTRAI_IGUAL,

    // Tipo de token para ;
    PONTO_VIRGULA,

    // Tipo de token para (a-z)(a-z | A-Z | 0-9)*
    VARIAVEL,

    // Tipo de token para (0-9) +((.)(0-9)+)^0-1
    NUMERO_REAL,

    // Uma string qualquer
    STRING;
}

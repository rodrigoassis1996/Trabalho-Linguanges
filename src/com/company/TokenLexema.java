package com.company;

/**
 * @author Rodrigo Assis
 * Representa um token
 */
public class TokenLexema {

    private TipoToken tipoToken;
    private String lexema;

    /**
     * Construtor.
     */
    public TokenLexema(TipoToken tipoToken, String lexema) {
        super();
        this.tipoToken = tipoToken;
        this.lexema = lexema;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[tipoToken=" + tipoToken + ", lexema=" + lexema + "]";
    }

    public TipoToken getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(TipoToken tipoToken) {
        this.tipoToken = tipoToken;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
}

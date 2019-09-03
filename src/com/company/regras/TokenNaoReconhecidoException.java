package com.company.regras;

import com.company.TipoToken;
import com.company.TokenLexema;

/**
 * @author
 *
 * Exceção de token Não reconhecido
 */
public class TokenNaoReconhecidoException extends RuntimeException {

  /**
   * @param tl {@link TokenLexema}
   */
  public TokenNaoReconhecidoException(TokenLexema tl) {
    super ("Token não reconhecido " + tl );
  }
}

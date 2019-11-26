package com.company.regras;

import java.util.List;

import com.company.TokenLexema;

/**
 * @author
 * Representa uma regra abstrata
 */
public abstract class RegraAbstrata {

  /**
   * Reconhece o próximo token
   * @param lista {@link List}
   * @param indice {@link Integer}
   */
  public abstract void reconher(List<TokenLexema> lista, int indice);
}

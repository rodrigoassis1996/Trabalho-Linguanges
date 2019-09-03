package com.company.regras;

import com.company.TipoToken;
import com.company.TokenLexema;

import java.util.List;

/**
 * Created by usuario on 03/09/2019.
 */
public class ListaDeVariaveis extends RegraAbstrata {

  @Override
  public void reconher(List<TokenLexema> lista, int indice) {
    System.out.println("Regra Lista de Variáveis");

    if (indice >= lista.size()) {
      throw new TokenNaoReconhecidoException(null);
    }

    TokenLexema tl = lista.get(indice);
    if (!TipoToken.VARIAVEL.equals(tl.getTipoToken())) {
      throw new TokenNaoReconhecidoException(tl);
    }

  }
}

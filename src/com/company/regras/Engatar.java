package com.company.regras;

import com.company.TipoToken;
import com.company.TokenLexema;

import java.util.List;


/**
 * Created by usuario on 03/09/2019.
 */
public class Engatar extends RegraAbstrata{


  @Override
  public void reconher(List<TokenLexema> lista, int indice) {
    System.out.println("Regra Engatar");

    if(indice < lista.size()) {
      TokenLexema tl = lista.get(indice);
      //System.out.println(tl.getTipoToken());
      if(!TipoToken.ENGATAR.equals(tl.getTipoToken())) {
        throw new TokenNaoReconhecidoException(tl);
      }

      indice ++;
      Acelerador a = new Acelerador();
      a.reconher(lista, indice);

    }
  }
}

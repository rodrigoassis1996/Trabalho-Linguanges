package com.company.regras;

import com.company.TipoToken;
import com.company.TokenLexema;

import java.util.List;


/**
 * Created by usuario on 03/09/2019.
 */
public class S extends RegraAbstrata{


  @Override
  public void reconher(List<TokenLexema> lista, int indice) {
    System.out.println("Regra Partida");

    if(indice < lista.size()) {
      TokenLexema tl = lista.get(indice);
      //System.out.println(tl.getTipoToken());
      if(!TipoToken.PARTIDA.equals(tl.getTipoToken())) {
        throw new TokenNaoReconhecidoException(tl);
      }
        indice ++;
        Embreagem e = new Embreagem();
        e.reconher(lista, indice);


    }
  }
}

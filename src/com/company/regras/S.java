package com.company.regras;

import com.company.TipoToken;
import com.company.TokenLexema;

import java.util.List;


/**
 * Created by usuario on 03/09/2019.
 */
public class S extends RegraAbstrata{

  private ListaDeVariaveis lista_de_variaveis = new ListaDeVariaveis();

  @Override
  public void reconher(List<TokenLexema> lista, int indice) {
    System.out.println("Regra S");

    if(indice < lista.size()) {
      TokenLexema tl = lista.get(indice);
      if(!TipoToken.PROGRAM.equals(tl.getTipoToken())) {
        throw new TokenNaoReconhecidoException(tl);
      }

      indice ++;
      if(indice < lista.size()) {
        tl = lista.get(indice);
        if(!TipoToken.BEGIN.equals(tl.getTipoToken())) {
          lista_de_variaveis.reconher(lista, indice);
        }
      }
    }
  }

}

import { Restricao } from './restricao';
import { FuncaoObjetiva } from './funcao-objetiva';

export class MixDeProducao {

  funcaoObjetiva: FuncaoObjetiva;
  restricoes: Restricao[];

  constructor() {
    this.funcaoObjetiva = new FuncaoObjetiva();
    this.restricoes = [];
  }
}
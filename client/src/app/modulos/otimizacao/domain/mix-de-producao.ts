import { Restricao } from './restricao';
import { FuncaoObjetiva } from './funcao-objetiva';

export class MixDeProducao {
  
  metodo: string;
  quantidadeVariaveisDecisao: number;
  quantidadeRestricao: number;
  funcaoObjetiva: FuncaoObjetiva;
  restricoes: Restricao[];

  constructor() {
    this.funcaoObjetiva = new FuncaoObjetiva();
    this.restricoes = [];
  }
}
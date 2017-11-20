import { DropDownUtils } from 'app/util/controls/dropdown.util';
import { Pageable } from './../../util/pageable-request';
import { SelectItem } from 'primeng/components/common/api';
import { DateUtil } from './../../util/date-util';
import { Restricao } from './../domain/restricao';
import { MixDeProducao } from './../domain/mix-de-producao';
import { FuncaoObjetiva } from './../domain/funcao-objetiva';
import { Location } from '@angular/common';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ConfirmationService } from 'primeng/primeng';
import { ActivatedRoute, Router } from '@angular/router';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { Component } from '@angular/core';
import { OnInit, ViewChild, ElementRef } from '@angular/core';
declare const $: any;
import * as moment from 'moment/moment';
import * as _ from 'lodash';
import { OtimizacaoSimplexService } from 'app/modulos/otimizacao/service/otimizacao-simplex.service';

@Component({
  selector: 'app-otimizacao-simplex',
  styleUrls: ['./otimizacao-simplex.component.css'],
  templateUrl: './otimizacao-simplex.component.html'
})
export class OtimizacaoSimplexComponent implements OnInit {
  @BlockUI() blockUI: NgBlockUI;

  restricao: Restricao = new Restricao();
  mixDeProducao: MixDeProducao = new MixDeProducao();
  funcaoObjetiva: FuncaoObjetiva = new FuncaoObjetiva();
  restricao1: Restricao = new Restricao();
  restricao2: Restricao = new Restricao();
  restricao3: Restricao = new Restricao();
 
  montarProblema = false;
  escolherVariaveis = true;
 
  objetivosOptions: SelectItem[] = [];
  tipoRestricaoOptions: SelectItem[] = [];

  quantidadeVariaveisDecisao: any[] = [];
  quantidadeRestricoes: any[] = [];
  constructor(private otimizacaoSimplexService: OtimizacaoSimplexService) {
  }

  ngOnInit() {
  
    this.objetivosOptions.push({ label: "Selecione...", value: null});
    this.objetivosOptions.push({ label: "Maximizar", value: 'max'});
    this.objetivosOptions.push({ label: "Minimizar", value: 'min'});

    this.tipoRestricaoOptions.push({ label: '<=', value: 'menorIgual'});
    this.tipoRestricaoOptions.push({ label: '>=', value: 'maiorIgual'});
    this.tipoRestricaoOptions.push({ label: '=', value: 'igual'});
    
  }

  calcular(){
    this.mixDeProducao.funcaoObjetiva = this.funcaoObjetiva;
    this.mixDeProducao.restricoes.push(this.restricao1);
    this.mixDeProducao.restricoes.push(this.restricao2);
    this.mixDeProducao.restricoes.push(this.restricao3);
    console.log(this.mixDeProducao);
    this.otimizacaoSimplexService.resolverProblema(this.mixDeProducao).$observable.subscribe(res => {
      console.log(res);
    })
  }

  checkNumber(event, campo, maxValue) {
    const value = event.target.value;
    if (value.length > maxValue) {
      campo.control.setValue(value.substring(0, value.length - (value.length - maxValue)));
    }
  }

  checkNaN(event) {
    if ((isNaN(event.key) && event.key !== 'Backspace' && event.key !== 'Tab') || event.key === ' ') {
      event.preventDefault();
    }
  }
}

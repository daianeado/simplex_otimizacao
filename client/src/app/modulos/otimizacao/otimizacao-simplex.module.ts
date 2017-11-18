import { OtimizacaoSimplexService } from './service/otimizacao-simplex.service';
import { OtimizacaoSimplexRoutingModule } from './otimizacao-simplex.route';
import { OtimizacaoSimplexComponent } from './otimizacao-simplex/otimizacao-simplex.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CurrencyMaskModule } from 'ng2-currency-mask';
import { PrimeNgModules } from '../../primeng.modules';
import { DateBeforeValidator } from './../../util/validators/date-before.validator';


const COMPONENTES = [
  OtimizacaoSimplexComponent
];

@NgModule({
  imports: [
    PrimeNgModules,
    CommonModule,
    FormsModule,
    OtimizacaoSimplexRoutingModule
  ],
  declarations: [
    ...COMPONENTES,
  ],
  providers: [
    OtimizacaoSimplexService
  ]

})
export class OtimizacaoSimplexModule {
}

import { OtimizacaoSimplexComponent } from './otimizacao-simplex/otimizacao-simplex.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

export const OtimizacaoSimplexRoute: Routes = [

    { path: 'otimizacao-simplex', component: OtimizacaoSimplexComponent }
];

@NgModule({
    imports: [
        RouterModule.forChild(OtimizacaoSimplexRoute)
    ],
    exports: [RouterModule]
})
export class OtimizacaoSimplexRoutingModule {

}

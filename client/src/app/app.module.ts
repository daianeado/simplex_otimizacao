import { OtimizacaoSimplexRoute } from './modulos/otimizacao/otimizacao-simplex.route';
import { OtimizacaoSimplexModule } from './modulos/otimizacao/otimizacao-simplex.module';
import { AppFooter } from './app.footer.component';
import { AppTopBar } from './app.topbar.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule, LOCALE_ID } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { CurrencyMaskModule } from 'ng2-currency-mask';
import { ResourceModule } from 'ngx-resource';
import { ToastrModule } from 'ngx-toastr';
import { ConfirmationService, GrowlModule } from 'primeng/primeng';
import { BlockUIModule } from 'ng-block-ui';

import { AppComponent } from './app.component';
import { PrimeNgModules } from './primeng.modules';

@NgModule({
  declarations: [
    AppComponent,
    AppTopBar,
    AppFooter
  ],
  imports: [
    BlockUIModule,
    BrowserAnimationsModule,
    BrowserModule,
    CurrencyMaskModule,
    OtimizacaoSimplexModule,
    FormsModule,
    GrowlModule,
    PrimeNgModules,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    ResourceModule.forRoot(),
    RouterModule.forRoot(OtimizacaoSimplexRoute, { useHash: true })

  ],
  providers: [
    ConfirmationService,
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: LOCALE_ID, useValue: 'pt-BR' }
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }

import {Component,Inject,forwardRef} from '@angular/core';
import {AppComponent} from './app.component';

@Component({
    selector: 'app-footer',
    template: `
        <div class="footer">
            <div class="card clearfix">
                <span class="footer-text-left">Otimização de Sistemas Computacionais</span>
                <span class="footer-text-right">2017</span>
            </div>
        </div>
    `
})
export class AppFooter {

}
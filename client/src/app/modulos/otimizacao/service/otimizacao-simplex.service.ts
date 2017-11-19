import { MixDeProducao } from './../domain/mix-de-producao';
import { Injectable } from '@angular/core';
import { RequestMethod } from '@angular/http';
import { Resource, ResourceAction, ResourceParams, ResourceResult } from 'ngx-resource';
import { ResourceMethod, ResourceMethodStrict } from 'ngx-resource/src/Interfaces';

import { AppSettings } from '../../../app.settings';
import { Page } from '../../util/page';
import { Pageable } from '../../util/pageable-request';

@Injectable()
@ResourceParams({
    url: '/api/otimizacao/simplex',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
})
export class OtimizacaoSimplexService extends Resource {

    @ResourceAction({
        method: RequestMethod.Post
    })
    resolverProblema: ResourceMethod<MixDeProducao, MixDeProducao>;
   
}
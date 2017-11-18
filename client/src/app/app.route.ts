import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

export const routes: Routes = [{ path: '**', redirectTo: '', canActivate: [] }];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes);
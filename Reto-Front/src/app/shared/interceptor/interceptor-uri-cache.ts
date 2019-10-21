import { HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { RegisterCacheService } from './register-cache.service';

@Injectable()
export class InterceptorUriCache implements HttpInterceptor {
  constructor(private cache: RegisterCacheService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    if (!this.estaCachadaPeticion(req)) {
      return next.handle(req);
    }

    const respuestaCacheada = this.cache.buscarEnCache(req);

    if (null !== respuestaCacheada) {
      return of(respuestaCacheada);
    }

    return next.handle(req).pipe(
      tap(evento => {
        if (evento instanceof HttpResponse) {
          this.cache.adicionarEnCache(req, evento);
        }
      })
    );
  }

  private estaCachadaPeticion(req: HttpRequest<any>) {
    return (req.method === 'GET') && (req.url.indexOf(environment.baseUrl) > -1);
  }
}

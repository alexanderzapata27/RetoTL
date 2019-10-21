import { HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EntranceCache, MAXIMO_TIEMPO_CACHE } from './entrance-cache';

@Injectable()
export class RegisterCacheService {
  listaRecursoCache = new Map<string, EntranceCache>();

  buscarEnCache(request: HttpRequest<any>): HttpResponse<any> | null {
    const recursoCache = this.listaRecursoCache.get(request.urlWithParams);
    if (!recursoCache) {
      return  null;
    }
    const expiro = (Date.now() - recursoCache.tiempoEntrante) > MAXIMO_TIEMPO_CACHE;
    return expiro ? null : recursoCache.resp;
  }

  adicionarEnCache(request: HttpRequest<any>, response: HttpResponse<any>): void {
    const entrada: EntranceCache = { url: request.urlWithParams, resp: response, tiempoEntrante: Date.now() };
    if (request.headers.get('use-cache')) {
      this.listaRecursoCache.set(request.urlWithParams, entrada);
    }
    this.borradoExpiroCache();
  }

  private borradoExpiroCache() {
    this.listaRecursoCache.forEach(dato => {
      if ((Date.now() - dato.tiempoEntrante) > MAXIMO_TIEMPO_CACHE) {
        this.listaRecursoCache.delete(dato.url);
      }
    });
  }
}

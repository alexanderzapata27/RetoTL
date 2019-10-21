import { HttpResponse } from '@angular/common/http';

export interface EntranceCache {
  url: string;
  resp: HttpResponse<any>;
  tiempoEntrante: number;
}

export const MAXIMO_TIEMPO_CACHE = 20000;

import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';

import { ApiResponse } from '../../../core/models/api-response';

import { RewriteRequest } from '../models/rewrite-request';
import { RewriteResponse } from '../models/rewrite-response';

@Injectable({
  providedIn: 'root'
})
export class ResumeRewriteService {

  private readonly http = inject(HttpClient);

  private readonly apiUrl =
    `${environment.apiUrl}/rewrite`;

  rewrite(
    request: RewriteRequest
  ): Observable<ApiResponse<RewriteResponse>> {

    return this.http.post<ApiResponse<RewriteResponse>>(
      this.apiUrl,
      request
    );

  }

}
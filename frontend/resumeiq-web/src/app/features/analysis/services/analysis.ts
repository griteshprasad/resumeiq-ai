import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';

import { ApiResponse } from '../../../core/models/api-response';

import { AnalysisRequest } from '../models/analysis-request';
import { AnalysisResponse } from '../models/analysis-response';

@Injectable({
  providedIn: 'root'
})
export class AnalysisService {

  private readonly http = inject(HttpClient);

  private readonly apiUrl =
    `${environment.apiUrl}/analysis`;

  analyze(
    request: AnalysisRequest
  ): Observable<ApiResponse<AnalysisResponse>> {

    return this.http.post<ApiResponse<AnalysisResponse>>(
      this.apiUrl,
      request
    );

  }

}
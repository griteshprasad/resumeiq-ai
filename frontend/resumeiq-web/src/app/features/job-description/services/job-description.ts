import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { ApiResponse } from '../../../core/models/api-response';
import { JobDescriptionResponse } from '../models/job-description-response';

@Injectable({
  providedIn: 'root'
})
export class JobDescriptionService {

  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/jobDescriptions`;

  getAll(): Observable<ApiResponse<JobDescriptionResponse[]>> {
    return this.http.get<ApiResponse<JobDescriptionResponse[]>>(this.apiUrl);
  }

  getById(id: string): Observable<ApiResponse<JobDescriptionResponse>> {
    return this.http.get<ApiResponse<JobDescriptionResponse>>(`${this.apiUrl}/${id}`);
  }

  upload(file: File): Observable<ApiResponse<JobDescriptionResponse>> {

    const formData = new FormData();
    formData.append('file', file);

    return this.http.post<ApiResponse<JobDescriptionResponse>>(
      `${this.apiUrl}/upload`,
      formData
    );
  }

  delete(id: string): Observable<ApiResponse<string>> {
    return this.http.delete<ApiResponse<string>>(`${this.apiUrl}/${id}`);
  }

}
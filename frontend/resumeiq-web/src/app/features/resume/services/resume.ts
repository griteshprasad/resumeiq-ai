import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { ApiResponse } from '../../../core/models/api-response';
import { ResumeResponse } from '../models/resume-response';

@Injectable({
  providedIn: 'root'
})
export class ResumeService {

  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/resumes`;

  getAll(): Observable<ApiResponse<ResumeResponse[]>> {
    return this.http.get<ApiResponse<ResumeResponse[]>>(this.apiUrl);
  }

  getById(id: string): Observable<ApiResponse<ResumeResponse>> {
    return this.http.get<ApiResponse<ResumeResponse>>(`${this.apiUrl}/${id}`);
  }

  upload(file: File): Observable<ApiResponse<ResumeResponse>> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post<ApiResponse<ResumeResponse>>(
      `${this.apiUrl}/upload`,
      formData
    );
  }

  delete(id: string): Observable<ApiResponse<string>> {
    return this.http.delete<ApiResponse<string>>(`${this.apiUrl}/${id}`);
  }
}
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

import { environment } from '../../../../environments/environment';

import { ApiResponse } from '../../../core/models/api-response';

import { LoginRequest } from '../models/login-request';
import { LoginResponse } from '../models/login-response';

import { RegisterRequest } from '../models/register-request';
import { RegisterResponse } from '../models/register-response';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly http = inject(HttpClient);
  private readonly router = inject(Router);
  
  private readonly apiUrl = `${environment.apiUrl}/auth`;

  private readonly TOKEN_KEY = 'access_token';

  login(request: LoginRequest): Observable<ApiResponse<LoginResponse>> {
    return this.http.post<ApiResponse<LoginResponse>>(
      `${environment.apiUrl}/auth/login`,
      request
    ).pipe(
      tap(response => this.setToken(response.data.accessToken))
    );
  }

  register(request: RegisterRequest): Observable<ApiResponse<RegisterResponse>> {
    return this.http.post<ApiResponse<RegisterResponse>>(
      `${this.apiUrl}/register`,
      request
    );
  }

  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    this.router.navigateByUrl('/login');
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

}
import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthService } from '../../../features/auth/services/auth';

@Component({
  selector: 'app-header',
  imports: [
    MatToolbarModule,
    MatButtonModule
  ],
  templateUrl: './header.html',
  styleUrl: './header.scss'
})
export class Header {

  private readonly authService = inject(AuthService);

  logout(): void {
    this.authService.logout();
  }

}
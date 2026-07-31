import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-sidenav',
  imports: [
    RouterLink,
    MatListModule
  ],
  templateUrl: './sidenav.html',
  styleUrl: './sidenav.scss'
})
export class Sidenav {}
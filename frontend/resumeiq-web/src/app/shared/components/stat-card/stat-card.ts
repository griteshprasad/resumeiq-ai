import { Component, input } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-stat-card',
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule
  ],
  templateUrl: './stat-card.html',
  styleUrl: './stat-card.scss'
})
export class StatCard {

  title = input.required<string>();

  value = input.required<string | number>();

  icon = input.required<string>();

  color = input('primary');

}
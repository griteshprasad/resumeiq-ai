import { ChangeDetectorRef, Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { DashboardService } from '../../services/dashboard';
import { DashboardData } from '../../models/dashboard-data';

import { StatCard } from '../../../../shared/components/stat-card/stat-card';
import { PageHeader } from '../../../../shared/components/page-header/page-header';

@Component({
  selector: 'app-dashboard',
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    StatCard,
    PageHeader
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard implements OnInit {

  private readonly dashboardService = inject(DashboardService);
  private readonly router = inject(Router);
  private cdr = inject(ChangeDetectorRef);

  dashboard: DashboardData = {
    resumeCount: 0,
    jobDescriptionCount: 0
  };

  ngOnInit(): void {
    this.loadDashboard();
  }

  private loadDashboard(): void {

    this.dashboardService.loadDashboard().subscribe({

      next: data => {

        this.dashboard = data;

        this.cdr.detectChanges();

      },

      error: error => {

        console.error(error);
        this.cdr.detectChanges();

      }

    });

  }

  navigateToResume(): void {

    this.router.navigate(['/resumes']);

  }

  navigateToJobDescriptions(): void {

    this.router.navigate(['/job-descriptions']);

  }

  navigateToAnalysis(): void {

    this.router.navigate(['/analysis']);

  }

}
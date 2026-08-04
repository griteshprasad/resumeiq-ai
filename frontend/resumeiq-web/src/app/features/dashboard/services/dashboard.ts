import { Injectable, inject } from '@angular/core';
import { forkJoin, map, Observable } from 'rxjs';

import { ResumeService } from '../../resume/services/resume';
import { JobDescriptionService } from '../../job-description/services/job-description';

import { DashboardData } from '../models/dashboard-data';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private readonly resumeService = inject(ResumeService);

  private readonly jobDescriptionService =
    inject(JobDescriptionService);

  loadDashboard(): Observable<DashboardData> {

    return forkJoin({

      resumes: this.resumeService.getAll(),

      jobDescriptions: this.jobDescriptionService.getAll()

    }).pipe(

      map(result => ({

        resumeCount: result.resumes.data.length,

        jobDescriptionCount:
          result.jobDescriptions.data.length

      }))

    );

  }

}
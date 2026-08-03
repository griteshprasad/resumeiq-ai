import { ChangeDetectorRef, Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';

import {
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

import { ResumeService } from '../../../resume/services/resume';
import { JobDescriptionService } from '../../../job-description/services/job-description';
import { AnalysisService } from '../../services/analysis';

import { ResumeResponse } from '../../../resume/models/resume-response';
import { JobDescriptionResponse } from '../../../job-description/models/job-description-response';
import { AnalysisResponse } from '../../models/analysis-response';

import { PageHeader } from '../../../../shared/components/page-header/page-header';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { AtsScoreCard } from '../../components/ats-score-card/ats-score-card';

@Component({
  selector: 'app-analysis-result',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatChipsModule,
    MatIconModule,
    MatDividerModule,
    PageHeader,
    MatGridListModule,
    AtsScoreCard
  ],
  templateUrl: './analysis-result.html',
  styleUrl: './analysis-result.scss'
})
export class AnalysisResult implements OnInit {

  private readonly fb = inject(NonNullableFormBuilder);

  private readonly resumeService = inject(ResumeService);
  private readonly jobDescriptionService = inject(JobDescriptionService);
  private readonly analysisService = inject(AnalysisService);
  private readonly cdr = inject(ChangeDetectorRef);

  resumes: ResumeResponse[] = [];
  jobDescriptions: JobDescriptionResponse[] = [];

  analysis?: AnalysisResponse;

  isAnalyzing = false;

  readonly analysisForm = this.fb.group({
    resumeId: ['', Validators.required],
    jobDescriptionId: ['', Validators.required]
  });

  ngOnInit(): void {
    this.loadResumes();
    this.loadJobDescriptions();
  }

  private loadResumes(): void {

    this.resumeService.getAll().subscribe({
      next: response => {
        this.resumes = response.data;
      }
    });

  }

  private loadJobDescriptions(): void {

    this.jobDescriptionService.getAll().subscribe({
      next: response => {
        this.jobDescriptions = response.data;
      }
    });

  }

  analyze(): void {

    if (this.analysisForm.invalid) {
      this.analysisForm.markAllAsTouched();
      return;
    }

    this.isAnalyzing = true;
    this.analysis = undefined;

    this.analysisService
      .analyze(this.analysisForm.getRawValue())
      .subscribe({

        next: response => {

          this.analysis = response.data;
          this.isAnalyzing = false;
          this.cdr.detectChanges();

        },

        error: error => {

          console.error(error);
          this.isAnalyzing = false;
          this.cdr.detectChanges();

        }

      });

  }

}
import {
  ChangeDetectorRef,
  Component,
  OnInit,
  inject
} from '@angular/core';

import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';

import { PageHeader } from '../../../../shared/components/page-header/page-header';

import { NotificationService } from '../../../../core/services/notification';

import { ResumeService } from '../../../resume/services/resume';
import { JobDescriptionService } from '../../../job-description/services/job-description';

import { ResumeRewriteService } from '../../services/resume-rewrite';

import { ResumeResponse } from '../../../resume/models/resume-response';
import { JobDescriptionResponse } from '../../../job-description/models/job-description-response';

import { RewriteInstruction } from '../../models/rewrite-instruction';
import { RewriteRequest } from '../../models/rewrite-request';
import { RewriteResponse } from '../../models/rewrite-response';
import { RewriteSection } from '../../models/rewrite-section';

@Component({
  selector: 'app-resume-rewrite',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    PageHeader
  ],
  templateUrl: './resume-rewrite.html',
  styleUrl: './resume-rewrite.scss'
})
export class ResumeRewrite implements OnInit {

  private readonly fb = inject(FormBuilder);

  private readonly resumeService =
    inject(ResumeService);

  private readonly jobDescriptionService =
    inject(JobDescriptionService);

  private readonly rewriteService =
    inject(ResumeRewriteService);

  private readonly notificationService =
    inject(NotificationService);

  private readonly cdr =
    inject(ChangeDetectorRef);

  resumes: ResumeResponse[] = [];

  jobDescriptions: JobDescriptionResponse[] = [];

  rewriteResult: RewriteResponse | null = null;

  isLoading = false;

  rewriteForm = this.fb.nonNullable.group({

    resumeId: ['', Validators.required],

    jobDescriptionId: ['', Validators.required],

    professionalSummaryGoal: [''],

    experienceGoal: [''],

    skillsGoal: [''],

    educationGoal: [''],

    projectsGoal: ['']

  });

  ngOnInit(): void {

    this.loadResumes();

    this.loadJobDescriptions();

  }

  private loadResumes(): void {

    this.resumeService.getAll().subscribe({

      next: response => {

        this.resumes = response.data;

        this.cdr.detectChanges();

      }

    });

  }

  private loadJobDescriptions(): void {

    this.jobDescriptionService.getAll().subscribe({

      next: response => {

        this.jobDescriptions = response.data;

        this.cdr.detectChanges();

      }

    });

  }

  rewrite(): void {

    if (this.rewriteForm.invalid) {

      this.rewriteForm.markAllAsTouched();

      return;

    }

    const request: RewriteRequest = {

      resumeId: this.rewriteForm.controls.resumeId.value,

      jobDescriptionId:
        this.rewriteForm.controls.jobDescriptionId.value,

      instructions: this.buildInstructions()

    };

    this.isLoading = true;

    this.rewriteResult = null;

    this.rewriteService.rewrite(request).subscribe({

      next: response => {

        console.log('Before assignment', this.isLoading);

        this.rewriteResult = response.data;

        console.log('After result assignment', this.rewriteResult);

        this.isLoading = false;

        console.log('After loading false', this.isLoading);

        this.notificationService.success(
          'Resume rewritten successfully.'
        );

        this.cdr.detectChanges();

        console.log('After detectChanges', this.isLoading);

      },

      error: () => {

        this.isLoading = false;

        this.notificationService.error(
          'Unable to rewrite resume.'
        );

        this.cdr.detectChanges();

      }

    });

  }

  private buildInstructions(): RewriteInstruction[] {

    const value = this.rewriteForm.getRawValue();

    const instructions: RewriteInstruction[] = [];

    this.addInstruction(
      instructions,
      RewriteSection.PROFESSIONAL_SUMMARY,
      value.professionalSummaryGoal
    );

    this.addInstruction(
      instructions,
      RewriteSection.EXPERIENCE,
      value.experienceGoal
    );

    this.addInstruction(
      instructions,
      RewriteSection.SKILLS,
      value.skillsGoal
    );

    this.addInstruction(
      instructions,
      RewriteSection.EDUCATION,
      value.educationGoal
    );

    this.addInstruction(
      instructions,
      RewriteSection.PROJECTS,
      value.projectsGoal
    );

    return instructions;

  }

  private addInstruction(
    instructions: RewriteInstruction[],
    section: RewriteSection,
    goal: string
  ): void {

    if (!goal.trim()) {

      return;

    }

    instructions.push({

      section,

      goal

    });

  }

}
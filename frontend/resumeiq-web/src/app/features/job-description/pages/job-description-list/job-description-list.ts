import { ChangeDetectorRef, Component, OnInit, inject } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { JobDescriptionResponse } from '../../models/job-description-response';
import { JobDescriptionService } from '../../services/job-description';
import { UploadJobDescriptionDialog } from '../../components/upload-job-description-dialog/upload-job-description-dialog';

import { FileSizePipe } from '../../../../shared/pipes/file-size-pipe';
import { NotificationService } from '../../../../core/services/notification';
import { PageHeader } from '../../../../shared/components/page-header/page-header';

@Component({
  selector: 'app-job-description-list',
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatIconModule,
    MatProgressSpinnerModule,
    DatePipe,
    FileSizePipe,
    PageHeader
  ],
  templateUrl: './job-description-list.html',
  styleUrl: './job-description-list.scss'
})
export class JobDescriptionList implements OnInit {

  private readonly jobDescriptionService = inject(JobDescriptionService);
  private readonly dialog = inject(MatDialog);
  private readonly notificationService = inject(NotificationService);
  private readonly cdr = inject(ChangeDetectorRef);

  isLoading = false;
  errorMessage = '';

  displayedColumns = [
    'fileName',
    'fileSize',
    'createdAt',
    'actions'
  ];

  dataSource = new MatTableDataSource<JobDescriptionResponse>();

  ngOnInit(): void {
    this.loadJobDescriptions();
  }

  private loadJobDescriptions(): void {

    this.isLoading = true;
    this.errorMessage = '';

    this.jobDescriptionService.getAll().subscribe({
      next: response => {
        this.dataSource.data = response.data;
        this.isLoading = false;

        // TODO Remove after Angular change detection investigation
        this.cdr.detectChanges();
      },
      error: () => {
        this.isLoading = false;
        this.errorMessage = 'Unable to load job descriptions.';
        this.notificationService.error('Unable to load job descriptions.');
      }
    });
  }

  openUploadDialog(): void {
    const dialogRef = this.dialog.open(UploadJobDescriptionDialog, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (!result) {
        return;
      }
      this.notificationService.success('Job description uploaded successfully.');
      this.loadJobDescriptions();
    });
  }

  deleteJobDescription(id: string): void {
    if (!confirm('Delete this job description?')) {
      return;
    }

    this.jobDescriptionService.delete(id).subscribe({
      next: () => {
        this.notificationService.success('Job description deleted successfully.');
        this.loadJobDescriptions();
      },
      error: () => {
        this.notificationService.error('Unable to delete job description.');
      }
    });
  }

}
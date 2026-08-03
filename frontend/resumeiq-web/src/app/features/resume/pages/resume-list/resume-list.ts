import { ChangeDetectorRef, Component, OnInit, inject } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { MatTableDataSource, MatTableModule } from '@angular/material/table';

import { ResumeResponse } from '../../models/resume-response';
import { ResumeService } from '../../services/resume';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { UploadResumeDialog } from '../../components/upload-resume-dialog/upload-resume-dialog';
import { MatIconModule } from "@angular/material/icon";
import { FileSizePipe } from '../../../../shared/pipes/file-size-pipe';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { NotificationService } from '../../../../core/services/notification';

@Component({
  selector: 'app-resume-list',
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatIconModule,
    MatProgressSpinnerModule,
    DatePipe,
    FileSizePipe
  ],
  templateUrl: './resume-list.html',
  styleUrl: './resume-list.scss'
})
export class ResumeList implements OnInit {

  private readonly resumeService = inject(ResumeService);
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

  dataSource = new MatTableDataSource<ResumeResponse>();

  ngOnInit(): void {
    this.loadResumes();
  }

  private loadResumes(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.resumeService.getAll().subscribe({
      next: response => {
        this.dataSource.data = response.data;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.isLoading = false;
        this.errorMessage = 'Unable to load resumes.';
        this.notificationService.error('Unable to load resumes.');
      }
    });
  }

  openUploadDialog(): void {
    const dialogRef = this.dialog.open(UploadResumeDialog, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.notificationService.success('Resume uploaded successfully.');
        this.loadResumes();
      }
    });
  }

  deleteResume(id: string): void {
    if (!confirm('Delete this resume?')) {
      return;
    }

    this.resumeService.delete(id).subscribe({
      next: () => {
        this.notificationService.success('Resume deleted successfully.');
        this.loadResumes();
      },
      error: () => {
        this.notificationService.error('Unable to delete resume.');
      }
    });
  }

}
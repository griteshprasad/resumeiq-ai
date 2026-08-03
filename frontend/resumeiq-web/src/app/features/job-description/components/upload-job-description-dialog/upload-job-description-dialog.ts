import { Component, inject } from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import {
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';

import { JobDescriptionService } from '../../services/job-description';

@Component({
  selector: 'app-upload-job-description-dialog',
  imports: [
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatButtonModule
  ],
  templateUrl: './upload-job-description-dialog.html',
  styleUrl: './upload-job-description-dialog.scss'
})
export class UploadJobDescriptionDialog {

  private readonly dialogRef = inject(MatDialogRef<UploadJobDescriptionDialog>);
  private readonly jobDescriptionService = inject(JobDescriptionService);

  selectedFile?: File;

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) {
      return;
    }
    this.selectedFile = input.files[0];
  }

  upload(): void {
    if (!this.selectedFile) {
      return;
    }

    this.jobDescriptionService.upload(this.selectedFile).subscribe({
      next: response => {
        this.dialogRef.close(response.data);
      },
      error: error => {
        console.error(error);
      }
    });
  }

}
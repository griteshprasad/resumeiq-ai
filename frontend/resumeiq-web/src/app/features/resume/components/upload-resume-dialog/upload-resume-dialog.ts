import { Component, inject } from '@angular/core';
import { MatDialogRef, MatDialogActions, MatDialogContent } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';

import { ResumeService } from '../../services/resume';

@Component({
  selector: 'app-upload-resume-dialog',
  imports: [
    MatButtonModule,
    MatDialogActions,
    MatDialogContent
  ],
  templateUrl: './upload-resume-dialog.html',
  styleUrl: './upload-resume-dialog.scss'
})
export class UploadResumeDialog {

  private readonly dialogRef = inject(MatDialogRef<UploadResumeDialog>);
  private readonly resumeService = inject(ResumeService);

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

    this.resumeService.upload(this.selectedFile).subscribe({
      next: response => {
        this.dialogRef.close(response.data);
      },
      error: error => {
        console.error(error);
      }
    });

  }

}
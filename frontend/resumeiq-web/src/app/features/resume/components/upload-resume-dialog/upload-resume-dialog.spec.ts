import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadResumeDialog } from './upload-resume-dialog';

describe('UploadResumeDialog', () => {
  let component: UploadResumeDialog;
  let fixture: ComponentFixture<UploadResumeDialog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UploadResumeDialog],
    }).compileComponents();

    fixture = TestBed.createComponent(UploadResumeDialog);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

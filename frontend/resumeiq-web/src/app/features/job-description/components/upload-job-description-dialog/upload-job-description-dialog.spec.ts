import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadJobDescriptionDialog } from './upload-job-description-dialog';

describe('UploadJobDescriptionDialog', () => {
  let component: UploadJobDescriptionDialog;
  let fixture: ComponentFixture<UploadJobDescriptionDialog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UploadJobDescriptionDialog],
    }).compileComponents();

    fixture = TestBed.createComponent(UploadJobDescriptionDialog);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

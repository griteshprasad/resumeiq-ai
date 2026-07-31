import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobDescriptionList } from './job-description-list';

describe('JobDescriptionList', () => {
  let component: JobDescriptionList;
  let fixture: ComponentFixture<JobDescriptionList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobDescriptionList],
    }).compileComponents();

    fixture = TestBed.createComponent(JobDescriptionList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

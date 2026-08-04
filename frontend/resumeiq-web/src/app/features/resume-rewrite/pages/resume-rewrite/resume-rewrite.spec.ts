import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResumeRewrite } from './resume-rewrite';

describe('ResumeRewrite', () => {
  let component: ResumeRewrite;
  let fixture: ComponentFixture<ResumeRewrite>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResumeRewrite],
    }).compileComponents();

    fixture = TestBed.createComponent(ResumeRewrite);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

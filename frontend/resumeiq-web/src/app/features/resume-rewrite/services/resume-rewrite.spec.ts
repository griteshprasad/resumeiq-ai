import { TestBed } from '@angular/core/testing';

import { ResumeRewrite } from './resume-rewrite';

describe('ResumeRewrite', () => {
  let service: ResumeRewrite;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResumeRewrite);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

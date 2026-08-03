import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtsScoreCard } from './ats-score-card';

describe('AtsScoreCard', () => {
  let component: AtsScoreCard;
  let fixture: ComponentFixture<AtsScoreCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AtsScoreCard],
    }).compileComponents();

    fixture = TestBed.createComponent(AtsScoreCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

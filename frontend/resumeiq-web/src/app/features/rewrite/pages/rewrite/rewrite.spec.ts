import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Rewrite } from './rewrite';

describe('Rewrite', () => {
  let component: Rewrite;
  let fixture: ComponentFixture<Rewrite>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Rewrite],
    }).compileComponents();

    fixture = TestBed.createComponent(Rewrite);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

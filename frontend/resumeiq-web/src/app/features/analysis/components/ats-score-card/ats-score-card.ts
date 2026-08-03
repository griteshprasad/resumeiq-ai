import { Component, computed, input } from '@angular/core';
import { CommonModule, NgClass } from '@angular/common';

import { MatCardModule } from '@angular/material/card';

import { AtsFeedback } from '../../models/ats-feedback';

@Component({
  selector: 'app-ats-score-card',
  imports: [
    CommonModule,
    NgClass,
    MatCardModule
  ],
  templateUrl: './ats-score-card.html',
  styleUrl: './ats-score-card.scss'
})
export class AtsScoreCard {

  feedback = input.required<AtsFeedback>();

  readonly radius = 70;

  readonly circumference = 2 * Math.PI * this.radius;

  readonly progressOffset = computed(() => {

    const percentage = this.feedback().atsScore;

    return this.circumference -
      (percentage / 100) * this.circumference;

  });

  readonly scoreStatus = computed(() => {

    const score = this.feedback().atsScore;

    if (score >= 80) {
      return 'Excellent ATS Match';
    }

    if (score >= 60) {
      return 'Good ATS Match';
    }

    return 'Needs Improvement';

  });

  readonly progressClass = computed(() => {

    const score = this.feedback().atsScore;

    if (score >= 80) {
      return 'good';
    }

    if (score >= 60) {
      return 'average';
    }

    return 'poor';

  });

}
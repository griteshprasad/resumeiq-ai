import { AtsFeedback } from './ats-feedback';
import { Improvement } from './improvement';

export interface AnalysisResponse {
  atsFeedback: AtsFeedback;
  improvements: Improvement[];
}
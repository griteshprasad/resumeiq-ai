import { RewriteSection } from './rewrite-section';

export interface RewriteInstruction {
  section: RewriteSection;
  goal: string;
}
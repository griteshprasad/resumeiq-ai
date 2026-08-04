import { RewriteSection } from './rewrite-section';

export interface RewrittenSection {
  section: RewriteSection;
  rewrittenContent: string;
  explanation: string;
}
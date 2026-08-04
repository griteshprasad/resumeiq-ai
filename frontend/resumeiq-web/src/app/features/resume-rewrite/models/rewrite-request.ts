import { RewriteInstruction } from './rewrite-instruction';

export interface RewriteRequest {
  resumeId: string;
  jobDescriptionId: string;
  instructions: RewriteInstruction[];
}
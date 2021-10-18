import { Story } from '../stories/story';
export class TaskWithId {
  id: string;
  issueType: string;
  description: string;
  summary: string;
  hours: number;
  issueId: number;
  story: Story;
  epicLink: string;
  complexityPoints: string;
  priority: string;
  components: string;
  fixVersions: string;
  labels: string;
  dueDate: string;
  team: string;
  originalEstimate: number;
}

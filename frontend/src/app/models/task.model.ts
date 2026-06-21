export interface Task {
  id: number;
  name: string;
  description: string;
  status: 'Pending' | 'Completed';
  createdDate: string;
}

export interface TaskRequest {
  name: string;
  description: string;
}

export interface StatusUpdate {
  status: string;
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Task } from '../models/task.model';
import { TaskRequest } from '../models/task.model';
import { environment } from '../../environments/environment';

@Injectable({
providedIn: 'root'
})
export class TaskService {

apiUrl = 'https://task-management-system-oh5e.onrender.com/api/tasks';

constructor(private http: HttpClient) { }

getAllTasks(): Observable<Task[]> {


return this.http.get<Task[]>(this.apiUrl);


}

createTask(task: TaskRequest): Observable<Task> {


return this.http.post<Task>(
  this.apiUrl,
  task
);


}

markAsCompleted(id: number): Observable<Task> {


return this.http.patch<Task>(
  this.apiUrl + '/' + id + '/status',
  {
    status: 'Completed'
  }
);


}

deleteTask(id: number): Observable<any> {


return this.http.delete(
  this.apiUrl + '/' + id
);


}

}

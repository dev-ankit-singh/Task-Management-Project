import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  totalTasks = 0;
  pendingTasks = 0;
  completedTasks = 0;

  loading = false;
  error = '';

  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
    this.getTasks();
  }

  getTasks() {

    this.loading = true;

    this.taskService.getAllTasks().subscribe(
      (data: any) => {

        this.totalTasks = data.length;

        this.pendingTasks = data.filter(
          (task: any) => task.status == 'Pending'
        ).length;

        this.completedTasks = data.filter(
          (task: any) => task.status == 'Completed'
        ).length;

        this.loading = false;
      },
      () => {
        this.error = 'Backend is not running.';
        this.loading = false;
      }
    );
  }
}
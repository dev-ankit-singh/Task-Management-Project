import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../services/task.service';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  allTasks: Task[] = [];
  filteredTasks: Task[] = [];

  isLoading = false;
  errorMessage = '';

  searchText = '';
  selectedStatus = 'All';

  deleteConfirmId: number | null = null;

  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks() {

    this.isLoading = true;

    this.taskService.getAllTasks().subscribe({

      next: (data) => {

        this.allTasks = [...data].reverse();

        this.filteredTasks = this.allTasks;

        this.applyFilter();

        this.isLoading = false;
      },

      error: () => {

        this.errorMessage = 'Unable to load tasks';

        this.isLoading = false;
      }

    });
  }

  applyFilter() {

    let tasks = [...this.allTasks];

    if (this.selectedStatus !== 'All') {
      tasks = tasks.filter(
        task => task.status === this.selectedStatus
      );
    }

    if (this.searchText.trim() !== '') {

      const text = this.searchText.toLowerCase();

      tasks = tasks.filter(task =>
        task.name.toLowerCase().includes(text) ||
        task.description.toLowerCase().includes(text)
      );
    }

    this.filteredTasks = tasks;
  }

  onSearchChange() {
    this.applyFilter();
  }

  onStatusFilterChange() {
    this.applyFilter();
  }

  markCompleted(id: number) {

    this.taskService.markAsCompleted(id).subscribe({

      next: () => {
        this.loadTasks();
      },

      error: () => {
        alert('Could not update task');
      }

    });
  }

  confirmDelete(id: number) {
    this.deleteConfirmId = id;
  }

  cancelDelete() {
    this.deleteConfirmId = null;
  }

  deleteTask(id: number) {

    this.taskService.deleteTask(id).subscribe({

      next: () => {

        this.loadTasks();

        this.deleteConfirmId = null;
      },

      error: () => {

        alert('Could not delete task');

        this.deleteConfirmId = null;
      }

    });
  }

}
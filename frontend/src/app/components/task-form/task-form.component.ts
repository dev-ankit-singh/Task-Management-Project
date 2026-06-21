import { Router } from '@angular/router';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent implements OnInit {

  @Output() taskCreated = new EventEmitter<void>();

  taskForm!: FormGroup;
  isSubmitting: boolean = false;
  successMessage: string = '';
  errorMessage: string = '';

constructor(
  private fb: FormBuilder,
  private taskService: TaskService,
  private router: Router
) { }

  ngOnInit(): void {
    this.taskForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      description: ['']
    });
  }

  get nameField() {
    return this.taskForm.get('name');
  }

  onSubmit() {
    if (this.taskForm.invalid) {
      this.taskForm.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;
    this.successMessage = '';
    this.errorMessage = '';

    const taskData = {
      name: this.taskForm.value.name.trim(),
      description: this.taskForm.value.description?.trim() || ''
    };

    this.taskService.createTask(taskData).subscribe({
      next: () => {
        this.successMessage = 'Task created successfully!';
        this.taskForm.reset();
        this.isSubmitting = false;
        this.taskCreated.emit();
        this.router.navigate(['/tasks']);
       
        setTimeout(() => {
          this.successMessage = '';
        }, 3000);
      },
      error: (err) => {
        this.errorMessage = 'Failed to create task. Please try again.';
        this.isSubmitting = false;
      }
    });
  }

  onReset() {
    this.taskForm.reset();
    this.successMessage = '';
    this.errorMessage = '';
  }
}

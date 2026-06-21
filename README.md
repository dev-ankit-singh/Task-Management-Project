# Task Management Application

This is a simple Task Management application developed using Angular for the frontend and Spring Boot for the backend. The application allows users to create, view, update, and delete tasks. It is designed to demonstrate basic full-stack development concepts and REST API integration.

# Technologies Used

# Frontend

* Angular 17
* TypeScript
* Reactive Forms

# Backend

* Java 17
* Spring Boot 3
* Spring Web
* Spring Validation

# Storage

* In-Memory List (No database required)

# Features

* Add a new task
* View all tasks
* Mark tasks as completed
* Delete tasks
* Search tasks by name or description
* Filter tasks by status
* Dashboard showing total, pending, and completed tasks
* Responsive user interface
* Form validation for task creation

# Project Structure

The project is divided into two parts:

# Backend

The backend is developed using Spring Boot and follows a simple layered architecture.

* Controller Layer handles API requests.
* Service Layer contains business logic.
* Model Layer stores task-related classes.

# Frontend

The frontend is built using Angular.

* Components are used for UI screens.
* Services handle API communication.
* Reactive Forms are used for validation.

# Working of the Application

When the application starts, five sample tasks are loaded automatically. Users can create new tasks using the form provided on the dashboard. All tasks are displayed in a table where users can update task status or delete tasks.

The Angular frontend communicates with the Spring Boot backend through REST APIs. Whenever a task is added, updated, or deleted, the frontend fetches the latest data from the backend to keep the information updated.

# Validation

Task name is mandatory.

# Frontend Validation

Angular Reactive Forms are used to validate user input before submitting the form.

# Backend Validation

Spring Boot validation annotations are used to validate incoming requests and return proper error messages if required fields are missing.

# REST API Endpoints
I have created the following API endpoints in the backend:

GET /api/tasks
This endpoint returns the list of all tasks stored in memory.

POST /api/tasks
This endpoint is used to create a new task. 
The task name is required.

PATCH /api/tasks/{id}/status
This endpoint is used to mark a task as completed 
by passing the task id.

DELETE /api/tasks/{id}
This endpoint is used to delete a task permanently 
by passing the task id.

# Conclusion

This project helped me understand the basics of full-stack application development using Angular and Spring Boot. Through this project, I learned how frontend and backend communicate using REST APIs, how form validation works, and how to organize code using a layered architecture.

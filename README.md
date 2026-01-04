# 📋 Employee & Task Management Application

## 📖 Project Overview
This application is a desktop software solution designed for **Project Managers** and HR professionals to efficiently manage company staff and project workflows.

It replaces manual task tracking with a digital system that supports complex project hierarchies (projects composed of multiple sub-tasks) and provides real-time analytics on employee performance and workload.

---

## 🚀 Key Features

### 1. 👥 Employee Management
* **Onboarding:** Easily add new employees to the company database.
* **Dashboard:** View a complete list of employees and their currently assigned tasks in a clear, tabular format.

### 2. 🗂️ Advanced Task Structure
The system uses the **Composite Design Pattern** to handle different types of work:
* **Simple Tasks:** Basic tasks with a specific start and end time.
* **Complex Tasks:** Large projects that act as "containers" for other Simple or Complex tasks.
* **Logic:** This structure allows managers to break down large projects into manageable sub-tasks.

### 3. ⚙️ Smart Assignment Logic
To ensure accurate project tracking, the system includes validation rules:
* **Assignment Status:** Every task tracks whether it is currently "Assigned" or "Free".
* **Hierarchy Protection:** When a Complex Task is assigned to an employee, all of its internal sub-tasks are automatically marked as "Assigned". This prevents double-booking and ensures that sub-tasks cannot be re-assigned to other people while the main project is active.

### 4. 📊 Analytics & Reporting
The application includes a built-in statistics engine to assist HR and Management:
* **Overtime Alert:** Automatically filters and sorts employees who have an estimated workload exceeding **40 hours**.
* **Productivity Report:** Calculates and displays the specific count of "Completed" vs. "Uncompleted" tasks for each employee.

### 5. 📝 Status Management
* Managers can manually update the status of tasks (e.g., marking them "Completed" once finished).

### 6. 💾 Data Persistence
* **Auto-Save:** The application uses **Java Serialization** to save all data locally. This ensures that employees, tasks, and assignment records are preserved between sessions and are never lost when the application is closed.

---

## 🛠️ Technical Details (For Technical Reviewers)

This project demonstrates proficiency in modern software engineering principles and Object-Oriented Design.

* **Programming Language:** Java

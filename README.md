#  Employee & Task Management System

##  Project Overview
This application is a comprehensive desktop software solution designed for **Project Managers** to efficiently organize employees, manage complex project hierarchies, and track team performance.

Moving away from manual tracking, this system digitizes the assignment workflow, ensuring that complex projects—composed of multiple smaller tasks—are handled correctly without administrative errors or scheduling conflicts.

---

##  Key Features

### 1.  Employee Management
* [cite_start]**Onboarding:** Simple interface to add new employees to the company database[cite: 40].
* [cite_start]**Workload Overview:** A dashboard view that displays all employees and their currently assigned tasks[cite: 41].

### 2.  Advanced Task Structure (Composite Pattern)
[cite_start]The system distinguishes between two types of work items to mimic real-world project structures[cite: 31, 32, 33]:
* **Simple Tasks:** Standard tasks defined by a specific start and end time (duration is calculated automatically).
* **Complex Tasks:** "Container" tasks that are composed of multiple Simple Tasks or sub-projects.
    * *Business Value:* This allows managers to assign a whole project phase (a Complex Task) to an employee, which automatically includes all the necessary sub-tasks.

### 3.  Intelligent Assignment Logic
To ensure data integrity and prevent double-booking:
* **Assignment Locking:** When a Complex Task is assigned to an employee, all internal sub-tasks are automatically marked as "Assigned" in the system.
* **Conflict Prevention:** These locked sub-tasks are visually indicated in the dashboard so they cannot be accidentally re-assigned to a different employee.

### 4. Analytics & HR Statistics
[cite_start]The application includes a built-in utility engine to help HR and Management monitor performance[cite: 37, 38]:
* **Overtime Monitor:** Automatically filters and displays employees with an estimated workload exceeding **40 hours**, sorted by duration.
* **Progress Tracking:** Generates reports showing the count of **Completed** vs. **Uncompleted** tasks for every employee.

### 5.  Data Persistence
* [cite_start]**Auto-Save:** All data (employees, task hierarchies, and statuses) is saved locally using **Java Serialization**[cite: 43]. This ensures work is never lost between sessions.

---

##  Technical Technology Stack

This project showcases proficiency in modern software engineering principles, suitable for review by Technical Recruiters:

* **Language:** Java (Object-Oriented Programming).
* [cite_start]**Architecture:** Layered Architecture (separation of GUI, Business Logic, and Data Model)[cite: 54].
* [cite_start]**Design Pattern:** **Composite Design Pattern** used to handle the hierarchy of Simple and Complex tasks seamlessly[cite: 58].
* [cite_start]**User Interface:** **Java Swing** for a responsive desktop Graphical User Interface (GUI)[cite: 54].
* [cite_start]**Data Structures:** Advanced use of Java Collections (`Map`, `List`) to manage relationships between employees and tasks[cite: 16].
* [cite_start]**Advanced Java Features:** Implementation of **Sealed Classes** [cite: 31] and Abstract Classes for robust code security.

---

##  How to Use (Workflow)

1.  **Launch:** Open the application to see the main dashboard.
2.  **Create:** Use the input forms to create Employees or Tasks. When creating a Complex Task, you can add existing tasks to it.
3.  **Assign:** Select a task and an employee to link them.
    * *Note:* Assigning a parent task automatically locks its children.
4.  **Monitor:** Use the table view to check status. [cite_start]Click "Statistics" to view the 40+ hour workload report[cite: 42].
5.  [cite_start]**Update:** As work finishes, the Project Manager modifies the task status to "Completed"[cite: 36].

---

*This project was developed as part of the Fundamental Programming Techniques curriculum, demonstrating the practical application of Design Patterns and GUI development.*

# SBB6_TODO Web Application

## Overview
This is a full-stack web application that allows users to manage their tasks (To-Do List) in a simple and intuitive interface. It is built using Java (for the backend) and JSP/HTML (for the frontend), with real-time user authentication and database connectivity for persistent storage.

The application supports user registration, login, adding tasks, viewing tasks, and marking tasks as completed.

## Features
- **User Authentication**: Secure user login and registration with session management.
- **Task Management**: Add new tasks, view tasks, and mark them as completed.
- **Database Connectivity**: Data is stored in a relational database, ensuring tasks are persisted across sessions.
- **Real-Time Authentication**: Real-time login and registration with immediate access to the task list upon successful authentication.

## Technologies Used
- **Backend**: Java (Servlets, JDBC)
- **Frontend**: JSP, HTML, CSS
- **Database**: MySQL or any JDBC-supported database
- **Authentication**: Real-time user authentication using Java sessions

## Project Structure

### 1. Backend (Java)
- **src/main/java**: Contains Java classes for backend logic.
  - **beans**: Contains Java beans like `Task.java` (model for tasks) and `Register.java` (user registration).
  - **dao**: Includes `ToDoDAO.java` (data access object for tasks) and `ToDoDAOImpl.java` (implementation of the DAO).
  - **servlets**: Servlets for handling requests (adding tasks, registering, logging in, etc.)
    - `AddTaskServlet.java`
    - `LoginServlet.java`
    - `RegisterServlet.java`
    - `LogoutServlet.java`
    - `MarkTaskCompletedServlet.java`
  - **factory**: Database connection factory (`DBConn.java`) to handle database connections.

### 2. Frontend (JSP/HTML)
- **src/main/webapp**: Contains JSP and HTML files for the UI.
  - **Login.jsp**: Login page for users.
  - **Register.html**: Registration page for new users.
  - **ViewTasks.jsp**: Displays the list of tasks with the ability to mark them as completed.

### 3. Database
- The database is used for storing user credentials and tasks. The `ToDoDAO.java` is responsible for handling CRUD operations.
  
### 4. Configuration
- **web.xml**: Web configuration file for servlets and URL mapping.
  
### 5. Target Directory
- **target**: This directory contains the compiled `.class` files and the final `.war` file (deployable web archive).

### 6. Maven
- **pom.xml**: Maven configuration for project dependencies and build management.

## Installation

### Prerequisites
- Java 8 or higher
- Maven
- A relational database (e.g., MySQL) or JDBC-compatible database
- Apache Tomcat or any other servlet container to run the web application

### Steps to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Vishwas-Chakilam/SBB6_TODO.git
   ```

2. Navigate to the project directory:
   ```bash
   cd SBB6_TODO
   ```

3. Configure the database connection in `DBConn.java` (in `src/main/java/factory/DBConn.java`).
   - Update the database URL, username, and password for your MySQL or other relational database.

4. Build the project with Maven:
   ```bash
   mvn clean install
   ```

5. Deploy the `.war` file located in `target/` folder to your servlet container (e.g., Apache Tomcat).

6. Open the application in your browser:
   ```bash
   http://localhost:8080/todo
   ```

## Usage

1. **Register a new account**: Visit the `Register.html` page to create a new account.
2. **Login**: After registering, use the `Login.jsp` page to log in.
3. **Manage Tasks**: Once logged in, you can add tasks, view tasks, and mark them as completed on the `ViewTasks.jsp` page.

## Contributing
Feel free to fork the repository, make changes, and submit a pull request. Contributions are welcome!

## License
This project is open-source and available under the MIT License.

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import beans.Register;
import beans.Task;
import factory.DBConn;

public class ToDoDAOImpl implements ToDoDAO {

    private Connection con;
    private static ToDoDAO toDoDAO;

    // Private constructor for Singleton pattern
    private ToDoDAOImpl() {
        try {
            con = DBConn.getConn();
            if (con == null) {
                throw new RuntimeException("Database connection failed! Check DBConn.java.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Singleton Instance Getter
    public static ToDoDAO getInstance() {
        if (toDoDAO == null) {
            toDoDAO = new ToDoDAOImpl();
        }
        return toDoDAO;
    }

    @Override
    public int register(Register register) {
        int regid = 0;
        String getMaxRegIdQuery = "SELECT MAX(regid) FROM register";
        String insertUserQuery = "INSERT INTO register VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(getMaxRegIdQuery);
             PreparedStatement pstmt = con.prepareStatement(insertUserQuery)) {

            if (rs.next()) {
                regid = rs.getInt(1);
            }
            regid++;

            pstmt.setInt(1, regid);
            pstmt.setString(2, register.getFname());
            pstmt.setString(3, register.getLname());
            pstmt.setString(4, register.getEmail());
            pstmt.setString(5, register.getPass());
            pstmt.setLong(6, register.getMobile());
            pstmt.setString(7, register.getAddress());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted == 1) {
                System.out.println("✅ Record inserted into register table");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regid;
    }

    @Override
    public int login(String email, String pass) {
        int regId = 0;
        String loginQuery = "SELECT regid FROM register WHERE email = ? AND pass = ?";

        try (PreparedStatement pstmt = con.prepareStatement(loginQuery)) {
            pstmt.setString(1, email);
            pstmt.setString(2, pass);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    regId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regId;
    }

    @Override
    public boolean addTask(Task task, int regid) {
        int taskId = 0;
        boolean isNew = true;
        boolean flag = false;

        String checkTaskQuery = "SELECT taskid FROM taskid_pks WHERE regid = ?";
        String insertTaskQuery = "INSERT INTO tasks VALUES (?, ?, ?, ?, ?)";
        String insertTaskIdQuery = "INSERT INTO taskid_pks VALUES (?, ?)";
        String updateTaskIdQuery = "UPDATE taskid_pks SET taskid = ? WHERE regid = ?";

        try (PreparedStatement checkTaskStmt = con.prepareStatement(checkTaskQuery);
             PreparedStatement insertTaskStmt = con.prepareStatement(insertTaskQuery);
             PreparedStatement insertTaskIdStmt = con.prepareStatement(insertTaskIdQuery);
             PreparedStatement updateTaskIdStmt = con.prepareStatement(updateTaskIdQuery)) {

            checkTaskStmt.setInt(1, regid);
            try (ResultSet rs = checkTaskStmt.executeQuery()) {
                if (rs.next()) {
                    isNew = false;
                    taskId = rs.getInt(1);
                }
            }
            taskId++;

            con.setAutoCommit(false);
            insertTaskStmt.setInt(1, taskId);
            insertTaskStmt.setString(2, task.getTaskName());
            insertTaskStmt.setString(3, task.getTaskDate());
            insertTaskStmt.setInt(4, task.getTaskStatus());
            insertTaskStmt.setInt(5, task.getTaskregid());

            int taskInserted = insertTaskStmt.executeUpdate();
            int taskIdUpdated = 0;

            if (isNew) {
                insertTaskIdStmt.setInt(1, regid);
                insertTaskIdStmt.setInt(2, taskId);
                taskIdUpdated = insertTaskIdStmt.executeUpdate();
            } else {
                updateTaskIdStmt.setInt(1, taskId);
                updateTaskIdStmt.setInt(2, regid);
                taskIdUpdated = updateTaskIdStmt.executeUpdate();
            }

            if (taskInserted == 1 && taskIdUpdated == 1) {
                con.commit();
                flag = true;
                System.out.println("✅ Task added successfully.");
            } else {
                con.rollback();
                System.out.println("❌ Transaction failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Task> findAllTasksByRegId(int regid) {
        List<Task> taskList = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE regid = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, regid);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                    taskList.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    @Override
    public boolean markTaskCompleted(int regid, int taskid) {
        boolean flag = false;
        String updateQuery = "UPDATE tasks SET taskstatus = 3 WHERE regid = ? AND taskid = ?";

        try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
            pstmt.setInt(1, regid);
            pstmt.setInt(2, taskid);
            int updatedRows = pstmt.executeUpdate();
            if (updatedRows == 1) {
                flag = true;
                System.out.println("✅ Task " + taskid + " of " + regid + " marked as completed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public String getFLNameByRegID(int regId) {
        String flname = "";
        String query = "SELECT fname, lname FROM register WHERE regid = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, regId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    flname = rs.getString(1) + " " + rs.getString(2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flname;
    }
}

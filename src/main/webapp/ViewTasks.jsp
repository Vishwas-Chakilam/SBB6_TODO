<%@page import="beans.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Tasks</title>
</head>
<body>
  <p align="right">
    <%
      Object obj=session.getAttribute("regId");
      Integer in=(Integer)obj;
      int regId=in.intValue();
      
      dao.ToDoDAO dao1=dao.ToDoDAOImpl.getInstance();
      String flname=dao1.getFLNameByRegID(regId);
    %>
    Welcome <%=flname%>, <a href="./LogoutServlet">Logout</a>  
  </p>
  
  <form method="post" action="./AddTaskServlet">
    <table border="1" align="center" width="20%">
      <tr>
        <th>Task Name</th>
        <td><input type="text" name="taskName"></td>
      </tr>
      <tr>
        <th>Task Date</th>
        <td><input type="date" name="taskDate"></td>
      </tr>
      <tr>
        <th>Task Status</th>
        <td>
          <select name="taskStatus" size="1">
            <option value="1">Not Yet Started</option>
            <option value="2">In Progress</option>
            <option value="3">Completed</option>
          </select>
        </td>
      </tr>
      <tr>
        <th><input type="submit" name="submit" value="Add Task"></th>
        <td><input type="reset" name="reset" value="Clear"></td>
      </tr>
    </table>
  </form>
  
  <hr/>
  
  <%
    List<Task> tasks=dao1.findAllTasksByRegId(regId);
  %>
  <table border="1" width="50%" align="center">
    <tr>
      <th>Task ID</th>
      <th>Task Name</th>
      <th>Task Date</th>
      <th>Task Status</th>
      <th></th>
    </tr>
    <%
      for(Task task:tasks) {
        int taskId=task.getTaskid();
        String taskName=task.getTaskName();
        String taskDate=task.getTaskDate();
        int taskStatus=task.getTaskStatus();
    %>
        <%
          if(taskStatus==3) {
        %>
            <tr style="text-decoration:line-through;">
              <td><%=taskId%></td>
              <td><%=taskName%></td>
              <td><%=taskDate%></td>
              <td><%=taskStatus%></td>
              <td>Completed</td>
            </tr>
        <%
          } else {
        %>
            <tr>
              <td><%=taskId%></td>
              <td><%=taskName%></td>
              <td><%=taskDate%></td>
              <td><%=taskStatus%></td>
              <td><a href="./MarkTaskCompletedServlet?regId=<%=regId%>&taskId=<%=taskId%>">Complete</a></td>
            </tr>
        <%
          }
        %>
    <%
      }
    %>
  </table>
</body>
</html>
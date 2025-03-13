<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
  <p style="colour:red;background-colour:yellow;
                          font-style:italic;">
               <%Object error=request.getAttribute("loginError"); %>
               <%=(error==null)?"":error.toString() %>
               </p>
</center>
<form method="post" action="./LoginServlet">
    <table align="center" border="1" width="17%">
      <tr>
        <th>Email</th>
        <td><input type="text" name="email" value=""></td>
      </tr>
      <tr>
        <th>Pass</th>
        <td><input type="password" name="pass" value=""></td>
      </tr>
      <tr>
        <th><input type="submit" name="submit" value="Login"></th>
        <td><input type="reset" name="reset" value="Clear"></td>
      </tr>

    </table>
  </form>
  <p align="center">New User, <a href="Register.html">SignUp</a></p>  
</body>
</html>
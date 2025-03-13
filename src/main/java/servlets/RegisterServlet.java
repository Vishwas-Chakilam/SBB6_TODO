package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Register;
import dao.ToDoDAO;
import dao.ToDoDAOImpl;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// read form data
		String fname = request.getParameter("fname").trim();
		String lname = request.getParameter("lname").trim();
		String email = request.getParameter("email").trim();
		String pass = request.getParameter("pass").trim();
		long mobile = Long.parseLong(request.getParameter("mobile").trim());
		String address = request.getParameter("address").trim();

		// call the dao method
		ToDoDAO dao = ToDoDAOImpl.getInstance();
		Register r = new Register(0, fname, lname, email, pass, mobile, address);
		int i = dao.register(r);
		if (i > 0)
			response.sendRedirect("Login.jsp");
		else
			out.println("registration Failed");
	}
}
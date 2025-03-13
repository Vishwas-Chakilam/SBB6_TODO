package dao;

import java.util.List;

import beans.Register;
import beans.Task;

public interface ToDoDAO {

	public abstract int register(Register register);
	public abstract int login(String email, String pass);
	public abstract boolean addTask(Task task, int regid);
	public abstract List<Task> findAllTasksByRegId(int regid);
	public abstract boolean markTaskCompleted(int taskid, int regid);
	public String getFLNameByRegID(int regId);
}
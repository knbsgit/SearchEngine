package searchEngine;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class TaskBean implements Comparable<TaskBean>
{
	String TaskName;
	String TaskDesc;
	
	Date date = new Date();
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	Long CreatedTime =  timestamp.getTime();
	
	Date dateAndTime  = new Date(CreatedTime);
	
	
	public TaskBean(String taskName, String taskDesc, Date dateAndTime) {
		super();
		TaskName = taskName;
		TaskDesc = taskDesc;
		this.dateAndTime = dateAndTime;
	}
	
	public String getTaskName()
	{
		return TaskName;
	}
	
	public void setTaskName(String taskName) throws IllegalAccessException
	{
		if (taskName == null && taskName.equals(""))
			throw new IllegalAccessException("The Task Tittle cannot be be empty!!!");
		this.TaskName = taskName;
	}
	
	public String getTaskDesc()
	{
		return TaskDesc;
	}
	
	public void setTaskDesc(String taskDesc) throws IllegalAccessException
	{	
		if (taskDesc == null && taskDesc.equals(""))
			throw new IllegalAccessException("The Task Description cannot be empty!!!");
		this.TaskDesc = taskDesc;
	}
	public long getCreatedTime()
	{
		return CreatedTime;
	}
	public TaskBean(String taskName, String taskDesc) {
		super();
		TaskName = taskName;
		TaskDesc = taskDesc;
	}
	
	public TaskBean(String taskName, String taskDesc, long createdTime) 
	{
		super();
		TaskName = taskName;
		TaskDesc = taskDesc;
		CreatedTime = createdTime;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(CreatedTime, TaskDesc, TaskName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		return Objects.equals(CreatedTime, other.CreatedTime) && Objects.equals(TaskDesc, other.TaskDesc)
				&& Objects.equals(TaskName, other.TaskName);
	}
	@Override
	public String toString() {
		return TaskName + ":"+ TaskDesc + ":" + CreatedTime + ":" + dateAndTime;
	}

	@Override
	public int compareTo(TaskBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
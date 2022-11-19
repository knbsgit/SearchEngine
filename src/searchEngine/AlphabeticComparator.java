package searchEngine;

import java.util.Comparator;

public class AlphabeticComparator implements Comparator<TaskBean> 
{
	public int compare(TaskBean t1, TaskBean t2) 
	{
		return t1.getTaskName().compareTo(t2.getTaskName());
	};
}
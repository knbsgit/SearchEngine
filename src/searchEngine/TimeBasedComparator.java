package searchEngine;

import java.util.Comparator;

public class TimeBasedComparator implements Comparator<TaskBean> 
{
	public int compare(TaskBean t1, TaskBean t2) 
	{
		return (int)(t2.getCreatedTime()-t1.getCreatedTime());
	};
}

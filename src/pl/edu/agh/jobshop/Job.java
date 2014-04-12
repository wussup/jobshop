package pl.edu.agh.jobshop;

import java.util.ArrayList;
import java.util.List;

public class Job {
	
	private List<Integer> tasks;
	
	public Job(List<Integer> tasks)
	{
		this.tasks = tasks;
	}
	
	public Job(int[] tasks)
	{
		this.tasks = new ArrayList<>();
		for (int task: tasks)
		{
			this.tasks.add(task);
		}
	}

	public List<Integer> getTasks() {
		return tasks;
	}

	public void setTasks(List<Integer> tasks) {
		this.tasks = tasks;
	}

}

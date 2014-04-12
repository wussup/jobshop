package pl.edu.agh.jobshop;

import java.util.List;

public class JobShop {

	private int machines;
	private List<Job> jobs;

	public JobShop(int numOfMachines, List<Job> jobs) {
		this.machines = numOfMachines;
		this.jobs = jobs;
	}

	public JobOrder createOrder() {
		return new JobOrder(jobs.size());
	}

	public int getMakespan(JobOrder order_) {
/*
		int[] finishTime = new int[machines+1];
		// machines[0] - zabezpieczenie OutOfRangeException
		
		for(Integer jobNum : order.toList()) {
			
			Job job = jobs.get(jobNum);
			
			for(int i=1; i<machines+1; ++i) {
				
				if(finishTime[i-1] < finishTime[i]) {
					// poprzednie skonczone, zacznij od zaraz
					finishTime[i] += finishTime[i] + job.getTime(i-1);
				}
				else {
					// czekaj na zakonczenie na poprzedniej maszynie
					finishTime[i] = finishTime[i-1] + job.getTime(i-1);
				}
			}
		}
		
		int makespan = finishTime[finishTime.length-1];
		return makespan;
		*/


		// @up nie dziala


		Integer[] order = new Integer[order_.toList().size()];
		order = order_.toList().toArray(order);

		//Integer[] order = (Integer[]) order.toList().toArray();
		int[][] time = new int[jobs.size()][machines];

		for(int i=0; i<jobs.size(); ++i) {
			for(int j=0; j<machines; ++j) {
				time[i][j] = jobs.get(i).getTasks().get(j);
			}
		}

		int jobNum = time.length;
		int machNum = time[0].length;
		int[][] endTime = new int[jobNum][];
		for (int i = 0; i < jobNum; ++i)
			endTime[i] = new int[machNum];
		// filling in the end times of the first job
		int[] currJobEndTime = endTime[0];
		int currJob = order[0];
		currJobEndTime[0] = time[currJob][0];
		for (int i = 1; i < machNum; ++i)
			currJobEndTime[i] = currJobEndTime[i - 1] + time[currJob][i];
		// filling in the end times on the first machine
		for (int orderIter = 1; orderIter < jobNum; ++orderIter)
			endTime[orderIter][0] = endTime[orderIter - 1][0]
					+ time[order[orderIter]][0];
		for (int i = 1; i < jobNum; ++i) {
			currJob = order[i];
			for (int currMach = 1; currMach < machNum; ++currMach)
				endTime[i][currMach] = Math.max(endTime[i - 1][currMach],
						endTime[i][currMach - 1]) + time[currJob][currMach];
		}

		int makespan = endTime[jobNum - 1][machNum - 1];
		return makespan;

	}
	
}

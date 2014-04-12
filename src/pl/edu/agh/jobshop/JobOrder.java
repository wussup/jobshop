package pl.edu.agh.jobshop;

import java.util.*;

class JobOrder {
	
	private List<Integer> order;
	
	public JobOrder(int jobsCount) {
		order = new ArrayList<>();
		for(int i=0; i<jobsCount; ++i) order.add(i);
	}
	
	public List<Integer> toList() {
		return Collections.unmodifiableList(order);
	}
	
	public void permute() {
		Collections.shuffle(order);
	}
	
	public double distance(JobOrder other) {
		double distance = 0.0;
		for(int i=0; i<order.size(); ++i)
			if(order.get(i) != other.order.get(i))
				distance += 2.0;
		return distance;
	}
	
	public double[][] getPriorities() {
		int dim = order.size();
		double[][] priorities = new double[dim][dim];
		for(int i=0; i<dim; ++i)
			priorities[order.get(i)][i] = 1.0;
		return priorities;
	}
	
	public void updateByPriorities(double[][] priorities) {
		
		normalizePriorities(priorities);
		
		int dim = order.size();
		int[] newOrder = new int[dim];
		
		List<Integer> skipThis = new ArrayList<>();
		
		for(int i=0; i<dim; ++i) {
			int pos = getMaxPosition(priorities[i], skipThis);
			newOrder[pos] = i;
			skipThis.add(pos);
		}
		
		for(int i=0; i<dim; ++i) {
			order.set(i, newOrder[i]);
		}
	}
	
	private int getMaxPosition(double[] data, List<Integer> skipThis) {
		
		double maxVal = Double.MIN_VALUE;
		int maxPos = -1;
		
		for(int i=0; i<data.length; ++i) {
			
			if(skipThis.contains(i)) continue;
			
			if(data[i] > maxVal) {
				maxVal = data[i];
				maxPos = i;
			}
		}
		
		return maxPos;
	}
	
	private double sigmoidFuction(double d) {
		return 1.0 / (1.0 + Math.exp(-d));
	}
	
	private void normalizePriorities(double[][] priorities) {
		for(int i=0; i<priorities.length; ++i)
			for(int j=0; j<priorities.length; ++j)
				priorities[i][j] = sigmoidFuction(priorities[i][j]);
	}
}

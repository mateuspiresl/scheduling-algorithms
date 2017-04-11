package mateuspires.so.scheduling;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import mateuspires.so.Process;

public abstract class SchedulingAlgorithm {

	protected LinkedList<Process> queue = new LinkedList<>();;
	
	public SchedulingAlgorithm() { }
	
	public final SchedulingAlgorithm add(Process process)
	{
		ListIterator<Process> iterator = this.queue.listIterator(this.queue.size());
		
		while (iterator.hasPrevious()) {
			if (compareProcesses(iterator.previous(), process) <= 0)
			{
				iterator.next();
				iterator.add(process);
				return this;
			}
		}
		
		iterator.add(process);
		return this;
	}
	
	public final SchedulingAlgorithm add(List<Process> processes)
	{
		for (Process process : processes)
			add(process.clone());
		
		return this;
	}
	
	protected abstract int compareProcesses(Process a, Process b);
	
	public abstract List<Process> run();
	
}

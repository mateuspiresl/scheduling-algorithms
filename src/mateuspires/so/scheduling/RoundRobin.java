package mateuspires.so.scheduling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import mateuspires.so.Process;

public class RoundRobin extends SchedulingAlgorithm {

	private final int quantum;
	
	public RoundRobin(int quantum) {
		super();
		this.quantum = quantum;
	}
	
	@Override
	protected int compareProcesses(Process a, Process b) {
		return a.arrived - b.arrived;
	}
	
	@Override
	public List<Process> run()
	{
		List<Process> executed = new ArrayList<Process>();
		Queue<Process> ready = new LinkedList<>();
		int clock = 0;
		
		while (!super.queue.isEmpty() || !ready.isEmpty())
		{
			Process process = ready.poll();
			
			if (process != null)
			{
				clock = process.execute(clock, this.quantum);
				
				if (process.isDone())
				{
					executed.add(process);
					process = null;
				}
			}
			
			while (super.queue.size() > 0 && super.queue.peekFirst().arrived <= clock)
				ready.add(super.queue.pollFirst());
			
			if (process != null) ready.add(process);
		}
		
		return executed;
	}

}

package mateuspires.so.scheduling;

import java.util.ArrayList;
import java.util.List;

import mateuspires.so.Process;

public class FirstComeFirstServed extends SchedulingAlgorithm {

	public FirstComeFirstServed() {
		super();
	}

	@Override
	protected int compareProcesses(Process a, Process b) {
		return a.arrived - b.arrived;
	}
	
	@Override
	public List<Process> run()
	{
		List<Process> executed = new ArrayList<Process>();
		int clock = 0;
		
		while (!this.queue.isEmpty())
		{
			Process process = this.queue.pollFirst();
			clock = process.execute(clock < process.arrived ? process.arrived : clock);
			executed.add(process);
		}
		
		return executed;
	}
}

package mateuspires.so.scheduling;

import java.util.ArrayList;
import java.util.List;

import mateuspires.so.Process;
import mateuspires.so.Program;

public class ShortestJobFirst extends SchedulingAlgorithm {

	private int clock = 0;
	
	public ShortestJobFirst() {
		super();
	}
	
	@Override
	protected int compareProcesses(Process a, Process b)
	{
		// Program.log(a.getSimpleInfo() + " / " + b.getSimpleInfo());
		// Program.log((a.arrived < this.clock) + " " + (b.arrived < this.clock) + " " + (a.arrived != b.arrived));
		
		if ((a.arrived > this.clock || b.arrived > this.clock) && a.arrived != b.arrived)
			return a.arrived - b.arrived;
		
		int aPoints = a.arrived + a.duration - this.clock;
		int bPoints = b.arrived + b.duration - this.clock;
		
		return aPoints - bPoints;
	}
	
	@Override
	public List<Process> run()
	{
		List<Process> executed = new ArrayList<Process>();
		
		while (!super.queue.isEmpty())
		{
			Process process = this.queue.pollFirst();
			Program.log("clock: " + clock + ", arrived: " + process.arrived + ", duration: " + process.duration);
			
			if (super.queue.size() > 1 && clock > process.arrived)
			{
				super.add(process);
				
				if (super.queue.peekFirst() == process)
					super.queue.pollFirst();
				
				else continue;
			}
			
			this.clock = process.execute(this.clock < process.arrived ? process.arrived : this.clock);
			executed.add(process);
		}
		
		return executed;
	}
	
}

package mateuspires.so;

public class Process implements Cloneable {

	public final int arrived, duration;
	private int lastClock = 0, execution = 0, response = 0;
	
	public Process(int arrived, int duration)
	{
		this.arrived = arrived;
		this.duration = duration;
		this.lastClock = arrived;
	}
	
	public int getWaitingTime() {
		return getReturnTime() - this.execution;
	}

	public int getResponseTime() {
		return this.response;
	}

	public int getReturnTime() {
		return this.lastClock - this.arrived;
	}
	
	public boolean isDone() {
		return this.execution == this.duration;
	}
	
	public int execute(int clock) {
		return execute(clock, this.duration);
	}

	public int execute(int clock, int time)
	{
		int left = this.duration - this.execution;
		if (time > left) time = left;
		
		if (this.execution == 0)
			this.response = clock - this.arrived;
		
		this.execution += time;
		this.lastClock = clock + time;

		Program.log("clock: " + clock + ", time: " + time + ", " + this);
		
		return this.lastClock;
	}
	
	@Override
	public Process clone()
	{
		try {
			return (Process) super.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getSimpleInfo() {
		return	"{ arrived: " + this.arrived
				+ ", duration: " + this.duration + " }";
	}
	
	@Override
	public String toString() {
		return	"{ arrived: " + this.arrived
				+ ", duration: " + this.duration
				+ ", lastClock: " + this.lastClock
				+ ", execution: " + this.execution
				+ ", response: " + this.response
				+ ", getWaitingTime: " + getWaitingTime()
				+ ", getReturnTime: " + getReturnTime()
				+ ", ref: " + super.toString() + " }";
	}
	
}

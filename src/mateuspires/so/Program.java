package mateuspires.so;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mateuspires.so.scheduling.FirstComeFirstServed;
import mateuspires.so.scheduling.RoundRobin;
import mateuspires.so.scheduling.ShortestJobFirst;

public class Program {

	private static final boolean DO_LOG = false;
	
	public static void main(String[] args)
	{
		ArrayList<Process> processes = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		
		while (in.hasNext())
		{
			int arrived = in.nextInt();
			int duration = in.nextInt();
			
			if (arrived == 0 && duration == 0) break;
			
			processes.add(new Process(arrived, duration));
		}
		
		in.close();
		
		System.out.println("FCFS");
		analyse(new FirstComeFirstServed().add(processes).run());
		
		System.out.println("\nSJF");
		analyse(new ShortestJobFirst().add(processes).run());
		
		System.out.println("\nRR");
		analyse(new RoundRobin(2).add(processes).run());
	}
	
	private static void analyse(List<Process> processes)
	{
		float waitingTime = 0, responseTime = 0, returnTime = 0; 
		
		for (Process process : processes)
		{
			waitingTime += process.getWaitingTime() / (float) processes.size();
			responseTime += process.getResponseTime() / (float) processes.size();
			returnTime += process.getReturnTime() / (float) processes.size();
		}
		
		System.out.println("waiting: " + waitingTime + ", response: " + responseTime + ", return: " + returnTime);
	}
	
	public static void log(Object text) {
		if (DO_LOG) System.out.println(text);
	}
	
}

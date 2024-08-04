package telran.numbers;

import java.util.stream.IntStream;

public class GroupsSumTaskThread extends GroupsSum {

	public GroupsSumTaskThread(int[][] groups) {
		super(groups);
	}

	@Override
	public long getSum() {
		SumArrayTask[] tasks = getTasks();
		Thread[] threads = startTasks(tasks);
		waitingThreadsFinished(threads); // puts this main thread into a waiting state
		// until all threads are finished
		
		return sumOfGroups(tasks);
	}

	private void waitingThreadsFinished(Thread[] threads) {
		for(Thread thread: threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// no interrupts
			}
		}		
	}

	private Thread[] startTasks(SumArrayTask[] tasks) {
		Thread[] threads = new Thread[tasks.length];
		IntStream.range(0, threads.length).forEach(i -> {
			threads[i] = new Thread(tasks[i]);
			threads[i].start();
		});
		return threads;
	}

	
	

}

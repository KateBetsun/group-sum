package telran.numbers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GroupsSumThreadPool extends GroupsSum {
	ExecutorService executor;

	public GroupsSumThreadPool(int[][] groups) {
		super(groups);
		this.executor = Executors.newFixedThreadPool(getNumberOfThreads());
	}


	private int getNumberOfThreads() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.availableProcessors();
	}

	@Override
	public long getSum() {
		SumArrayTask[] tasks = getTasks();
		executeTasks(tasks); // puts into the Tasks queue
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS); //awaiting completion all of tasks no more than the specified time
		} catch (InterruptedException e) {
			//no interrupts
		}
		return sumOfGroups(tasks);
	}


	private void executeTasks(SumArrayTask[] tasks) {
		for(SumArrayTask task: tasks) {
			executor.execute(task);
		}		
	}

}

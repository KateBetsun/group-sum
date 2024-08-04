package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.stream.IntStream;

import telran.numbers.*;
import org.junit.jupiter.api.Test;

class GroupsSumTest {
	private static final int N_GROUPS = 10000;
	private static final int GROUP_LENGTH = 10000;

	int[][] groups = {
			{1, 2}, 
			{3, 4},
			{5, 6}
	};
	
	int[][] largeGroups = getLargeGroups(N_GROUPS, GROUP_LENGTH);
	
	@Test
	void groupsSumTaskThreadTest() {
		GroupsSum gs = new GroupsSumTaskThread(groups);
		assertEquals(21,  gs.getSum());
	}
	
	private int[][] getLargeGroups(int nGroups, int groupLength) {
		//creating random two dimensional array
		//using method generate of Stream
		Random random = new Random();
		return IntStream.range(0, nGroups)
				.mapToObj(i -> random.ints(groupLength).toArray())
				.toArray(int[][]::new);
	}
	
	@Test
	void performanceTimeTaskThreadTest() {
		long start = System.currentTimeMillis();
		groupsSumTaskThreadPerformance();
		long finish = System.currentTimeMillis();
		   System.out.println("Time without ThreadPool takes " +
                   (finish - start) + " ms");
	}
	
	@Test
	void performanceTimeTaskThreadPoolTest() {
		long start = System.currentTimeMillis();
		groupsSumTaskThreadPerformance();
		long finish = System.currentTimeMillis();
		   System.out.println("Time with ThreadPool takes " +
                   (finish - start) + " ms");
	}

	@Test
	void groupsSumThreadPoolTest() {
		GroupsSum gs = new GroupsSumThreadPool(groups);
		assertEquals(21,  gs.getSum());
	}
	
	@Test
	void groupsSumTaskThreadPerformance() {
		new GroupsSumTaskThread(largeGroups).getSum();
	}
	@Test
	void groupsSumTaskThreadPoolsPerformance() {
		new GroupsSumThreadPool(largeGroups).getSum();
	}

}

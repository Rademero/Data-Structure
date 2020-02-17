package cs2321;

/**
 * Michael Romero
 *Assignment 4
 * This class is used to find the minimum amount of machines to complete 
 * tasks based off of start and end time of said task 
 */
public class TaskScheduling {
	/**
	 * Goal: Perform all the tasks using a minimum number of machines.
	 * 
	 * 
	 * @param tasks tasks[i][0] is start time for task i tasks[i][1] is end time for
	 *              task i
	 * @return The minimum number or machines
	 */
	//@time O(log n)
		//This is do to the first for loop looping n amount of times
		//followed by the while loop thought the height of the tree
	public static int NumOfMachines(int[][] tasks) {
		HeapPQ2<Integer, int[]> heap = new HeapPQ2();
		//Enters all data into a heap
		for (int i = 0; i <= tasks.length - 1; i++) {
			int[] temp = { tasks[i][0], tasks[i][1] };
			heap.insert(i, temp);
		}
		ArrayList<Integer> list = new ArrayList();
		int machines = 0;
		//runs thought all tasks  to find number of machines
		while (!heap.isEmpty()) {
			int[] min = heap.removeMin().getValue();
			//starting case
			if (list.isEmpty()) {
				list.add(0, min[1]);
				machines++;
			} else {
				//find if a new machine needs to be added
				int j = -1;
				for (int i = 0; i <= list.size() - 1; i++) {
					if (list.get(i) <= min[0]) {
						j = i;
					}
				}
				if (j != -1) {
					list.set(j, min[1]); //a machine starts a new task
				} else {
					list.add(machines, min[1]);//Adds a new machine
					machines++;
				}
			}
		}
		return machines;
	}
}
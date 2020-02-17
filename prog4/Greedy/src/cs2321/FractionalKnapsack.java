package cs2321;
/**
 * Michael Romero
 *Assignment 4
 * This class is used to find the maximum value possible given the benefit and 
 * Weight of objects along with a maximum amount of value 
 */
public class FractionalKnapsack {

   
	/**
	 * Goal: Choose items with maximum total benefit but with weight at most W.
	 *       You are allowed to take fractional amounts from items.
	 *       
	 * @param items items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
	 * @param knapsackWeight
	 * @return The maximum total benefit. Please use double type operation. For example 5/2 = 2.5
	 * 		 
	 */
	//@time O(log n)
	//This is do to the first for loop looping n amount of times
	//followed by the second thought the height of the tree
	public static double MaximumValue(int[][] items, int knapsackWeight) {
		HeapPQ<Integer,Integer> heap = new HeapPQ(); 
		//Emptys all of the data into a heap to be sorted by value
		for(int i=0;i<items.length;i++) {
			int value = items[i][1]/items[i][0];
			heap.insert(value,i );
		}
		double totalValue = 0;//Initial value 
		
		for(int i=0;i<items.length;i++) {
			if(!heap.isEmpty()) {
				int tempWeight = items[heap.min().getValue()][0];
				int tempbenefit = items[heap.min().getValue()][1];
				if(knapsackWeight-tempWeight >=0 ) {
					knapsackWeight -=tempWeight;
					totalValue += tempbenefit; 
					heap.removeMin();
				}else {
					//makes the fractional ration to find how much of an object can be added 
					double fraction = ((double)knapsackWeight/(double)tempWeight); 
	                totalValue += (tempbenefit*fraction); 
	                knapsackWeight = (int)(knapsackWeight - (knapsackWeight*fraction)); 
	                heap.removeMin();
	                break; 
				}
			}
		}
		return totalValue;//end Value
	} 
} 

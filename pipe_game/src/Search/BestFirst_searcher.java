package Search;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class BestFirst_searcher<T> extends common_searcher <T>{

	private Queue<state<T>> nextvisit;
	
	
	
	
	@Override
	public solution search(searchable<T> mysearchable) {
	
		nextvisit.add(mysearchable.getfirststate());
		System.out.println("Best-First-search");
		state<T> checking;
		
		while(!nextvisit.isEmpty())
		{
			checking = nextvisit.remove();
			visited.add(checking);
			if(mysearchable.isGoal(checking) != null)
			{
				System.out.println("Find goal!!!!!!!!");
				return mysearchable.isGoal(checking);
			}
			Queue<state<T>> temp = mysearchable.getposiblestates(checking);
			while(!temp.isEmpty())
			{
				checking = temp.remove();
				if(!visited.contains(checking))
					nextvisit.add(checking);
			}
			System.out.println("Size now: "+nextvisit.size());
		}
		System.out.println("none");
		return null;
		
	}
	
	public  BestFirst_searcher() {
		nextvisit = new PriorityQueue<state<T>>((a,b)->Integer.compare(a.DfromG, b.DfromG));
		//nextvisit = new PriorityQueue<state<T>>();
		visited = new HashSet<state<T>>();

	}
	

}

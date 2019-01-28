package Search;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class HillClimb_searcher<T> extends common_searcher <T>{

	private Queue<state<T>> nextvisit;
	private long TimeToRun = 10000;
	
	
	@Override
	public solution search(searchable<T> mysearchable) {
		Queue<state<T>> temp;
		System.out.println("Hill-Climb-search");
		state<T> checking=mysearchable.getfirststate();
		long Time0 = System.currentTimeMillis();
		while(!nextvisit.isEmpty() || System.currentTimeMillis() - Time0 < TimeToRun)
		{
			visited.add(checking);
			if(mysearchable.isGoal(checking) != null)
			{
				System.out.println("Find goal!!!!!!!!");
				return mysearchable.isGoal(checking);
			}
			if(Math.random() < 0.7)
			{
				temp = mysearchable.getposiblestates(checking);
				while (!temp.isEmpty())
					if (!visited.contains(temp.peek()))
						nextvisit.add(temp.remove());
					else temp.remove();
				checking = nextvisit.remove();
			}
			else
			{
				temp = mysearchable.getposiblestates(checking);
				if (!temp.isEmpty())
					checking = temp.remove();
				while (visited.contains(checking) && (!temp.isEmpty()))
						checking = temp.remove();
				while (!temp.isEmpty())
					if (!visited .contains(temp.peek()))
						nextvisit.add(temp.remove());
					else temp.remove();
			}
			System.out.println("Size now: "+nextvisit.size());
		}
		System.out.println("none");
		return null;
		
	}
	
	public  HillClimb_searcher() {
		
		nextvisit = new PriorityQueue<state<T>>((a,b)->Integer.compare(a.DfromG, b.DfromG));
		//nextvisit = new PriorityQueue<state<T>>();
		visited = new HashSet<state<T>>();

	}
	

}

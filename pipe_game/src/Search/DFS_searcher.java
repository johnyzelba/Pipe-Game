package Search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class DFS_searcher<T> extends common_searcher <T>{

	private Stack<state<T>> nextvisit;
	
	
	
	
	@Override
	public solution search(searchable<T> mysearchable) {
		nextvisit.add(mysearchable.getfirststate());
		System.out.println("DFS-search");
		state<T> checking;
		
		while(!nextvisit.isEmpty())
		{
			checking = nextvisit.pop();
			visited.add(checking);
			if(mysearchable.isGoal(checking) != null)
			{
				System.out.println("Find goal!!!!!!!!");
				return mysearchable.isGoal(checking);
			}
			Queue<state<T>> temp = mysearchable.getposiblestates(checking);
				while(!temp.isEmpty()) {
					checking = temp.remove();
					if(!visited.contains(checking))
						nextvisit.add(checking);
				}					
				System.out.println("next to visit: "+nextvisit.size());
		}
		System.out.println("none");
		return null;
		
	}
	
	public  DFS_searcher() {
		nextvisit = new Stack<state<T>>();
		visited = new HashSet<state<T>>();
	}

	
	

}

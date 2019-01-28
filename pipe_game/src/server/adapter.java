package server;


import Search.searchable;
import Search.searcher;

public class adapter<T,AL> implements solver<T> {
	
	private searcher<T> my_searcher;


	public String solve(searchable<T> mysearchable) {
			
		return my_searcher.search(mysearchable).getSolist();
	}


	public searcher<T> getMy_searcher() {
		return my_searcher;
	}


	public adapter(searcher<T> my_searcher) {
		this.my_searcher = my_searcher;
	}
	
	

}

package server;

import Search.searchable;
import Search.searcher;

public interface solver<T> {
	
	public String solve(searchable<T> mysearchable);

}

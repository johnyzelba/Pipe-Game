package Search;

import java.util.LinkedList;

public class solution {
	private LinkedList<String> solist;

	public solution() {
		this.solist = new LinkedList<String>();
	}
	public void addtosolution(String s)
	{
		solist.add(s);
	}
	public String getSolist() {
		String str = "";
		for (String string : solist) {
			str+= string;
		}
		return str;
	}


	
}

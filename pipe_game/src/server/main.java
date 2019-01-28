package server;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Search.BFS_searcher;
import Search.BestFirst_searcher;
import Search.DFS_searcher;
import Search.HillClimb_searcher;

public class main {

	public static void main(String[] args) {

		single_server server=new single_server();
		server.start(6400, new my_client_handler(), new adapter(new DFS_searcher<Character>()));

		try {
		TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Scanner sc = new Scanner(System.in);
		while(!sc.nextLine().equals("stop"))
			{
			
			
			}
		sc.close();	
		server.stop();

	}

}

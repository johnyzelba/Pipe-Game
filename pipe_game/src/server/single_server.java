package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Search.BFS_searcher;
import Search.pipe_searchable;

public class single_server implements server {

	private int port;
	private client_handler handler;
	private volatile boolean done;
	
//	public single_server (int port, client_handler handler)
//	{
//		this.port=port;
//		this.handler=handler;
//		done=false;
//		
//	}
	@Override
	public void stop() {
		done = true;
	}
	public void start(int port, client_handler handler, solver adap) {
		
		this.port=port;
		this.handler= handler;
		done=false;
		new Thread(()->{
			try {
				runServer(adap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}
	
	public void runServer(solver adap) throws IOException {
		ServerSocket server = new ServerSocket(port);
		server.setSoTimeout(1000);
		System.out.println("Server is alive..");
		while (!done)
		{
			try {
				Socket aClient = server.accept();

				try {
					System.out.println("user conected");
					handler.hc(aClient.getInputStream(), aClient.getOutputStream(), adap);
					
					aClient.getInputStream().close();
					
					aClient.getOutputStream().close();
					aClient.close();
				}
				catch (IOException e) { }
				System.out.println("user disconected");
			}
			catch (SocketTimeoutException e) { }

		}
		server.close();
		System.out.println("Server is closed.");
	}


}

package server;

import java.io.IOException;

public interface server {	

	void start(int port, client_handler handler, solver adap);
	void runServer(solver adap) throws IOException;
	void stop();
}

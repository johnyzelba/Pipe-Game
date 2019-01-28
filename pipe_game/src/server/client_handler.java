package server;

import java.io.InputStream;
import java.io.OutputStream;

public interface client_handler {
	void hc(InputStream in, OutputStream out,solver adap);

	
}

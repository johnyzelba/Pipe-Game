package server;

import java.io.FileInputStream;
import java.util.stream.Stream;

public interface cache_meneger {

	public FileInputStream searchfile( long hash);
	public void addfile(long hash,String sol);
}

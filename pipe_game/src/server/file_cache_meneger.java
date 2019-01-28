package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;

public class file_cache_meneger implements cache_meneger {

	public FileInputStream searchfile(long hash)
	{
		FileInputStream solution = null;
		File f = new File("cache/"+hash+".txt");

		if(f.exists())
		{
			System.out.println("cache/"+hash+".txt exists");

			try {
				solution = new FileInputStream("cache/"+hash+".txt");

			} catch (FileNotFoundException e) {			
				e.printStackTrace();
			}
		}
		else {
			System.out.println(hash+".txt not exists");
		}
		return solution;
	}
	public void addfile(long hash,String sol)
	{
		FileOutputStream solution;
		try {
			solution = new FileOutputStream("cache/"+hash+".txt");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(solution));
			String[] temp = sol.split("\\r?\\n");
			for (String string : temp) 
			{
				System.out.println(string + "sadfas");
				bw.write(string);
				bw.newLine();
			}
			bw.flush();
			solution.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}

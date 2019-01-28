package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import Search.BFS_searcher;
import Search.pipe_searchable;
import Search.searchable;

public class my_client_handler implements client_handler{
	
	private solver s;
	private cache_meneger cm;
	
	@Override
	public void hc(InputStream in, OutputStream out,solver adap) {
		BufferedReader serverInput=new BufferedReader(new InputStreamReader(in));
		BufferedWriter serverOutput = new BufferedWriter(new OutputStreamWriter(out));	
		String solutionString="";
		
		//OutputStreamWriter serverOutput = new OutputStreamWriter(out);	
		
		try {
			int num_of_cols,num_of_rows=0;
			String s,oneString[] = {"",""};
			s = serverInput.readLine();
			num_of_cols = s.length();
			
			while (!s.equals("done"))
		    {
				num_of_rows++;
		        System.out.println(s);
				oneString[0] = oneString[0]+s;
				s = serverInput.readLine();
		    }

			PipeGameBoard mygame=new PipeGameBoard(num_of_cols, num_of_rows, oneString[0]);
			System.out.println(s);
			System.out.println("original string: " + oneString[0]);
			normalize n = new normalize();
			System.out.println("Normalized string: " + n.normalizer(oneString, num_of_cols, num_of_rows));
			
			long hash = n.normalizer(oneString, num_of_cols, num_of_rows).hashCode();
			System.out.println("number string: " + oneString[1]);
			System.out.println("hash string: " + hash);
			
			FileInputStream solution = new file_cache_meneger().searchfile(hash);
			if (solution != null)
			{
				ArrayList<String> cached_solution = new ArrayList<String>();
				BufferedReader buffered_solution=new BufferedReader(new InputStreamReader(solution));
				while ((s=buffered_solution.readLine()) != null)
					cached_solution.add(s); 					
				
				//buffered_solution=new BufferedReader(new InputStreamReader(solution));
				System.out.println("solution string: ");
				int raw=0,col=0,turns=0;

				for (String result: cached_solution) {
					
					raw = Character.getNumericValue(result.charAt(0));
					col = Character.getNumericValue(result.charAt(2));
					turns = Character.getNumericValue(result.charAt(4)) + (Character.getNumericValue(oneString[1].charAt((raw*num_of_cols)+col)));
					char type = (char) oneString[0].charAt(raw*num_of_cols+col);

					if (turns>=4)
						turns-=4;
					if (turns>=2 && (type == '|' || type == '-'))
						turns-=2;
					System.out.println(raw + "," + col + "," + turns);
					
					if(turns !=0)
						{
							serverOutput.write(raw + "," + col + "," + turns);
							serverOutput.newLine();

						}
					
					}
				solution.close();
				serverOutput.flush();
			}
			else
			{
				//solver adap = new adapter(new BFS_searcher<Character>());
				solutionString = adap.solve(new pipe_searchable(mygame));
				serverOutput.write(solutionString);
				serverOutput.flush();
				
				int raw=0,col=0,turns=0;
				String[] temp = solutionString.split("\\r?\\n");
				solutionString = "";		
				for (String result: temp) {
					
					raw = Character.getNumericValue(result.charAt(0));
					col = Character.getNumericValue(result.charAt(2));
					turns = Character.getNumericValue(result.charAt(4)) + (Character.getNumericValue(oneString[1].charAt((raw*num_of_cols)+col)));
					char type = (char) oneString[0].charAt(raw*num_of_cols+col);

					if (turns>=4)
						turns-=4;
					if (turns>=2 && (type == '|' || type == '-'))
						turns-=2;
					System.out.println(raw + "," + col + "," + turns);
					solutionString+=raw + "," + col + "," + turns + "\n";
					
				}
				
				file_cache_meneger newsolution = new file_cache_meneger();
				newsolution.addfile(hash, solutionString);
			}
			
			

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

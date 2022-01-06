package comp2402a4;
// Thanks to Pat Morin for this file!

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeMap;

public class Part2 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		TreeMap<String, Integer> s = new TreeMap<>();
		for (String line = r.readLine(); line != null; line = r.readLine()) {
			if(s.isEmpty()){
				if(line.startsWith("c")){
					s.put(line, 1);
					w.println(line);
				}
				else{
					w.println("*");
				}
			}
			else{
				if(line.startsWith("c")){
					if(s.containsKey(line)){
						s.put(line, s.get(line)+1);
					}
					else{
						s.put(line, 1);
					}
					w.println(s.firstKey());
				}
				else if(line.equals("###")){
					s.put(s.firstKey(), s.get(s.firstKey())-1);
					if(s.get(s.firstKey())==0){
						s.remove(s.firstKey());
					}
					if(!s.isEmpty()){
						w.println(s.firstKey());
					}
					else{
						w.println("*");
					}
				}
				else{
					w.println(s.firstKey());
				}
			}
		}
	}

	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);				
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 1e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}

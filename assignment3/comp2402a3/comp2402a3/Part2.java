package comp2402a3;
// Thanks to Pat Morin for this file!

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Part2 {
	
	/**
	 * Your code goes here - see Part0 for an example
   * @param x the number of lines to read in
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(int x, BufferedReader r, PrintWriter w) 
      throws IOException {
        Deque<String> dt = new ArrayDeque<>();
        String temp="";
        boolean state = true;
        if(x==0){
          x++;
        }
        for (String line = r.readLine(); line != null; line = r.readLine()) {
          dt.addLast(line);
          if(dt.size() > x) {
            if(temp.compareTo(dt.getFirst())==0){
              dt.removeFirst();
              if(dt.contains(temp)==false){
                String max=null;
                for(String value : dt){
                  if(max==null){
                    max = value;
                  }
                  else{
                    if(max.compareTo(value) <=0){
                      max = value;
                    }
                  }
                }
                temp = max;
              }
            }
            else{
              dt.removeFirst();
            }
          }

          if(dt.size() >= x && dt.getLast().compareTo(temp) >= 0){
            w.println(dt.getLast());
          }

          if(state==true){
            temp = line;
            state = false;
          }
          else{
            if(temp.compareTo(dt.getLast()) <= 0){
              temp = dt.getLast();
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
      int x;
      if (args.length == 0) {
        x = 3;
        r = new BufferedReader(new InputStreamReader(System.in));
        w = new PrintWriter(System.out);
      } else if( args.length == 1) {
        x = Integer.parseInt(args[0]); 
        r = new BufferedReader(new InputStreamReader(System.in));
        w = new PrintWriter(System.out);
      } else if (args.length == 2) {
        x = Integer.parseInt(args[0]); 
        r = new BufferedReader(new FileReader(args[1]));
        w = new PrintWriter(System.out);				
      } else {
        x = Integer.parseInt(args[0]); 
        r = new BufferedReader(new FileReader(args[1]));
        w = new PrintWriter(new FileWriter(args[2]));
      }
      long start = System.nanoTime();
      doIt(x, r, w);
      w.flush();
      long stop = System.nanoTime();
      System.out.println("Execution time: " + 1e-9 * (stop-start));
    } catch (IOException e) {
      System.err.println(e);
      System.exit(-1);
    }
  }
}

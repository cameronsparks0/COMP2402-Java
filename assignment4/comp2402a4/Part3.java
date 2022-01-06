package comp2402a4;
// Thanks to Pat Morin for this file!

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.SortedSet;

public class Part3 {

    /**
     * Your code goes here - see Part0 for an example
     * @param r the reader to read from
     * @param w the writer to write to
     * @throws IOException
     */
    public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
        TreeSet<String> l = new TreeSet<>();
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            if(l.isEmpty()){
                l.add(line);
            }
            else{
                SortedSet<String> temp = new TreeSet<>(l.tailSet(line));
                l.removeAll(temp);
                l.add(line);
            }
        }
        for(String s: l){
            w.println(s);
        }
    }

    /**
     * The driver.  Open a
     *  BufferedReader and a PrintWriter, either from System.in
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

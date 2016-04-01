package smallworld;

import edu.princeton.cs.StdIn;
import edu.princeton.cs.StdOut;

/**
 * ****************************************************************************
 * Compilation: javac WordLadder.java Execution: java WordLadder word1 word2 <
 * wordlist.txt Dependencies: Graph.java In.java PathFinder.java
 *
 * java WordLadder break flirt < words5.txt flirt flint fling cling clink click
 * clock cloak croak creak break
 *
 * % java WordLadder allow brown < words5.txt NOT CONNECTED
 *
 *****************************************************************************
 */
public class WordLadder {

    // return true if two strings differ in exactly one letter
    public static boolean isNeighbor(String a, String b) {
        int differ = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                differ++;
            }
        }
        return (differ == 1);
    }

    public static void main(String[] args) {
        String from = args[0];
        String to = args[1];
        if (from.length() != to.length()) {
            throw new RuntimeException("Words have different lengths");
        }

        /**
         * *****************************************************************
         * Read a list of strings, all of the same length. At most 10000 words
         * supported here.
        ******************************************************************
         */
        String[] words = new String[10000];
        int N = 0;
        while (!StdIn.isEmpty()) {
            words[N] = StdIn.readString();
            if (words[N].length() != from.length()) {
                throw new RuntimeException("Words have different lengths");
            }
            N++;
        }
        System.err.println("Finished reading word list");

        /**
         * *****************************************************************
         * Insert connections between neighboring words into graph. This
         * construction process can be improved from LN^2 to L N log N by
         * sorting, where L = length of words.
        ******************************************************************
         */
        Graph G = new Graph();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isNeighbor(words[i], words[j])) {
                    G.addEdge(words[i], words[j]);
                }
            }
        }
        System.err.println("Finished building graph");

        /**
         * *****************************************************************
         * Run breadth first search
        ******************************************************************
         */
        PathFinder finder = new PathFinder(G, from);
        if (finder.hasPathTo(to)) {
            StdOut.println(finder.pathTo(to));
        } else {
            StdOut.println("NOT CONNECTED");
        }
    }
}
